package ru.itis.servlets;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ru.itis.dto.UserForm;
import ru.itis.services.LoginService;
import ru.itis.services.RegistrationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/login")
public class RegistrationServlet extends HttpServlet {

    private RegistrationService registrationService;
    private LoginService loginService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.registrationService = (RegistrationService) context.getAttribute("registrationService");
        this.loginService = (LoginService) context.getAttribute("loginService");
        this.validator = (Validator) context.getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("fail", new String());
        req.setAttribute("errors", new ArrayList<Object>());
        req.getRequestDispatcher("/jsp/user_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserForm form = new UserForm(
                req.getParameter("email"),
                req.getParameter("password"));

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(form);

        if (!constraintViolations.isEmpty()) {
            req.setAttribute("fail", new String());
            req.setAttribute("errors", constraintViolations);
            req.getRequestDispatcher("/jsp/user_form.jsp").forward(req, resp);
        } else {
            if (req.getParameter("action") == null || req.getParameter("action").equals("register")) {
                if (!registrationService.register(form)) {
                    req.setAttribute("fail", "This email is already taken");
                    req.setAttribute("errors", new ArrayList<Object>());
                    req.getRequestDispatcher("/jsp/user_form.jsp").forward(req, resp);
                } else {
                    resp.addCookie(loginService.login(form, req.getSession()));
                    resp.sendRedirect("/user");
                }
            } else {
                Cookie cookie = loginService.login(form, req.getSession());
                if (cookie == null) {
                    req.setAttribute("fail", "Email or password are wrong");
                    req.setAttribute("errors", new ArrayList<Object>());
                    req.getRequestDispatcher("/jsp/user_form.jsp").forward(req, resp);
                } else {
                    resp.addCookie(cookie);
                    resp.sendRedirect("/user");
                }
            }
        }
    }
}
