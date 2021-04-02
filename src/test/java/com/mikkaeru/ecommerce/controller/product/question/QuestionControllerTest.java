package com.mikkaeru.ecommerce.controller.product.question;

import com.mikkaeru.ecommerce.helper.IntegrationHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuestionControllerTest extends IntegrationHelper {

    private static final String ENDPOINT = "/products/{id}/questions";

    @BeforeEach
    void setUp() throws Exception {
        addHeaders();
    }

    @Test
    @DisplayName("Dado um json de com perguntas validas deve retornar status code 200")
    void GIVEN_ValidPayload_MUST_ReturnOK() throws Exception {
        var productId = 1;
        var title = faker.book().title();

        JSONObject payload = new JSONObject().put("title", title);

        mockMvc.perform(post(ENDPOINT, productId)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Dado um json invalido deve retornar status code 400")
    void GIVEN_InvalidPayload_MUST_ReturnBadRequest() throws Exception {
        var productId = 1;
        var invalidTitle = "    ";

        JSONObject payload = new JSONObject().put("title", invalidTitle);

        mockMvc.perform(post(ENDPOINT, productId)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Dado o id de um produto inexistente deve retornar status code 404")
    void GIVEN_InvalidID_MUST_ReturnNotFound() throws Exception {
        var invalidId = 34567893;
        var title = faker.book().title();

        JSONObject payload = new JSONObject().put("title", title);

        mockMvc.perform(post(ENDPOINT, invalidId)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isNotFound());
    }
}