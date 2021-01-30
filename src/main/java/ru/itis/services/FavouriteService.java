package ru.itis.services;

public interface FavouriteService {
    void addFavourite(Long userId, Long movieId);
    void deleteFavourite(Long userId, Long movieId);
    Boolean isFavourite(Long userId, Long movieId);
    Long countFavourites(Long movieId);
}
