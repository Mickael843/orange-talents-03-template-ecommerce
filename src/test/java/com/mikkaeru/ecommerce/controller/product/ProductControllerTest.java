package com.mikkaeru.ecommerce.controller.product;

import com.mikkaeru.ecommerce.helper.IntegrationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends IntegrationHelper {

    private static final String ENDPOINT = "/products";

    @BeforeEach
    void setUp() throws Exception {
        addHeaders();
    }

    @Test
    @DisplayName("Dado um json valido deve retornar status code 200")
    void GIVEN_ValidPayload_MUST_ReturnOK() throws Exception {
        var payload = provideData("src/test/resources/json/product.json", "product");

        mockMvc.perform(post(ENDPOINT)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk());
    }

}