package com.mikkaeru.ecommerce.controller.product.opnion;

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

class OpinionControllerTest extends IntegrationHelper {

    private static final String ENDPOINT = "/products/{id}/opinions";

    @BeforeEach
    void setUp() throws Exception {
        addHeaders();
    }

    @Test
    @DisplayName("Dado um json valido deve retornar status code 200")
    void GIVEN_ValidPayload_MUST_ReturnOK() throws Exception {
        var productId = 1;
        var title = faker.book().title();
        var rating = faker.number().numberBetween(1, 5);
        var description = faker.lorem().fixedString(500);

        JSONObject payload = new JSONObject()
                .put("title", title)
                .put("rating", rating)
                .put("description", description);

        mockMvc.perform(post(ENDPOINT, productId)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidData")
    @DisplayName("Dado um json invalido deve retornar status code 400")
    void GIVEN_InvalidPayload_MUST_ReturnBadRequest(String title, int rating, String description) throws Exception {
        var productId = 1;

        JSONObject payload = new JSONObject()
                .put("title", title)
                .put("rating", rating)
                .put("description", description);

        mockMvc.perform(post(ENDPOINT, productId)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidData() {
        var validTitle = faker.book().title();
        var validRating = faker.number().numberBetween(1, 5);
        var validDescription = faker.lorem().fixedString(500);

        return Stream.of(
                arguments("   ", validRating, validDescription),
                arguments(validTitle, 0, validDescription),
                arguments(validTitle, 6, validDescription),
                arguments(validTitle, validRating, "   "),
                arguments(validTitle, validRating, faker.lorem().fixedString(600))
        );
    }
}