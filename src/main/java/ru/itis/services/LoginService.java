package ru.itis.services;

import ru.itis.dto.UserForm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public interface LoginService {
    Cookie login(UserForm form, HttpSession session);
}
