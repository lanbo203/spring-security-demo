package us.zoom.async.security.provider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import us.zoom.async.security.AsyncAuthenticatedToken;
import us.zoom.async.security.Token1AuthenticationToken;

import java.util.ArrayList;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 14:06
 * @Description
 */
@Component
public class Token1AuthenticationProvider implements CustomAuthenticationProvider {

    private final String TOKEN = "token1";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //authentication
        String token = (String)authentication.getPrincipal();
        if (!StringUtils.isEmpty(token) && token.equalsIgnoreCase(TOKEN)) {
            return new AsyncAuthenticatedToken(null,new ArrayList<GrantedAuthority>(),true,null);
        }else {
            return new AsyncAuthenticatedToken(null,new ArrayList<GrantedAuthority>(),false,null);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (Token1AuthenticationToken.class.isAssignableFrom(aClass));
    }
}
