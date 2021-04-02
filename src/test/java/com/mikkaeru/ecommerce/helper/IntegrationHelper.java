package com.mikkaeru.ecommerce.helper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationHelper extends TestHelper {

    @Autowired
    protected MockMvc mockMvc;

    protected HttpHeaders headers = new HttpHeaders();

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

        String authorization = result.getResponse().getContentAsString()
                .split(":")[2]
                .replace("\"", "")
                .replace("}", "");;

        headers.add("Authorization", "Bearer " + authorization);
    }

    protected String provideDataFile(String pathName, String jsonObjectName) {
        JsonValue payload = null;

        File jsonInputFile = new File(pathName);
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(jsonInputFile);

            JsonReader reader = Json.createReader(inputStream);
            JsonObject jsonObject = reader.readObject();

            reader.close();

            payload = jsonObject.get(jsonObjectName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert payload != null;
        return payload.toString();
    }
}
