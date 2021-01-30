package ru.itis.services;

import ru.itis.dto.UserForm;

public interface RegistrationService {
    Boolean register(UserForm form);
}
