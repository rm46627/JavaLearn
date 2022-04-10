package web.javaLearn.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.User;
import web.javaLearn.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    // override metody która wczytuje usera - jeżeli go znajdzie to
    // wrapuje "naszego usera" w klasę usera ze springa i nadaje mu rolę 'USER'
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "found with username : " + username));
        if(!user.isAdmin()) {
            return new org.springframework.security.core
                    .userdetails.User(user.getUsername(), user.getPassword(),
                    user.isEnabled(), true, true,
                    true, getAuthorities("ROLE_USER"));
        }
        else {
            return new org.springframework.security.core
                    .userdetails.User(user.getUsername(), user.getPassword(),
                    user.isEnabled(), true, true,
                    true, getAuthorities("ROLE_ADMIN"));
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}