//package web.javaLearn.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
//public class JwtProviderTest {
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private JwtProvider jwtProvider;
//
//    @Before
//    public void setUp(){
//        this.mockMvc = MockMvcBuilders.standaloneSetup(jwtProvider).build();
//    }
    
//    @Test
//    @WithMockUser(username="user",password="psswd", authorities = {"ROLE_USER"})
//    public void generateToken_Success() throws Exception {
//        // Given
//        Authentication authentication = Mockito.mock(Authentication.class);
//        // Mockito.whens() for your authorization object
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        // call
//        String token = jwtProvider.generateToken(authentication);
//
//        // tests
//        Assert.assertNotNull(token);
//    }


//}
