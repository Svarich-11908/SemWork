package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserBySessionId(String sid);
    Optional<UserDto> getUserById(Long id);
}
