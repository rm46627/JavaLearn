package web.javaLearn.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.javaLearn.model.Email;
import web.javaLearn.model.Token;
import web.javaLearn.model.User;
import web.javaLearn.dto.RegisterRequest;
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

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);

        userRepository.save(user);

        String key = generateVerificationToken(user);
        mailService.sendMail(new Email("Java Learn Activation Email",
                user.getEmail(),
                "Your activation link:" +
                "http://localhost:8080/api/auth/accountVerification/" + key));
    }

    private String generateVerificationToken(User user) {
        String key = UUID.randomUUID().toString();
        Token verificationToken = new Token();
        verificationToken.setToken(key);
        verificationToken.setUser(user);

        tokenRepository.save(verificationToken);
        return key;
    }

    @SneakyThrows
    public void verifyAccount(String token) {
        Optional<Token> verificationToken = tokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new Exception("Invalid Token")));
    }

    @SneakyThrows
    private void fetchUserAndEnable(Token token) {
        String username = token.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found with name: " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
