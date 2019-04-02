

1. providerManager遍历provider,查询匹配的provider,认证，认证通过则不会认证下一个了
2. 如果有多个，遍历执行，执行到最后一个filter器


HitPathRequestMatcher implements RequestMatcher


AbstractAuthenticationProcessingFilter#doFilter , filter 的顺序
SecurityContextHolder  SecurityContext context = SecurityContextHolder.getContext()
ProviderManager#authenticate, AuthenticationProvider#supports(Class<?> var1)



AuthenticationProcessingFilter 


AbstractSecurityInterceptor
AbstractSecurityInterceptor  FilterInvocationSecurityMetadataSource  获取所需权限  AccessDecisionManager  鉴权
访问url时，会被AbstractSecurityInterceptor拦截器拦截，
然后调用FilterInvocationSecurityMetadataSource的方法来获取被拦截url所需的全部权限，
再调用授权管理器AccessDecisionManager鉴权。

ProviderManager -> AuthenticationProviders
AccessDecisionManager -> AccessDecisionVoter -> 




DelegatingFilterProxy - FilterChainProxy - HttpFirewall

Core Security Filters

 AccessDeniedException  ExceptionTranslationFilter  AccessDeniedHandler
 在HttpSecurity对象中，实际提供的是各默认Filter的配置类，通过配置类来控制对应Filter的各个属性配置；
 在配置完成将Filter加载到HttpSecurity中的FilterChain中去。
 
 httpSecurity 将配置添加到 configurers 属性中去
 -- 将FormLoginConfigurer对象添加到HttpSecurity的configurers属性中去
 
 HttpSecurity最终会被加载到WebSecurity的securityFilterChainBuilders属性中去；
  添加进去后，在WebSecurity对象的build方法中，会调用HttpSecurity的build方法生成FilterChain对象
 HttpSecurity的build-> dobuild -> configure() , add filter -> performBuild 生成FilterChain
 
 
 webSecurity 生成filter chain
 WebSecurityConfiguration
 
 https://blog.csdn.net/icarusliu/article/details/78739006
 https://blog.csdn.net/icarusliu/article/details/78722384
