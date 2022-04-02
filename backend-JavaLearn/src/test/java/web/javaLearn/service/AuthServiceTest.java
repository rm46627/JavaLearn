package web.javaLearn.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.javaLearn.model.*;
import web.javaLearn.repository.TokenRepository;
import web.javaLearn.repository.UserRepository;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRepository userRepository;
    @Mock
    MailService mailService;
    @Mock
    TokenRepository tokenRepository;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtProvider jwtProvider;

    @InjectMocks
    private AuthService authService;

    // args
    RegisterRequest request = new RegisterRequest("quokka@gmail.com", "Quokka", "tajnaQuokka");
    String token = "token";
    User user = User.builder()
            .userId(null)
            .email(request.getEmail())
            .enabled(false)
            .password(request.getPassword())
            .username(request.getUsername())
            .build();

    @Test
    public void signup_Success(){
        // when
        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn(user.getPassword());

        // call
        User result = authService.signup(request);

        // tests
        verify(userRepository, times(1)).save(user);
        assertEquals(result, user);
    }

    @Test
    public void verifyAccount_Success() throws Exception {
        // args
        user.setEnabled(true);
        Token verificationToken = Token.builder()
                .token(token)
                .user(user)
                .build();

        // when
        Mockito.when(tokenRepository.findByToken(token)).thenReturn(java.util.Optional.ofNullable(verificationToken));
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.ofNullable(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        // call
        User result = authService.verifyAccount(token);

        // tests
        verify(userRepository, times(1)).save(user);
        assertEquals(result, user);
    }

    @Test
    public void login_Success() throws Exception {
        // args
        LoginRequest loginRequest = new LoginRequest("Quokka", "tajnaQuokka");

        // when
        Mockito.when(jwtProvider.generateToken(Mockito.any())).thenAnswer(i -> token);

        // call
        AuthenticationResponse response = authService.login(loginRequest);

        // tests
        assertEquals(response, new AuthenticationResponse(token, loginRequest.getUsername()));
    }
}
