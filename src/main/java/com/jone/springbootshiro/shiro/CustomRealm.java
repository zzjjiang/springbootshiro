package com.jone.springbootshiro.shiro;

import com.jone.springbootshiro.model.User;
import com.jone.springbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jone
 * @version 1.0.0
 * @Description 自定义Realm
 * @createTime 2020年09月13日 21:03
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
//        info.addStringPermission("user:add");  该方法为硬编码模式
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User user1 = userService.findById(user.getId());
        info.addStringPermission(user1.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从数据库中找
        //执行认证逻辑
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findByName(username);
        //1 判断用户名
        if(user == null){
            //为NULL时，shiro底层会抛出unkownAccountException
            return null;
        }
        //2 判断密码  该密码为真实密码，非用户输入密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
