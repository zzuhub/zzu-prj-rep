package cn.zzu.wcj.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zzu.wcj.dao.IUserDAO;
import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.entity.User;
import cn.zzu.wcj.entity.UserRoleRecord;
import cn.zzu.wcj.service.IUserService;
import cn.zzu.wcj.util.EncryptUtils;

@Transactional
@Service
public class UserServiceImpl implements IUserService {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUserDAO userDAO  ;
	
	@Autowired
	private EncryptUtils encryptUtils  ;
	

	public Integer doCreate(User entity) throws Exception {
		   Integer count= 0 ;
		   String userName=entity.getUserName()  ;
		   String password=entity.getPassword()    ;
		   entity.setCreateTime(new Date());   //设置创建时间
		   password=this.encryptUtils.encBase64(userName+"@"+password);  //密码加密后增加
		   entity.setPassword(password);    
		   count=this.userDAO.doCreate(entity)  ;   //1.向shiro_user表中增加数据
		   Integer userId=this.userDAO.findSeqCurrVal() ;  //取出序列当前值作为userId
		   List<UserRoleRecord> userRoleRecords=new ArrayList<UserRoleRecord>() ;
		   for(Role role : entity.getRoles()){
			   UserRoleRecord userRoleRecord=new UserRoleRecord(userId, role.getRoleId());
			   userRoleRecords.add(userRoleRecord)  ;  //添加中间数据
		   }
		   if(userRoleRecords.size()!=0){        //中间表shiro_user_role没必要增加数据,因为增加用户时没选择角色
			   count=count+this.userDAO.doCreateAfter(userRoleRecords)  ;
		   }
		   return count ;
	}

	public Integer doUpdate(User entity) throws Exception {
		   Integer count= 0 ;
		   String userName=entity.getUserName()  ;  
		   String password=entity.getPassword()    ;       //获取密码
		   password=this.encryptUtils.encBase64(userName+"@"+password);  //密码加密处理
		   entity.setPassword(password);  //将密码设置为加密后的密码
		   count=this.userDAO.doUpdate(entity)  ;  //1.先更新shiro_user表
		   count=count+this.userDAO.doRemovePre(entity.getUserId())  ;  //2.删除原先的中间表数据
		   List<UserRoleRecord> userRoleRecords=new ArrayList<UserRoleRecord>();
		   for(Role role : entity.getRoles() ){
			    UserRoleRecord userRoleRecord=null ;
			    userRoleRecord=new UserRoleRecord(entity.getUserId(), role.getRoleId()) ;
			    userRoleRecords.add(userRoleRecord) ;  //增加中间数据
		   }
		   if(userRoleRecords.size()!=0){   //中间表shiro_user_role没必要增加数据,因为增加用户时没选择角色
			   count=count+this.userDAO.doCreateAfter(userRoleRecords) ; //3.向中间表增加数据
		   }
		   return count ;
	}

	public Integer doRemove(Integer id) throws Exception {
		   Integer count= 0 ;
		   //1.先删除中间表shiro_user_role的数据
		   count=this.userDAO.doRemovePre(id)  ;
		   //2.在删除shiro_user中的数据
		   count=count+this.userDAO.doRemove(id)  ;
		   return count ;
	}

	public User findById(Integer id) throws Exception {
		User user=null ;
		user=this.userDAO.findById(id)  ;
		String password = user.getPassword() ;  //取出从数据库中得到的密码
		password=this.encryptUtils.decBase64(password) ;   //对密码解密
		password=password.split("@")[1]  ;     //将数据库中的密码拆分出来
		user.setPassword(password);    //重新设置密码
		return user;
	}

	public Integer doUpdateLastLogin(User user) throws Exception {
		Integer count= 0 ;
		count=this.userDAO.doUpdateLastLogin(user)  ;
		return count;
	}

	public User findByUserName(String userName) throws Exception {
		User user=null ;
		user=this.userDAO.findByUserName(userName)   ;
		String password=null ;
		if(user!=null){
		    password = user.getPassword();  //取出从数据库中得到的密码
		    password=this.encryptUtils.decBase64(password) ;   //对密码解密
		    password=password.split("@")[1]  ;     //将数据库中的密码拆分出来
		    user.setPassword(password);    //重新设置密码
		}
		return user;
	}

	/**
	 * 这里没有将密码进行解密,主要是列表查询泄密
	 * 
	 */
	public List<User> findAll() throws Exception {
		List<User> users=this.userDAO.findAll()  ;    //查询全部用户
		return users;
	}



}
