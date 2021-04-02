package com.mikkaeru.ecommerce.controller.product;

import com.mikkaeru.ecommerce.helper.IntegrationHelper;
import com.mikkaeru.ecommerce.model.characteristic.Characteristic;
import com.mikkaeru.ecommerce.repository.characteristic.CharacteristicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest extends IntegrationHelper {

    private static final String ENDPOINT = "/products";

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @BeforeEach
    void setUp() throws Exception {
        addHeaders();
    }

    @Test
    @DisplayName("Dado um json de produtos valido deve salvar um produto com suas características")
    void GIVEN_ValidPayload_MUST_ReturnOK() throws Exception {
        var payload = provideDataFile("src/test/resources/json/product.json", "product");

        mockMvc.perform(post(ENDPOINT)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk());

        List<Characteristic> all = characteristicRepository.findAll();

        assertFalse(all.isEmpty());
    }

    @Test
    @DisplayName("Dado um id valido deve retornar um produto especifico")
    void GIVEN_ValidID_MUST_ReturnProduct() throws Exception {
        long productId = 1;

        mockMvc.perform(get(ENDPOINT + "/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }
}