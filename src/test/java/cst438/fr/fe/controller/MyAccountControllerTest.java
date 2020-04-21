package cst438.fr.fe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.fr.fe.domain.User;
import cst438.fr.fe.domain.UserRepository;

@WebMvcTest(MyAccountController.class)
public class MyAccountControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }
    
    @Test
    public void processSignInTest() throws Exception {
        User user = new User();
        String userId = "not_found_user";
        given(userRepository.findByUserId(userId)).willReturn(user);
        
        MockHttpServletResponse response = mvc.perform(
                post("/users/signin").contentType(MediaType.TEXT_HTML)
                        .content("user"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("User not found");

    }
}
