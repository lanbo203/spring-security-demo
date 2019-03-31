package us.zoom.async.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 17:14
 * @Description
 */
public class UserToken {

    private String userId;

    private List<GrantedAuthority> authorities;

}
