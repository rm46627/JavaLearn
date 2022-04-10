package web.javaLearn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.javaLearn.model.AuthenticationResponse;
import web.javaLearn.model.LoginRequest;
import web.javaLearn.model.RegisterRequest;
import web.javaLearn.service.AuthService;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Mock
    AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void registerNewUser_success() throws Exception {
        RegisterRequest request = new RegisterRequest("quokka@gmail.com", "Quokka", "tajnaQuokka");
        ResponseEntity response = new ResponseEntity<>("User registration successful", HttpStatus.OK);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), response.getBody().toString());
    }

    @Test
    public void verifyAccount_success() throws Exception {
        String token = "someToken";
        ResponseEntity response = new ResponseEntity<>("Account activated successfully", HttpStatus.OK);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/accountVerification/" + token))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), response.getBody().toString());
    }

    @Test
    public void loginRequest_success() throws Exception {
        LoginRequest request = new LoginRequest("Quokka", "tajnaQuokka");
        AuthenticationResponse response = new AuthenticationResponse("someToken", request.getUsername(), false);

        Mockito.when(authService.login(request)).thenReturn(response);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), toJson(response));
    }
}
