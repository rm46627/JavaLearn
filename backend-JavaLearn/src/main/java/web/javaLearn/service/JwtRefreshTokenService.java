package web.javaLearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import web.javaLearn.model.auth.Token;
import web.javaLearn.model.auth.User;
import web.javaLearn.repository.TokenRepository;
import web.javaLearn.repository.UserRepository;
import web.javaLearn.security.UserPrincipal;
import web.javaLearn.security.jwt.JwtProvider;
import web.javaLearn.utils.SecurityUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@Service
public class JwtRefreshTokenService {

    @Value("${app.jwt.refresh-expiration-in-ms}")
    private Long REFRESH_EXPIRATION_IN_MS;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private TokenRepository tokenRepository;

    public Token generateRefreshToken(User user){
        Token token = new Token();
        token.setTokenId(UUID.randomUUID().toString());
        token.setUser(user);
        token.setCreateDate(LocalDateTime.now());
        token.setExpirationDate(LocalDateTime.now().plus(REFRESH_EXPIRATION_IN_MS, ChronoUnit.MILLIS));

        return tokenRepository.save(token);
    }

    public User generateAccesTokenFromRefreshToken(String refreshTokenId){
        Token token = tokenRepository.findById(refreshTokenId).orElseThrow();

        if(token.getExpirationDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("JWT refresh token is not valid");
        }

        User user = userRepository.findByUsername(token.getUser().getUsername()).orElseThrow();

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Set.of(SecurityUtils.convertToAuthority(user.getRole().name())))
                .build();

        String accessToken = jwtProvider.generateToken(userPrincipal);

        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshTokenId);

        return user;
    }
}
