package ru.itis.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public interface LogoutService {
    Cookie logout(HttpSession session);
}
