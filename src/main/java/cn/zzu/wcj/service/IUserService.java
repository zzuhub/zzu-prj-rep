package cn.zzu.wcj.service;


import java.util.List;

import cn.zzu.wcj.entity.User;

public interface IUserService extends IBaseService<Integer, User>{
	
	
	//根据用户名(UNIQUE)和密码去更新最后一次登陆的时间,由影响行数去判定用户是否存在
	Integer doUpdateLastLogin(User user)throws Exception ;
	
	
	//根据用户名查找到用户
	User findByUserName(String userName) throws Exception   ;
	
	//查询全部用户
	List<User> findAll()throws Exception  ;
	
}
