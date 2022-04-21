package web.javaLearn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.Role;
import web.javaLearn.model.User;
import web.javaLearn.repository.TokenRepository;
import web.javaLearn.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    ObjectMapper objectMapper;

    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public Optional<User> getUserByUsername(String user) {
        return userRepository.findByUsername(user);
    }

    public boolean removeUser(Long id) {
        boolean isFound = userRepository.existsById(id);
        if(isFound){
            tokenRepository.deleteById(id);
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
