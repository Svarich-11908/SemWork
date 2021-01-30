package ru.itis.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String email;
    private List<MovieDto> favourites;

    public UserDto(Long id, String email, List<MovieDto> favourites) {
        this.id = id;
        this.email = email;
        this.favourites = favourites;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<MovieDto> getFavourites() {
        return favourites;
    }
}
