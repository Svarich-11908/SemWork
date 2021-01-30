package ru.itis.services;

import ru.itis.dto.MovieDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Movie;
import ru.itis.models.Session;
import ru.itis.models.User;
import ru.itis.repostories.FavouriteRepository;
import ru.itis.repostories.MovieRepository;
import ru.itis.repostories.SessionRepository;
import ru.itis.repostories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private FavouriteRepository favouriteRepository;
    private MovieRepository movieRepository;

    public UserServiceImpl(UserRepository userRepository, SessionRepository sessionRepository,
                           FavouriteRepository favouriteRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.favouriteRepository = favouriteRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<User> getUserBySessionId(String sid) {
        Optional<Session> session = sessionRepository.findBySessionId(sid);
        if (session.isPresent()) {
            return userRepository.findById(session.get().getUserId());
        } else return Optional.empty();
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            List<Movie> movies = movieRepository.findAllByIds(favouriteRepository.findSecondByKey(u.getId()));
            List<MovieDto> movieDtos = movies.stream()
                    .map(x -> new MovieDto(x.getTitle() + " (" + x.getYear() + ")", "/movie?id=" + x.getId()))
                    .collect(Collectors.toList());
            return Optional.of(new UserDto(u.getId(), u.getEmail(), movieDtos));
        }
        return Optional.empty();
    }
}
