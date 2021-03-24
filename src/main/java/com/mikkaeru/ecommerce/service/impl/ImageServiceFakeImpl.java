package com.mikkaeru.ecommerce.service.impl;

import com.mikkaeru.ecommerce.service.ImageService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class ImageServiceFakeImpl implements ImageService {

    @Override
    public List<String> sendImages(List<MultipartFile> images) {
        return images.stream()
                .map(multipartFile -> "https://test.fake.file.com/" + multipartFile.getOriginalFilename())
                .collect(Collectors.toList());
    }
}
