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
		count=roleDAO.doCreate(entity)  ;  //1.�����ӽ�ɫ��shiro_role
		Integer roleId=this.roleDAO.findCurrSeq()  ;   //2.ȡ�����е�ֵ��Ϊrole_id
		List<RolePermissionRecord> rolePermissionRecords=null ;
		rolePermissionRecords=new ArrayList<RolePermissionRecord>()  ;  //3.�����м���漯��
		for(Permission permission : entity.getPermissions()){
			 Integer permissionId = permission.getPermissionId() ;     //��ȡȨ�ޱ��
			 RolePermissionRecord rolePermissionRecord=null ;
			 rolePermissionRecord=new RolePermissionRecord(roleId, permissionId) ;//ʵ�����м�����
			 rolePermissionRecords.add(rolePermissionRecord) ;  //������������
		}
		if(rolePermissionRecords.size()!=0){
			count=count+this.roleDAO.doCreateAfter(rolePermissionRecords) ;  //��shiro_role_permission��������
		}
		return count;
	}

	public Integer doUpdate(Role entity) throws Exception {
		Integer count= 0 ;
		count=this.roleDAO.doUpdate(entity)   ;   //1.���޸�shiro_role���ݱ�
		count=count+this.roleDAO.doRemovePre(entity.getRoleId()) ;  //2.ɾ��shiro_role_permission�е�����
		Integer roleId=entity.getRoleId()   ;   //3.ȡ�ý�ɫ��ID
		List<RolePermissionRecord> rolePermissionRecords=null ;
		rolePermissionRecords=new ArrayList<RolePermissionRecord>()  ;  //4.�����м���漯��
		for(Permission permission : entity.getPermissions()){
			 Integer permissionId = permission.getPermissionId() ;     //��ȡȨ�ޱ��
			 RolePermissionRecord rolePermissionRecord=null ;
			 rolePermissionRecord=new RolePermissionRecord(roleId, permissionId) ;//ʵ�����м�����
			 rolePermissionRecords.add(rolePermissionRecord) ;  //������������
		}
		if(rolePermissionRecords.size()!=0){
			count=count+this.roleDAO.doCreateAfter(rolePermissionRecords) ;  //��shiro_role_permission��������
		}
		return count;
	}

	public Integer doRemove(Integer id) throws Exception {
		Integer count=0 ;
		count=this.roleDAO.doRemoveUserRoleTabById(id) ;//1.ɾ��shiro_user_role���й�������
	    count=count+this.roleDAO.doRemovePre(id) ; //2.��ɾ���м��shiro_role_permission
		count=count+roleDAO.doRemove(id)  ;   //3.���ɾ��shiro_role���е�����
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
		List<Role> roles=new ArrayList<Role>()  ;  //ȫ����ɫ
		roles=this.roleDAO.findAll()   ;
		return roles;
	}

	public Role findByRoleName(String roleName) throws Exception {
		Role role=this.roleDAO.findByName(roleName);
		return role;
	}
	
	

}
