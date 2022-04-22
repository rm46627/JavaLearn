package web.javaLearn.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import web.javaLearn.model.Role;

public class SecurityUtils {

    public static final String ROLE_PREFIX = "ROLE_";

    public static SimpleGrantedAuthority convertToAuthority(String role){
        String roleWithPrefix = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(roleWithPrefix);
    }
}
