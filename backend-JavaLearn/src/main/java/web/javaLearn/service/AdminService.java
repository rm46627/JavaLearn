package web.javaLearn.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import web.javaLearn.model.User;
import web.javaLearn.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<User> getAllUserRecords(){
        return userRepository.findAll();
    }

    public Optional<User> getUserRecord(String user) {
        return userRepository.findByUsername(user);
    }

    public boolean removeUser(Long id) {
        boolean isFound = userRepository.existsById(id);
        if(isFound){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
