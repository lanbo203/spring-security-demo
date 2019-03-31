package us.zoom.async.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 14:55
 * @Description
 */
public class HitPathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher hitMatchers;

    public HitPathRequestMatcher(List<String> pathsToHit) {
        List<RequestMatcher> hits = new LinkedList<RequestMatcher>();
        if (null != pathsToHit && !pathsToHit.isEmpty()) {
            hits = pathsToHit.stream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        }
        hitMatchers = new OrRequestMatcher(hits);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return hitMatchers.matches(request) ? true : false;
    }

}
