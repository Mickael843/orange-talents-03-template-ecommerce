package com.mikkaeru.ecommerce.controller.buy;

import com.mikkaeru.ecommerce.controller.product.ProductBuyRequest;
import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.buy.PaymentGateway;
import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.buy.BuyRepository;
import com.mikkaeru.ecommerce.repository.product.ProductRepository;
import com.mikkaeru.ecommerce.service.EmailService;
import com.mikkaeru.ecommerce.service.impl.EmailServiceFakeImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/buy")
public class BuyController {

    private final EmailService emailService;
    private final BuyRepository buyRepository;
    private final ProductRepository productRepository;

    public BuyController(BuyRepository buyRepository, ProductRepository productRepository) {
        this.buyRepository = buyRepository;
        this.productRepository = productRepository;
        this.emailService = new EmailServiceFakeImpl();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> buyProducts(@RequestBody @Valid  ProductBuyRequest request,
                                         @AuthenticationPrincipal User loggedUser, UriComponentsBuilder builder) throws BindException {
        Optional<Product> product = productRepository.findById(request.getProductId());

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        boolean isRemoved = product.get().removeFromStock(request.getAmount());

        if (isRemoved) {
            PaymentGateway gateway = request.getGateway();

            Buy newBuy = new Buy(product.get(), product.get().getPrice(), request.getAmount(), loggedUser, gateway);

            newBuy = buyRepository.save(newBuy);

            product.get().sendEmailProductOwner(emailService);

            return ResponseEntity.ok(gateway.generateUrl(builder, newBuy));
        }

        BindException stockProblem = new BindException(request, "request");
        stockProblem.reject(null, "Não foi possível realizar a compra do produto!");
        throw stockProblem;
    }
}
