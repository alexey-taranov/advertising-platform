package ru.taranov.homeSale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.taranov.homeSale.dao.PhotoRepository;
import ru.taranov.homeSale.entity.Photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void save(MultipartFile file) throws IOException {
        Photo photo = new Photo(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        photoRepository.save(photo);
    }

    @Override
    public void savePhotoImage(Photo photo, MultipartFile imageFile) throws Exception {
        // this gets us to src/main/resources without knowing the full path (hardcoding)
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        photo.setPath(absolutePath + "/src/main/resources/static/photos/");
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);

    }

    @Override
    public void save(Photo photo) {
        photoRepository.save(photo);
    }
}
