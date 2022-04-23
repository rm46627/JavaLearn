//package web.javaLearn.service;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.security.core.userdetails.UserDetails;
//import web.javaLearn.model.User;
//import web.javaLearn.repository.UserRepository;
//import web.javaLearn.security.CustomUserDetailsService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CustomUserDetailsServiceImplTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    private CustomUserDetailsService userDetailsService;
//
//    @Test
//    public void loadUserByUsername_Success(){
//        // given
//        User user = User.builder()
//                .userId(null)
//                .email("quokka@gmail.com")
//                .enabled(true)
//                .password("psswd")
//                .username("Quokka")
//                .build();
//
//        // when
//        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.ofNullable(user));
//
//        // call
//        UserDetails result = userDetailsService.loadUserByUsername(user.getUsername());
//
//        // test
//        Assert.assertEquals(result.getUsername(), user.getUsername());
//    }
//}
