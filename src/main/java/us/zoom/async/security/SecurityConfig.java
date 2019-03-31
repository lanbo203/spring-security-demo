package us.zoom.async.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import us.zoom.async.security.provider.CustomAuthenticationProvider;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Eden Ye
 * @Date 2019/3/25 17:13
 * @Description
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private List<CustomAuthenticationProvider> customAuthenticationProviders;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("defaultAuthenticationFailureHandler")
    private AuthenticationFailureHandler defaultFailureHandler;

    @Override
    public void configure(WebSecurity security) throws Exception {
        security.ignoring().antMatchers(
                "/js/**",
                "/css/**",
                "**.png",
                "**.ico",
                "/css/**"
        );
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/","/test","/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(buildAbNormalAuthenticationProcessingFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildToken1AuthenticationProcessingFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildToken2AuthenticationProcessingFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and()
//                .withUser("admin").password("admin").roles("ADMIN");
        List<AuthenticationProvider> providers = getAuthenticationProviders();
        for (AuthenticationProvider provider : providers) {
            auth.authenticationProvider(provider);
        }
    }

    private List<AuthenticationProvider> getAuthenticationProviders() {
        List<AuthenticationProvider> providers = new LinkedList<AuthenticationProvider>();
        if (null != customAuthenticationProviders) {
            for (Object customAuthenticationProviderObj : customAuthenticationProviders) {
                providers.add((CustomAuthenticationProvider) customAuthenticationProviderObj);
            }
        }
        return providers;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private Token1AuthenticationProcessingFilter buildToken1AuthenticationProcessingFilter() throws Exception  {
        HitPathRequestMatcher matcher = new HitPathRequestMatcher(Arrays.asList("/token1/test"));
        Token1AuthenticationProcessingFilter filter = new Token1AuthenticationProcessingFilter(matcher,defaultFailureHandler);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    private Token2AuthenticationProcessingFilter buildToken2AuthenticationProcessingFilter() throws Exception  {
        HitPathRequestMatcher matcher = new HitPathRequestMatcher(Arrays.asList("/token2/test"));
        Token2AuthenticationProcessingFilter filter = new Token2AuthenticationProcessingFilter(matcher,defaultFailureHandler);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    private AbNormalAuthenticationProcessingFilter buildAbNormalAuthenticationProcessingFilter() throws Exception {
        HitPathRequestMatcher matcher = new HitPathRequestMatcher(Arrays.asList("/**"));
        AbNormalAuthenticationProcessingFilter filter = new AbNormalAuthenticationProcessingFilter(defaultFailureHandler,matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
