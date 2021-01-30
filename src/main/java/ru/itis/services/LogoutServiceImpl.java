package ru.itis.services;

import ru.itis.models.Session;
import ru.itis.repostories.SessionRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LogoutServiceImpl implements LogoutService {

    private SessionRepository sessionRepository;

    public LogoutServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Cookie logout(HttpSession session) {
        session.setAttribute("userId", null);
        Optional<Session> sessionEntity = sessionRepository.findBySessionId(session.getId());
        if (sessionEntity.isPresent()) {
            sessionRepository.delete(sessionEntity.get().getId());
        }
        Cookie cookie = new Cookie("sessionId", session.getId());
        cookie.setMaxAge(0);
        return cookie;
    }
}
