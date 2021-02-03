package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.services.LogoutService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user")
public class UserPageServlet extends HttpServlet {

    private UserService userService;
    private LogoutService logoutService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
        logoutService = (LogoutService) context.getAttribute("logoutService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        Long id = (Long) req.getSession().getAttribute("userId");
        Boolean isProfile = true;
        try {
            if (req.getParameter("id") != null) {
                id = Long.parseLong(req.getParameter("id"));
                isProfile = false;
            }
        } catch (NumberFormatException e) {
            context.log(e.getMessage());
        } finally {
            try {
                Optional<UserDto> udto = userService.getUserById(id);
                if (udto.isPresent()) {
                    UserDto user = udto.get();
                    req.setAttribute("email", user.getEmail());
                    req.setAttribute("link", "/user?id=" + user.getId());
                    req.setAttribute("movies", user.getFavourites());
                    req.setAttribute("isProfile", isProfile);

                    req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/user");
                }
            } catch (ServletException | IOException e) {
                context.log(e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        try {
            resp.addCookie(logoutService.logout(req.getSession()));
            resp.sendRedirect("/home");
        } catch (IOException e) {
            context.log(e.getMessage());
        }
    }
}
