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
import java.util.Optional;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 14:41
 * @Description
 */
public class Token1AuthenticationProcessingFilter extends AsyncAbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;

    private final static String TOKEN = "token1";

    protected Token1AuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,AuthenticationFailureHandler failureHandler) {
        super(requiresAuthenticationRequestMatcher);
        this.failureHandler = failureHandler;
    }

    @Override
    public AbstractAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        return new Token1AuthenticationToken(getRawToken(request));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    private String getRawToken(HttpServletRequest request) {
        Optional<String> rawToken = Optional.ofNullable(request.getHeader(TOKEN));
        if (rawToken.isPresent()) {
            return rawToken.get();
        } else {
            return null;
        }

    }
}
