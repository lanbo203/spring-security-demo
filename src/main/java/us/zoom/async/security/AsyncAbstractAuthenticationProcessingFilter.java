package us.zoom.async.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 14:39
 * @Description
 */
public abstract class AsyncAbstractAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter{


    @Autowired
    protected AsyncAbstractAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        AbstractAuthenticationToken authenticationToken = getAuthenticationToken(request);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    public abstract AbstractAuthenticationToken getAuthenticationToken(HttpServletRequest request);

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication result) throws IOException, ServletException {
        if (result != null) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(result);
            SecurityContextHolder.setContext(context);
        }
        chain.doFilter(request, response);
    }
}
