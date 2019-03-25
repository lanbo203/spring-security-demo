package us.zoom.async.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @Author Eden Ye
 * @Date 2019/3/25 17:13
 * @Description
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    public void configure(WebSecurity security) throws Exception {
        security.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/js/**",
                "/css/**",
                "**.png",
                "**.ico",
                "/css/**",
                "/webjars/**",
                "/druid/**"
        );
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().
                anyRequest().authenticated();
//                .addFilterAfter(buildAbNormalAuthenticationProcessingFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(buildUserTokenAuthenticationProcessingFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(buildClientTokenAuthenticationProcessingFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(buildZAKTokenAuthenticationProcessingFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(buildOauthTokenProcessingFilter(),
//                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //List<AuthenticationProvider> providers = getAuthenticationProviders();
        //        for (AuthenticationProvider provider : providers) {
        //            auth.authenticationProvider(provider);
        //        }
    }

}
