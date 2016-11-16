package cn.zzu.wcj.service;

import java.util.List;

import cn.zzu.wcj.entity.Role;

public interface IRoleService extends IBaseService<Integer, Role> {
	   
	  /**
	   * 根据用户名查找全部角色名称
	   * @param userName 用户名
	   * @return 角色名称集合
	   * @throws Exception 异常直接抛出
	   */
	  List<String> findByUserName(String userName) throws Exception   ;
	
	  List<Role> findAll()throws Exception   ;    //查找全部角色,不用分页,因为角色不会太多
	  
	  Role findByRoleName(String roleName)throws Exception   ;
	  
}
