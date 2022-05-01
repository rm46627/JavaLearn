package web.javaLearn.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTH_HEADER = "authorization";
    public static final String AUTH_TOKEN_HEADER = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_HEADER + " ";

    public static SimpleGrantedAuthority convertToAuthority(String role) {
        String roleWithPrefix = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(roleWithPrefix);
    }

    public static String extractAuthTokenFromRequest(HttpServletRequest request){
        String bearerToekn = request.getHeader(AUTH_HEADER);
        if(StringUtils.hasLength(bearerToekn) && bearerToekn.startsWith(AUTH_TOKEN_PREFIX)){
            return bearerToekn.substring(7);
        }
        return null;
    }
}
