package com.mikkaeru.ecommerce.controller.buy;

import com.mikkaeru.ecommerce.helper.IntegrationHelper;
import com.mikkaeru.ecommerce.model.buy.PaymentGateway;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuyControllerTest extends IntegrationHelper {

    private static final String ENDPOINT = "/buy";

    @BeforeEach
    void setUp() throws Exception {
        addHeaders();
    }

    @Test
    @DisplayName("Quando os dados enviados são validos deve retornar status code 200 e a url de redirecionamento")
    void GIVEN_ValidPayload_MUST_ReturnOK() throws Exception {
        var productId = 1L;
        var amount = 9;
        var gateway = PaymentGateway.pagseguro;

        JSONObject payload = new JSONObject()
                .put("productId", productId)
                .put("amount", amount)
                .put("gateway", gateway);

        MockHttpServletResponse response = mockMvc.perform(post(ENDPOINT)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String url = response.getContentAsString();

        assertFalse(url.isEmpty());
    }

    @Test
    @DisplayName("Ao enviar o id de um produto que não existe deve retornar status code 404")
    void GIVEN_NonexistentProduct_MUST_ReturnNotFound() throws Exception {
        var productId = 100L;
        var amount = 10;
        var gateway = PaymentGateway.paypal;

        JSONObject payload = new JSONObject()
                .put("productId", productId)
                .put("amount", amount)
                .put("gateway", gateway);

        mockMvc.perform(post(ENDPOINT)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidData")
    @DisplayName("Ao enviar um json invalido deve retornar status code 400")
    void GIVEN_InvalidPayload_MUST_ReturnBadRequest(Long productId, Integer amount, String gateway) throws Exception {
        JSONObject payload = new JSONObject()
                .put("productId", productId)
                .put("amount", amount)
                .put("gateway", gateway);

        mockMvc.perform(post(ENDPOINT)
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidData() {
        var validProductId = 1L;
        var validAmount = 10;
        var validGateway = "paypal";

        return Stream.of(
                arguments(null, validAmount, validGateway),
                arguments(validProductId, null, validGateway),
                arguments(validProductId, -1, validGateway),
                arguments(validProductId, 0, validGateway),
                arguments(validProductId, 9999, validGateway),
                arguments(validProductId, validAmount, "  "),
                arguments(validProductId, validAmount, null)
        );
    }
}