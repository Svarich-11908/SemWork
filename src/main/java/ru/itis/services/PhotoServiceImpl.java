package ru.itis.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.postgresql.util.Base64;
import ru.itis.repostories.GalleryRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PhotoServiceImpl implements PhotoService {

    private static final String PHOTOS_DIRECTORY = "C:/pics/";

    private GalleryRepository galleryRepository;

    public PhotoServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public void upload(InputStream stream, String filename) {
        try {
            String extension = FilenameUtils.getExtension(filename);
            String path = PHOTOS_DIRECTORY + System.currentTimeMillis() + "." + extension;
            Files.copy(stream, Paths.get(path));
            galleryRepository.save(path);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public List<String> getGallery() {
        return galleryRepository.findAll().stream()
                .map(File::new)
                .map(x -> {
                    try {
                        return FileUtils.readFileToByteArray(x);
                    } catch (IOException e) {
                        return new byte[0];
                    }
                })
                .map(x -> "data:image/png;base64," + Base64.encodeBytes(x))
                .collect(Collectors.toList());
    }
}
