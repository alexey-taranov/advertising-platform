package ru.taranov.homeSale.service;

import org.springframework.web.multipart.MultipartFile;
import ru.taranov.homeSale.entity.Photo;

import java.io.IOException;

public interface PhotoService {

    public void savePhotoImage(Photo photo, MultipartFile imageFile) throws Exception;

    public void save(Photo photo);
}
