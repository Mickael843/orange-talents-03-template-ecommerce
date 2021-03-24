package com.mikkaeru.ecommerce.controller.product.question;

import com.mikkaeru.ecommerce.dto.in.product.question.QuestionRequest;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.product.ProductRepository;
import com.mikkaeru.ecommerce.repository.product.question.QuestionRepository;
import com.mikkaeru.ecommerce.service.EmailService;
import com.mikkaeru.ecommerce.service.impl.EmailServiceFakeImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/products/{id}/questions")
public class QuestionController {

    private final EmailService emailService;
    private final ProductRepository productRepository;
    private final QuestionRepository questionRepository;

    public QuestionController(ProductRepository productRepository, QuestionRepository questionRepository) {
        this.emailService = new EmailServiceFakeImpl();
        this.productRepository = productRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> addQuestion(@PathVariable Long id, @RequestBody @Valid QuestionRequest questionRequest, @AuthenticationPrincipal User user) {
        questionRequest.setProductId(id);
        questionRequest.setLoggedUser(user);
        questionRepository.save(questionRequest.toModel(productRepository));
        emailService.sendEmail(questionRequest.getProductOwner());
        return ResponseEntity.ok().build();
    }
}
