package web.javaLearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.javaLearn.model.auth.*;
import web.javaLearn.repository.TokenRepository;
import web.javaLearn.repository.UserRepository;
import web.javaLearn.security.UserPrincipal;
import web.javaLearn.security.jwt.JwtProvider;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;

    @Value("${app.website-link}")
    private String WEBSITE_LINK;

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
            "Your activation link: " + WEBSITE_LINK + "/api/auth/account-verification/" + token.getTokenId()));

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