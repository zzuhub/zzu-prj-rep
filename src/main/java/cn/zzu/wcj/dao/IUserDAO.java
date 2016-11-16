package cn.zzu.wcj.dao;

import java.util.List;



import cn.zzu.wcj.entity.User;
import cn.zzu.wcj.entity.UserRoleRecord;

public interface IUserDAO extends IBaseDAO<Integer, User> {
	
	//根据用户名(UNIQUE)和密码去更新最后一次登陆的时间
	Integer doUpdateLastLogin(User user)throws Exception ;
	
	//向中间表shiro_user_role增加记录
	Integer doCreateAfter(List<UserRoleRecord> userRoleRecords)throws Exception ;
	
	//删除用户时先删除中间表的记录
	Integer doRemovePre(Integer userId)throws Exception ;   
	
	//向中间表增加数据之前,先将本次会话序列的当前值取出来(userId)
	Integer findSeqCurrVal()throws Exception ;
	
	//根据用户名查询用户
	User findByUserName(String userName)throws Exception  ;
	
	//查询全部用户
	List<User> findAll()throws Exception  ;
	
	
	
	
}
