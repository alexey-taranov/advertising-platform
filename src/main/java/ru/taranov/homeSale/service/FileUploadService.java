package ru.taranov.homeSale.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    String upload(MultipartFile file) throws IOException;
}
