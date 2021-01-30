package ru.itis.services;

import ru.itis.dto.DirectorDto;
import ru.itis.models.Director;

import java.util.Optional;

public interface DirectorService extends ListService<DirectorDto> {
    Optional<Director> getFullDirectorById(Long id);
    Optional<DirectorDto> getDirectorById(Long id);
}
