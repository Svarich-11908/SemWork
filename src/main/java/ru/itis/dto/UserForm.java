package ru.itis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class UserForm {
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

    @Length(min = 8,
            max = 20,
            message = "Password must be between {min} and {max} characters long")
    private String password;

    public UserForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
