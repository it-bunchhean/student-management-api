package com.it_bunchhean.studentmanagement.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.it_bunchhean.studentmanagement.data.TestData.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    void registerLoginAndAccessProtectedEndpoint() throws Exception {

        //  regsiter
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(regsiterJson()))
                .andExpect(status().isCreated());

        // login
        String body = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        String token = objectMapper.readTree(body).get("accessToken").asText();

        mockMvc.perform(get("/api/v1/users/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    void protectEndPoint_withoutToken_returns401() throws Exception {
        mockMvc.perform(get("/api/v1/users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminEndPoint_asNormalUser_returns403() throws Exception {
        mockMvc.perform(get("/api/v1/users")
                .with(user(EMAIL).roles("USER")))
                .andExpect(status().isForbidden());
    }
}
