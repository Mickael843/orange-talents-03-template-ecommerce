package com.mikkaeru.ecommerce.controller.category;

import com.mikkaeru.ecommerce.helper.IntegrationHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest extends IntegrationHelper {

    private static final String ENDPOINT = "/categories";

    @BeforeEach
    void setup() throws Exception {
        addHeaders();
    }

    @Test
    @DisplayName("Dado um json de categoria valido deve retornar status code 200")
    void GIVEN_ValidPayload_MUST_ReturnOK() throws Exception {
        var name = faker.pokemon().name();
        long motherCategoryId = 1;

        JSONObject payload = new JSONObject().put("name", name).put("motherCategoryId", motherCategoryId);

        mockMvc.perform(post(ENDPOINT)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidData")
    @DisplayName("Dado um json de categoria invalido deve retornar status code 400")
    void GIVEN_ValidPayload_MUST_ReturnBadRequest(String name) throws Exception {
        JSONObject payload = new JSONObject().put("name", name);

        mockMvc.perform(post(ENDPOINT)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidData() {
        return Stream.of(
                arguments(" "),
                arguments("eletrodomésticos")
        );
    }
}