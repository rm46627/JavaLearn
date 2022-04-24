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
import web.javaLearn.security.UserPrincipal;
import web.javaLearn.security.jwt.JwtProvider;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final JwtRefreshTokenService jwtRefreshTokenService;

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
        user.setCreateTime(LocalDateTime.now());
        userRepository.save(user);
        Token token = jwtRefreshTokenService.generateRefreshToken(user);

        mailService.sendMail(new ActivationEmail(
            "Java Learn Activation Email",
            user.getEmail(),
            "Your activation link:" + "http://localhost:8080/api/auth/accountVerification/" + token.getTokenId()));

        return user;
    }

    //
    // VERIFICATION AFTER REGISTRATION
    //
    public boolean verifyAccount(String token) {
        if(tokenRepository.findById(token).isPresent()){
            User user = tokenRepository.findById(token).orElseThrow().getUser();
            user.setEnabled(true);
            userRepository.save(user);
            tokenRepository.deleteById(token);
            return true;
        }
        return false;
    }


    //
    // LOGIN
    //
    public User login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userPrincipal);

        User user = userPrincipal.getUser();
        user.setAccessToken(token);
        user.setRefreshToken(jwtRefreshTokenService.generateRefreshToken(user).getTokenId());

        return user;
    }
}