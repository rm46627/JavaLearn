//package web.javaLearn.repository;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import web.javaLearn.model.auth.User;
//import web.javaLearn.service.AuthService;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void existsById_Success(){
//        Long id = 11L;
//        boolean isFound = userRepository.existsById(id);
//        assertTrue(isFound);
//    }
//
//    @Test
//    public void findUserByUsername_Success(){
//        String username = "dziubasek";
//        Optional<User> isFound = userRepository.findByUsername(username);
//        assertEquals(isFound.get().getUsername(), username);
//    }
//}
