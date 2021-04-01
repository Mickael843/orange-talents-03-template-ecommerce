package com.mikkaeru.ecommerce.controller.user;

import com.mikkaeru.ecommerce.helper.IntegrationHelper;
import org.json.JSONObject;
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

class UserControllerTest extends IntegrationHelper {

    private static final String ENDPOINT = "/users";

    @Test
    @DisplayName("Ao fornecer um json valido deve retornar status code 200")
    void GIVEN_ValidPayload_MUST_ReturnOK() throws Exception {
        var login = faker.pokemon().name() + "@gmail.com";
        var password = "123456789";

        JSONObject payload = new JSONObject()
                .put("login", login)
                .put("password", password);

        mockMvc.perform(post(ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidData")
    @DisplayName("Ao fornecer um json invalido deve retornar status code 400")
    void GIVEN_InvalidPayload_MUST_ReturnBadRequest(String login, String password) throws Exception {
        JSONObject payload = new JSONObject()
                .put("login", login)
                .put("password", password);

        mockMvc.perform(post(ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidData() {
        var validLogin = faker.pokemon().name() + "@gmail.com";
        var validPassword = "123456789";

        return Stream.of(
                arguments("isso não é email", validPassword),
                arguments(" ", validPassword),
                arguments(validLogin, " "),
                arguments(validLogin, "1234")
        );
    }
}

