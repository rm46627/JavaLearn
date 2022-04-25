package web.javaLearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.javaLearn.model.User;
import web.javaLearn.repository.UserRepository;
import web.javaLearn.security.UserPrincipal;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getInfo(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }
}
