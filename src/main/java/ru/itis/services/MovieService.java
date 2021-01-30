package ru.itis.services;

import ru.itis.dto.MovieDto;
import ru.itis.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService extends ListService<MovieDto> {
    Optional<Movie> getFullMovieById(Long id);
    List<MovieDto> getByDirectorId(Long id);
}
