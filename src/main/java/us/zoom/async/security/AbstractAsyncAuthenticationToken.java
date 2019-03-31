package us.zoom.async.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.LinkedList;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 17:31
 * @Description
 */
public abstract class AbstractAsyncAuthenticationToken extends AbstractAuthenticationToken {

    private String rawToken;

    public AbstractAsyncAuthenticationToken(String rawToken){
        super(new LinkedList<GrantedAuthority>());
        this.eraseCredentials();
        this.rawToken = rawToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
