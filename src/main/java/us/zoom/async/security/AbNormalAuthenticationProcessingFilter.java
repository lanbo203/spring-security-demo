package us.zoom.async.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 17:34
 * @Description
 */
public class AbNormalAuthenticationProcessingFilter  extends AsyncAbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;

    @Autowired
    public AbNormalAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
        this.failureHandler = failureHandler;
    }

    @Override
    public AbstractAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        return null;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        SecurityContext context = SecurityContextHolder.getContext();
        return verifyToken(context);
    }

    private AsyncAuthenticatedToken verifyToken(SecurityContext context){
        AsyncAuthenticatedToken asyncAuthenticatedToken = null;
        if (null != context && null != context.getAuthentication()){
            asyncAuthenticatedToken =  (AsyncAuthenticatedToken)context.getAuthentication();
        }
        if(null == asyncAuthenticatedToken){
            throw new AuthenticationCredentialsNotFoundException("Can not find any auth parameters from request.");
        }
        if(asyncAuthenticatedToken.isAuth()){
            return asyncAuthenticatedToken;
        }

        throw new BadCredentialsException("The token parsed failed.");
    }
}
