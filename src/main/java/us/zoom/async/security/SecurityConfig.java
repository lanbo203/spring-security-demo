package us.zoom.async.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @Author Eden Ye
 * @Date 2019/3/25 17:13
 * @Description
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

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
//        http.cors().and().csrf().disable();
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/","/test","/user/login").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .logout().logoutUrl("/logout").permitAll();

//                .and()
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
//        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
        ;
//                .;
//                anyRequest().authenticated();
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

        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and()
                .withUser("admin").password("admin").roles("ADMIN");
        //List<AuthenticationProvider> providers = getAuthenticationProviders();
        //        for (AuthenticationProvider provider : providers) {
        //            auth.authenticationProvider(provider);
        //        }
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
