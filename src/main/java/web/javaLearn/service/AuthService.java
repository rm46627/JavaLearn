package web.javaLearn.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.javaLearn.model.Key;
import web.javaLearn.model.User;
import web.javaLearn.dto.RegisterRequest;
import web.javaLearn.repository.KeyRepository;
import web.javaLearn.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final KeyRepository keyRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);

        userRepository.save(user);

        String key = generateVerificationKey(user);
    }

    private String generateVerificationKey(User user) {
        String key = UUID.randomUUID().toString();
        Key verificationKey = new Key();
        verificationKey.setKey(key);
        verificationKey.setUser(user);

        keyRepository.save(verificationKey);
        return key;
    }
}
