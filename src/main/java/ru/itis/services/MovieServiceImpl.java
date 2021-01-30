package ru.itis.services;

import ru.itis.dto.MovieDto;
import ru.itis.models.Movie;
import ru.itis.repostories.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<Movie> getFullMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<MovieDto> getByDirectorId(Long id) {
        return movieRepository.findByDirectorId(id).stream()
                .map(x -> new MovieDto(x.getTitle() + " (" + x.getYear() + ")", "/movie?id=" + x.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getList() {
        return movieRepository.findAll().stream()
                .map(x -> new MovieDto(x.getTitle() + " (" + x.getYear() + ")", "/movie?id=" + x.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMatching(String sub) {
        return movieRepository.findAll().stream()
                .map(x -> new MovieDto(x.getTitle() + " (" + x.getYear() + ")", "/movie?id=" + x.getId()))
                .filter(x -> x.getTitle().toLowerCase().contains(sub.toLowerCase()))
                .collect(Collectors.toList());
    }
}
