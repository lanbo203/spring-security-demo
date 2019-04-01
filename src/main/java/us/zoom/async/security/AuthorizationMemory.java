package us.zoom.async.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Eden Ye
 * @Date 2019/4/1 15:39
 * @Description
 */
public class AuthorizationMemory {


    private static Map<String,List<SimpleGrantedAuthority>> GrantedAuthorities = new HashMap<>();

    public static Map<String, List<SimpleGrantedAuthority>> getGrantedAuthorities() {
        return GrantedAuthorities;
    }

    public static void addGrantedAuthorities(Map<String, List<SimpleGrantedAuthority>> grantedAuthorities) {
        GrantedAuthorities.putAll(grantedAuthorities);
    }
}
