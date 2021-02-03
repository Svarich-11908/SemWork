package ru.itis.servlets;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ru.itis.dto.UserForm;
import ru.itis.services.LoginService;
import ru.itis.services.RegistrationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/login")
public class RegistrationServlet extends HttpServlet {

    private static final String MESSAGE_ATTRIBUTE = "fail";
    private static final String ERROR_ATTRIBUTE = "errors";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String pagePath = (String) context.getAttribute("loginPagePath");
        try {
            req.setAttribute(MESSAGE_ATTRIBUTE, "");
            req.setAttribute(ERROR_ATTRIBUTE, new ArrayList<Object>());
            req.getRequestDispatcher(pagePath).forward(req, resp);
        } catch (ServletException | IOException e) {
            context.log(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String pagePath = (String) context.getAttribute("loginPagePath");
        RegistrationService registrationService = (RegistrationService) context.getAttribute("registrationService");
        LoginService loginService = (LoginService) context.getAttribute("loginService");
        Validator validator = (Validator) context.getAttribute("validator");
        try {
            UserForm form = new UserForm(
                    req.getParameter("email"),
                    req.getParameter("password"));

            Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(form);

            if (!constraintViolations.isEmpty()) {
                req.setAttribute(MESSAGE_ATTRIBUTE, "");
                req.setAttribute(ERROR_ATTRIBUTE, constraintViolations);
                req.getRequestDispatcher(pagePath).forward(req, resp);
            } else {
                if (req.getParameter("action") == null || req.getParameter("action").equals("register")) {
                    if (Boolean.FALSE.equals(registrationService.register(form))) {
                        req.setAttribute(MESSAGE_ATTRIBUTE, "This email is already taken");
                        req.setAttribute(ERROR_ATTRIBUTE, new ArrayList<Object>());
                        req.getRequestDispatcher(pagePath).forward(req, resp);
                    } else {
                        resp.addCookie(loginService.login(form, req.getSession()));
                        resp.sendRedirect("/user");
                    }
                } else {
                    Cookie cookie = loginService.login(form, req.getSession());
                    if (cookie == null) {
                        req.setAttribute(ERROR_ATTRIBUTE, "Email or password are wrong");
                        req.setAttribute(MESSAGE_ATTRIBUTE, new ArrayList<Object>());
                        req.getRequestDispatcher(pagePath).forward(req, resp);
                    } else {
                        resp.addCookie(cookie);
                        resp.sendRedirect("/user");
                    }
                }
            }
        } catch (ServletException | IOException e) {
            context.log(e.getMessage());
        }
    }
}
