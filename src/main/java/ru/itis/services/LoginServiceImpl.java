package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.UserForm;
import ru.itis.models.Session;
import ru.itis.models.User;
import ru.itis.repostories.SessionRepository;
import ru.itis.repostories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private UserRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private SessionRepository sessionRepository;

    public LoginServiceImpl(UserRepository usersRepository, PasswordEncoder passwordEncoder, SessionRepository sessionRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Cookie login(UserForm form, HttpSession session) {
        Optional<User> userOptional = usersRepository.findByEmail(form.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(form.getPassword(), user.getHashPassword())) {
                session.setAttribute("userId", user.getId());
                sessionRepository.save(new Session(session.getId(), user.getId()));
                Cookie cookie = new Cookie("sessionId", session.getId());
                cookie.setMaxAge(10000);
                return cookie;
            } else {
                Cookie cookie = new Cookie("sessionId", session.getId());
                cookie.setMaxAge(0);
                return cookie;
            }
        } else {
            Cookie cookie = new Cookie("sessionId", session.getId());
            cookie.setMaxAge(0);
            return cookie;
        }
    }
}
