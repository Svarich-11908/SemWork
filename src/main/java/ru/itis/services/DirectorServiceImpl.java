package ru.itis.services;

import ru.itis.dto.DirectorDto;
import ru.itis.models.Director;
import ru.itis.repostories.DirectorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DirectorServiceImpl implements DirectorService {

    private DirectorRepository directorRepository;
    private static final String LINK_PREFIX = "/director?id=";

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Optional<Director> getFullDirectorById(Long id) {
        return directorRepository.findById(id);
    }

    @Override
    public Optional<DirectorDto> getDirectorById(Long id) {
        Optional<Director> director = getFullDirectorById(id);
        if (director.isPresent()) {
            return Optional.of(new DirectorDto(director.get().getName(), LINK_PREFIX + director.get().getId()));
        } else return Optional.empty();
    }

    @Override
    public List<DirectorDto> getList() {
        return directorRepository.findAll().stream()
                .map(x -> new DirectorDto(x.getName(), LINK_PREFIX + x.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DirectorDto> getMatching(String sub) {
        return directorRepository.findAll().stream()
                .map(x -> new DirectorDto(x.getName(), LINK_PREFIX + x.getId()))
                .filter(x -> x.getName().toLowerCase().contains(sub.toLowerCase()))
                .collect(Collectors.toList());
    }
}
