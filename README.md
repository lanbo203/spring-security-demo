

1. providerManager遍历provider,查询匹配的provider,认证，认证通过则不会认证下一个了
2. 如果有多个，遍历执行，执行到最后一个filter器


HitPathRequestMatcher implements RequestMatcher


AbstractAuthenticationProcessingFilter#doFilter , filter 的顺序
SecurityContextHolder  SecurityContext context = SecurityContextHolder.getContext()
ProviderManager#authenticate, AuthenticationProvider#supports(Class<?> var1)



1eD5OhenQY-3kzVNzJ-lHQ  2NAh5eAbS1S0HfFJSyBiLA