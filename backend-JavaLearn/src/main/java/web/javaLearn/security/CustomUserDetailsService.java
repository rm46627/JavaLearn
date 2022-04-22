package web.javaLearn.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.javaLearn.model.Role;
import web.javaLearn.model.User;
import web.javaLearn.repository.UserRepository;
import web.javaLearn.service.AuthService;
import web.javaLearn.utils.SecurityUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singletonList;

public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(
                "No user found with username : " + username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();

//        if(user.getRole()==Role.ADMIN) {
//            return new org.springframework.security.core
//                    .userdetails.User(user.getUsername(), user.getPassword(),
//                    user.isEnabled(), true, true,
//                    true, getAuthorities("ROLE_USER"));
//        }
//        else {
//            return new org.springframework.security.core
//                    .userdetails.User(user.getUsername(), user.getPassword(),
//                    user.isEnabled(), true, true,
//                    true, getAuthorities("ROLE_ADMIN"));
//        }
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
//        return singletonList(new SimpleGrantedAuthority(role));
//    }
}
