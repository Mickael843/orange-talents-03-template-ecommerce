package com.mikkaeru.ecommerce.fake;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/fake")
public class FakeController {

    @PostMapping("/invoices")
    public ResponseEntity<?> invoice(@RequestBody @Valid InvoiceFakeRequest request) throws InterruptedException {
        System.out.println("CRIANDO NOTA FISCAL PARA => " + request.toString());
        Thread.sleep(200);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ranking")
    public ResponseEntity<?> ranking(@RequestBody @Valid RankingFakeRequest request) throws InterruptedException {
        System.out.println("ADICIONANDO VENDEDOR => " + request.getSellerId() + " NO RANKING DE VENDAS");
        Thread.sleep(200);
        return ResponseEntity.ok().build();
    }
}
