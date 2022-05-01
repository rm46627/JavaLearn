package web.javaLearn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.auth.Role;
import web.javaLearn.model.auth.User;
import web.javaLearn.repository.TokenRepository;
import web.javaLearn.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    ObjectMapper objectMapper;

    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User getUserByUsername(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public boolean removeUser(Long id) {
        if(userRepository.findById(id).isPresent()){
            User user = userRepository.findById(id).orElseThrow();
            tokenRepository.deleteByUser(user);
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean changeRole(Role newRole, Long id){
        boolean isFound = userRepository.existsById(id);
        if(isFound){
            userRepository.updateUserRole(id, newRole);
            return true;
        }
        return false;
    }

}
