package com.mikkaeru.ecommerce.controller.product;

import com.mikkaeru.ecommerce.dto.in.product.ImageRequest;
import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.product.ProductImage;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.product.ProductImageRepository;
import com.mikkaeru.ecommerce.repository.product.ProductRepository;
import com.mikkaeru.ecommerce.service.ImageService;
import com.mikkaeru.ecommerce.service.impl.ImageServiceFakeImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products/{id}")
public class ImageProductController {

    private final ImageService imageService;
    private final ProductRepository productRepository;
    private final ProductImageRepository imageRepository;

    public ImageProductController(ProductRepository productRepository, ProductImageRepository imageRepository) {
        this.imageService = new ImageServiceFakeImpl();
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional
    @PostMapping("/images")
    public ResponseEntity<?> addImageForProduct(@PathVariable Long id, @Valid ImageRequest imageRequest) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> links = imageService.sendImages(imageRequest.getImages());

        List<ProductImage> productImages = links.stream().map(link -> new ProductImage(link, product.get())).collect(Collectors.toList());

        imageRepository.saveAll(productImages);

        return ResponseEntity.ok().build();
    }
}
