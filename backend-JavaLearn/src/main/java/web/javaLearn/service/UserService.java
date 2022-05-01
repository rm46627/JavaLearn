package web.javaLearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.auth.LoginRequest;
import web.javaLearn.model.auth.User;
import web.javaLearn.repository.TokenRepository;
import web.javaLearn.repository.UserRepository;
import web.javaLearn.security.UserPrincipal;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenRepository tokenRepository;

    public User getInfo(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    public boolean removeUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        user = userRepository.findById(user.getId()).orElseThrow();
        tokenRepository.deleteByUser(user);
        userRepository.deleteById(user.getId());
        return true;
    }
}
