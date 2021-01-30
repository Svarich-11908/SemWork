package ru.itis.services;

import ru.itis.repostories.FavouriteRepository;

public class FavouriteServiceImpl implements FavouriteService {

    private FavouriteRepository favouriteRepository;

    public FavouriteServiceImpl(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public void addFavourite(Long userId, Long movieId) {
        favouriteRepository.save(userId, movieId);
    }

    @Override
    public void deleteFavourite(Long userId, Long movieId) {
        favouriteRepository.delete(userId, movieId);
    }

    @Override
    public Boolean isFavourite(Long userId, Long movieId) {
        return favouriteRepository.findSecondByKey(userId).contains(movieId);
    }

    @Override
    public Long countFavourites(Long movieId) {
        return favouriteRepository.countSecondByKey(movieId);
    }
}
