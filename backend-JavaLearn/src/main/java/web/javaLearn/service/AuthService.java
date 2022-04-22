package web.javaLearn.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.javaLearn.model.*;
import web.javaLearn.repository.TokenRepository;
import web.javaLearn.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    //
    // REGISTRATION
    //
    @Transactional
    public User signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);
        user.setRole(Role.USER);
        userRepository.save(user);

        String key = generateVerificationToken(user);

        mailService.sendMail(new ActivationEmail(
            "Java Learn Activation Email",
            user.getEmail(),
            "Your activation link:" + "http://localhost:8080/api/auth/accountVerification/" + key));

        return user;
    }

    String generateVerificationToken(User user) {
        String key = UUID.randomUUID().toString();
        Token verificationToken = new Token();
        verificationToken.setToken(key);
        verificationToken.setUser(user);
        tokenRepository.save(verificationToken);
        return key;
    }

    //
    // VERIFICATION AFTER REGISTRATION
    //
    public User verifyAccount(String token) throws Exception {
        Optional<Token> verificationToken = tokenRepository.findByToken(token);
        return fetchUserAndEnable(verificationToken.orElseThrow(() -> new Exception("Invalid Token")));
    }

    private User fetchUserAndEnable(Token token) throws Exception {
        String username = token.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found with name: " + username));
        user.setEnabled(true);
        return userRepository.save(user);
    }


    //
    // LOGIN
    //
    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
        // AM komunikuje się z userDetailsService
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        boolean admin = false;
        if(authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))) {
            admin = true;
        }

        return new AuthenticationResponse(token, loginRequest.getUsername(), admin);
    }
}