package ru.itis.filter;


import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/*")
public class MyServletFilter implements Filter {

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = (UserService) filterConfig.getServletContext().getAttribute("userService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        Boolean isAuthenticated = false;
        Boolean isRequestOnOpenPage = request.getRequestURI().equals("/login") || request.getRequestURI().equals("/home");
        Boolean isRequestOnEmptyPage = request.getRequestURI().equals("/");

        Cookie[] cookies = request.getCookies();
        Cookie c = null;
        if (cookies != null){
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionId")) {
                c = cookie;
                break;
            }
        }}
        if (c != null) {
            Optional<User> user = userService.getUserBySessionId(c.getValue());
            if (user.isPresent()) {
                session.setAttribute("userId", user.get().getId());
                isAuthenticated = true;
            }
        }

        if (isRequestOnEmptyPage) {
            response.sendRedirect("/home");
        } else if (isAuthenticated && !isRequestOnOpenPage || !isAuthenticated && isRequestOnOpenPage) {
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isRequestOnOpenPage) {
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }
}
