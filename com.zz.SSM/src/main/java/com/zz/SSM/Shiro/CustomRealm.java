package com.zz.SSM.Shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {
	
	 /** 
     * 登陆认证
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
    	//令牌——基于用户名和密码的令牌    
    	UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
    	//让shiro框架去验证账号密码  
    	return new SimpleAuthenticationInfo(token.getUsername(), String.valueOf(token.getPassword()), getName());
    } 
    
    /**
     * 获取授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	    return null;    
    }  
		
    /**
     * 清除缓存  
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();  
        super.clearCache(principals);  
    }  
}