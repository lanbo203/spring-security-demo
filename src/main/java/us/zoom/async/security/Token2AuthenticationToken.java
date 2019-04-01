package us.zoom.async.security;

/**
 * @Author Eden Ye
 * @Date 2019/3/31 19:22
 * @Description
 */
public class Token2AuthenticationToken extends AbstractAsyncAuthenticationToken {

    public Token2AuthenticationToken(String rawToken){
        super(rawToken);
    }

}
