

1. providerManager遍历provider,查询匹配的provider,认证，认证通过则不会认证下一个了
2. 如果有多个，遍历执行，执行到最后一个filter器