package com.mikkaeru.ecommerce.controller.product.opnion;

import com.mikkaeru.ecommerce.dto.in.product.opinion.OpinionRequest;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.product.ProductRepository;
import com.mikkaeru.ecommerce.repository.product.opinion.OpinionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/products/{id}/opinions")
public class OpinionController {

    private final ProductRepository productRepository;
    private final OpinionRepository opinionRepository;

    public OpinionController(ProductRepository productRepository, OpinionRepository opinionRepository) {
        this.productRepository = productRepository;
        this.opinionRepository = opinionRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> addOpinion(@PathVariable Long id, @RequestBody @Valid OpinionRequest opinionRequest, @AuthenticationPrincipal User user) {
        opinionRequest.setProductId(id);
        opinionRequest.setLoggedUser(user);
        opinionRepository.save(opinionRequest.toModel(productRepository));
        return ResponseEntity.ok().build();
    }
}
