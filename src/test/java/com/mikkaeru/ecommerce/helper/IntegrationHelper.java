package com.mikkaeru.ecommerce.helper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationHelper extends TestHelper {

    @Autowired
    protected MockMvc mockMvc;

    private final HttpHeaders headers = new HttpHeaders();

    protected void addHeaders() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("login", "teste002@gmail.com");
        payload.put("password", "123456789");

        headers.add("Origin", "*");

        var result = mockMvc.perform(post("/auth")
                .headers(headers)
                .contentType(APPLICATION_JSON)
                .content(payload.toString()))
                .andExpect(status().isOk())
                .andReturn();

        headers.add("Authorization", result.getResponse().getHeader("Authorization"));
    }
}
