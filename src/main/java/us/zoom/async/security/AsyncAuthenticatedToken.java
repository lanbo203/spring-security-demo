package us.zoom.async.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 17:11
 * @Description
 */
public class AsyncAuthenticatedToken extends AbstractAuthenticationToken  {

    private boolean auth;

    private String rawToken;

    public AsyncAuthenticatedToken( Collection<? extends GrantedAuthority> authorities, boolean auth,String rawToken) {
        super(authorities);
        super.setAuthenticated(true);
        this.auth = auth;
        this.rawToken = rawToken;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return rawToken;
    }

    @Override
    public Object getPrincipal() {
        return rawToken;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getRawToken() {
        return rawToken;
    }

    public void setRawToken(String rawToken) {
        this.rawToken = rawToken;
    }

}
