package cn.zzu.wcj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zzu.wcj.dao.IRoleDAO;
import cn.zzu.wcj.entity.Permission;
import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.entity.RolePermissionRecord;
import cn.zzu.wcj.service.IRoleService;

@Transactional
@Service
public class RoleServiceImpl implements IRoleService {
    

	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private IRoleDAO roleDAO   ;
	
	public Integer doCreate(Role entity) throws Exception {
		Integer count=0   ;    
		count=roleDAO.doCreate(entity)  ;  //1.先增加角色表shiro_role
		Integer roleId=this.roleDAO.findCurrSeq()  ;   //2.取得序列的值即为role_id
		List<RolePermissionRecord> rolePermissionRecords=null ;
		rolePermissionRecords=new ArrayList<RolePermissionRecord>()  ;  //3.创建中间表保存集合
		for(Permission permission : entity.getPermissions()){
			 Integer permissionId = permission.getPermissionId() ;     //获取权限编号
			 RolePermissionRecord rolePermissionRecord=null ;
			 rolePermissionRecord=new RolePermissionRecord(roleId, permissionId) ;//实例化中间表对象
			 rolePermissionRecords.add(rolePermissionRecord) ;  //集合增加数据
		}
		if(rolePermissionRecords.size()!=0){
			count=count+this.roleDAO.doCreateAfter(rolePermissionRecords) ;  //向shiro_role_permission增加数据
		}
		return count;
	}

	public Integer doUpdate(Role entity) throws Exception {
		Integer count= 0 ;
		count=this.roleDAO.doUpdate(entity)   ;   //1.先修改shiro_role数据表
		count=count+this.roleDAO.doRemovePre(entity.getRoleId()) ;  //2.删除shiro_role_permission中的数据
		Integer roleId=entity.getRoleId()   ;   //3.取得角色的ID
		List<RolePermissionRecord> rolePermissionRecords=null ;
		rolePermissionRecords=new ArrayList<RolePermissionRecord>()  ;  //4.创建中间表保存集合
		for(Permission permission : entity.getPermissions()){
			 Integer permissionId = permission.getPermissionId() ;     //获取权限编号
			 RolePermissionRecord rolePermissionRecord=null ;
			 rolePermissionRecord=new RolePermissionRecord(roleId, permissionId) ;//实例化中间表对象
			 rolePermissionRecords.add(rolePermissionRecord) ;  //集合增加数据
		}
		if(rolePermissionRecords.size()!=0){
			count=count+this.roleDAO.doCreateAfter(rolePermissionRecords) ;  //向shiro_role_permission增加数据
		}
		return count;
	}

	public Integer doRemove(Integer id) throws Exception {
		Integer count=0 ;
		count=this.roleDAO.doRemoveUserRoleTabById(id) ;//1.删除shiro_user_role表中关联数据
	    count=count+this.roleDAO.doRemovePre(id) ; //2.再删除中间表shiro_role_permission
		count=count+roleDAO.doRemove(id)  ;   //3.最后删除shiro_role表中的数据
		return count;
	}

	public Role findById(Integer id) throws Exception {
		Role role=null ;
		role=roleDAO.findById(id)  ;
		return role;
	}

	public List<String> findByUserName(String userName) throws Exception {
		List<String> roles=null ;
		roles=roleDAO.findByUserName(userName)  ;
		return roles;
	}

	public List<Role> findAll() throws Exception {
		List<Role> roles=new ArrayList<Role>()  ;  //全部角色
		roles=this.roleDAO.findAll()   ;
		return roles;
	}

	public Role findByRoleName(String roleName) throws Exception {
		Role role=this.roleDAO.findByName(roleName);
		return role;
	}
	
	

}
