package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.entity.RolePermissionRecord;



public interface IRoleDAO extends IBaseDAO<Integer, Role> {
	  
	  /**
	   * 根据用户名查找全部角色名称
	   * @param userName 用户名
	   * @return 角色名称集合
	   * @throws Exception 异常直接抛出
	   */
	  List<String> findByUserName(String userName) throws Exception   ;
	
	  /**
	   * 增加角色后增加中间表shiro_role_permission的记录
	   * @param rolePermissionRecords保存中间表数据
	   * @return 增加中间表的数据量 
	   * @throws Exception 有异常直接抛出
	   */
	  Integer doCreateAfter(List<RolePermissionRecord> rolePermissionRecords)throws Exception ;
	  
	  //查找本次对话中的序列值
	  Integer findCurrSeq()throws Exception   ;
	  
	  //在删除shiro_role之前,先将shiro_role_permission表中的数据清空
	  Integer doRemovePre(Integer roleId)throws Exception   ;
	  
	  //删除shiro_user_role表中的关联数据
	  Integer doRemoveUserRoleTabById(Integer id)throws Exception   ;
	  
	  //查询全部角色
	  List<Role> findAll()throws Exception ; 
	  
	  //根据名称查询角色
	  Role findByName(String roleName)throws Exception   ;
	  
}
