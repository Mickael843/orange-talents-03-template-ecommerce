package com.mikkaeru.ecommerce.fake;

import com.mikkaeru.ecommerce.utils.image.UploadImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class UploadImageFake implements UploadImage {

    @Override
    public List<String> sendImages(List<MultipartFile> images) {
        return images.stream()
                .map(multipartFile -> "https://test.fake.file.com/" + multipartFile.getOriginalFilename())
                .collect(Collectors.toList());
    }
}
