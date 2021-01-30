package ru.itis.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import ru.itis.repostories.GalleryRepository;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PhotoServiceImpl implements PhotoService {

    private final static String PHOTOS_DIRECTORY = "C:/pics/";

    private BASE64Encoder encoder;
    private GalleryRepository galleryRepository;

    public PhotoServiceImpl(BASE64Encoder encoder, GalleryRepository galleryRepository) {
        this.encoder = encoder;
        this.galleryRepository = galleryRepository;
    }

    @Override
    public void upload(InputStream stream, String filename) {
        try {
            System.out.println("2");
            String extension = FilenameUtils.getExtension(filename);
            String path = PHOTOS_DIRECTORY + System.currentTimeMillis() + "." + extension;
            Files.copy(stream, Paths.get(path));
            galleryRepository.save(path);
        } catch (IOException e) {}
    }

    @Override
    public List<String> getGallery() {
        return galleryRepository.findAll().stream()
                .map(x -> new File(x))
                .map(x -> {
                    try {
                        return FileUtils.readFileToByteArray(x);
                    } catch (IOException e) {
                        return new byte[0];
                    }
                })
                .map(x -> "data:image/png;base64," + encoder.encode(x))
                .collect(Collectors.toList());
    }
}
