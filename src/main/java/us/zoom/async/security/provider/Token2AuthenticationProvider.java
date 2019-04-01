package us.zoom.async.security.provider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import us.zoom.async.security.AsyncAuthenticatedToken;
import us.zoom.async.security.AuthorizationMemory;
import us.zoom.async.security.Token1AuthenticationToken;
import us.zoom.async.security.Token2AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 19:21
 * @Description
 */
@Component
public class Token2AuthenticationProvider implements CustomAuthenticationProvider {

    private final static String TOKEN = "token2";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //authentication
        String token = (String) authentication.getPrincipal();
        if (!StringUtils.isEmpty(token) && token.startsWith(TOKEN)) { // 认证过程
            String[] split = token.split("\\|");
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = AuthorizationMemory.getGrantedAuthorities().get(split[1]);
            return new AsyncAuthenticatedToken(simpleGrantedAuthorities,true,null);
        } else {
            return new AsyncAuthenticatedToken(new ArrayList<GrantedAuthority>(), false, null);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (Token2AuthenticationToken.class.isAssignableFrom(aClass));
    }

}
