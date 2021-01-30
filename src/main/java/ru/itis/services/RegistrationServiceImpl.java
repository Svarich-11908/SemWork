package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repostories.UserRepository;

public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean register(UserForm form) {
        if (!userRepository.findByEmail(form.getEmail()).isPresent()) {
            User user = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()));
            userRepository.save(user);
            return true;
        } else return false;
    }
}
