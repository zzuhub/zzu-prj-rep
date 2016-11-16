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
	 * ��֤Ȩ��ʱʹ��
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		String userName=(String)principals.getPrimaryPrincipal() ;    //�õ��û���
		try{
			 info.addRoles(roleService.findByUserName(userName)); //�����û�������ȫ����ɫ����
             info.addStringPermissions(permissionService.findByUserName(userName));//�����û�������ȫ��Ȩ��
		}catch(Exception e){
			 e.printStackTrace();
		}
		return info;
	}

	/**
	 * �û���½ʱʹ��
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();   //��ȡ�û���
		AuthenticationInfo info=null   ;
		User user =null;
		try{
			user=this.userService.findByUserName(userName) ;   //�����û������ҵ��û�
		}catch(Exception e){
			e.printStackTrace(); 
		}
		if(user !=null){
			info=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"MyShiroDBRealm"); 
		}
		return info;
	}

}
