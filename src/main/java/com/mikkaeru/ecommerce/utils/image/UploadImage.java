package com.mikkaeru.ecommerce.utils.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadImage {

    List<String> sendImages(List<MultipartFile> images);
}
