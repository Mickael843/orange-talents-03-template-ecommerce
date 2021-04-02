package com.mikkaeru.ecommerce.controller.product;

import com.mikkaeru.ecommerce.dto.in.characteristic.CharacteristicRequest;
import com.mikkaeru.ecommerce.dto.in.product.ProductRequest;
import com.mikkaeru.ecommerce.dto.out.product.ProductResponse;
import com.mikkaeru.ecommerce.model.characteristic.Characteristic;
import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.category.CategoryRepository;
import com.mikkaeru.ecommerce.repository.characteristic.CharacteristicRepository;
import com.mikkaeru.ecommerce.repository.product.ProductRepository;
import com.mikkaeru.ecommerce.repository.product.opinion.OpinionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final OpinionRepository opinionRepository;
    private final CategoryRepository categoryRepository;
    private final CharacteristicRepository characteristicRepository;

    public ProductController(ProductRepository productRepository, OpinionRepository opinionRepository, CategoryRepository categoryRepository, CharacteristicRepository characteristicRepository) {
        this.productRepository = productRepository;
        this.opinionRepository = opinionRepository;
        this.categoryRepository = categoryRepository;
        this.characteristicRepository = characteristicRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest, @AuthenticationPrincipal User user) {
        productRequest.setUserAuthenticated(user);

        List<CharacteristicRequest> characteristicList = productRequest.getCharacteristics();

        Product product = productRepository.save(productRequest.toModel(categoryRepository));

        // TODO Salvar as características no banco de dados pelo objeto produto

         characteristicRepository.saveAll(
                characteristicList.stream()
                        .map(characteristicRequest -> new Characteristic(characteristicRequest.getName(), characteristicRequest.getDescription(), product))
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ProductResponse(product.get(), opinionRepository));
    }
}
