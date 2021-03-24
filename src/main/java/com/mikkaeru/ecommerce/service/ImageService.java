package com.mikkaeru.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<String> sendImages(List<MultipartFile> images);
}
