package us.zoom.async.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 19:22
 * @Description
 */
public class Token2AuthenticationProcessingFilter extends AsyncAbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;

    protected Token2AuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationFailureHandler failureHandler) {
        super(requiresAuthenticationRequestMatcher);
        this.failureHandler = failureHandler;
    }

    @Override
    public AbstractAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        return new Token2AuthenticationToken();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
