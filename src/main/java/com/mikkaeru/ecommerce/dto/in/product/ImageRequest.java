package com.mikkaeru.ecommerce.dto.in.product;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ImageRequest {

    @NotNull
    @Size(min = 1)
    private final List<MultipartFile> images;

    public ImageRequest(@Size(min = 1) List<MultipartFile> images) {
        this.images = images;
    }

    public List<MultipartFile> getImages() {
        return images;
    }
}
