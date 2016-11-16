package cn.zzu.wcj.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zzu.wcj.entity.User;
import cn.zzu.wcj.service.IPermissionService;
import cn.zzu.wcj.service.IRoleService;
import cn.zzu.wcj.service.IUserService;

public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private IRoleService roleService   ;
	
	@Autowired
	private IPermissionService permissionService   ;
	
	@Autowired
	private IUserService userService   ;
	
	/**
	 * 验证权限时使用
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		String userName=(String)principals.getPrimaryPrincipal() ;    //得到用户名
		try{
			 info.addRoles(roleService.findByUserName(userName)); //根据用户名查找全部角色名称
             info.addStringPermissions(permissionService.findByUserName(userName));//根据用户名查找全部权限
		}catch(Exception e){
			 e.printStackTrace();
		}
		return info;
	}

	/**
	 * 用户登陆时使用
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();   //获取用户名
		AuthenticationInfo info=null   ;
		User user =null;
		try{
			user=this.userService.findByUserName(userName) ;   //根据用户名查找到用户
		}catch(Exception e){
			e.printStackTrace(); 
		}
		if(user !=null){
			info=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"MyShiroDBRealm"); 
		}
		return info;
	}

}
