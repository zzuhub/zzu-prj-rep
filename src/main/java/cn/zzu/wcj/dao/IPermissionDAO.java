package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Permission;

public interface IPermissionDAO extends IBaseDAO<Integer, Permission> {
	/**
	 *  根据用户编号查询出用户权限名称的集合
	 * @param userName 用户编号
	 * @return 权限名称的集合
	 * @throws Exception 出异常了直接抛出
	 */
	List<String>  findByUserName(String userName)throws Exception;
	
	//查询全部权限
	List<Permission> findAll()throws Exception  ;

	
	
}
