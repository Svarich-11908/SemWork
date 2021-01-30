package ru.itis.services;

import java.io.InputStream;
import java.util.List;

public interface PhotoService {
    void upload(InputStream stream, String filename);
    List<String> getGallery();
}
