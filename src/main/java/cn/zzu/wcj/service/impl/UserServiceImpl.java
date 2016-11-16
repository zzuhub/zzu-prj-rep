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
		   entity.setCreateTime(new Date());   //���ô���ʱ��
		   password=this.encryptUtils.encBase64(userName+"@"+password);  //������ܺ�����
		   entity.setPassword(password);    
		   count=this.userDAO.doCreate(entity)  ;   //1.��shiro_user������������
		   Integer userId=this.userDAO.findSeqCurrVal() ;  //ȡ�����е�ǰֵ��ΪuserId
		   List<UserRoleRecord> userRoleRecords=new ArrayList<UserRoleRecord>() ;
		   for(Role role : entity.getRoles()){
			   UserRoleRecord userRoleRecord=new UserRoleRecord(userId, role.getRoleId());
			   userRoleRecords.add(userRoleRecord)  ;  //����м�����
		   }
		   if(userRoleRecords.size()!=0){        //�м��shiro_user_roleû��Ҫ��������,��Ϊ�����û�ʱûѡ���ɫ
			   count=count+this.userDAO.doCreateAfter(userRoleRecords)  ;
		   }
		   return count ;
	}

	public Integer doUpdate(User entity) throws Exception {
		   Integer count= 0 ;
		   String userName=entity.getUserName()  ;  
		   String password=entity.getPassword()    ;       //��ȡ����
		   password=this.encryptUtils.encBase64(userName+"@"+password);  //������ܴ���
		   entity.setPassword(password);  //����������Ϊ���ܺ������
		   count=this.userDAO.doUpdate(entity)  ;  //1.�ȸ���shiro_user��
		   count=count+this.userDAO.doRemovePre(entity.getUserId())  ;  //2.ɾ��ԭ�ȵ��м������
		   List<UserRoleRecord> userRoleRecords=new ArrayList<UserRoleRecord>();
		   for(Role role : entity.getRoles() ){
			    UserRoleRecord userRoleRecord=null ;
			    userRoleRecord=new UserRoleRecord(entity.getUserId(), role.getRoleId()) ;
			    userRoleRecords.add(userRoleRecord) ;  //�����м�����
		   }
		   if(userRoleRecords.size()!=0){   //�м��shiro_user_roleû��Ҫ��������,��Ϊ�����û�ʱûѡ���ɫ
			   count=count+this.userDAO.doCreateAfter(userRoleRecords) ; //3.���м����������
		   }
		   return count ;
	}

	public Integer doRemove(Integer id) throws Exception {
		   Integer count= 0 ;
		   //1.��ɾ���м��shiro_user_role������
		   count=this.userDAO.doRemovePre(id)  ;
		   //2.��ɾ��shiro_user�е�����
		   count=count+this.userDAO.doRemove(id)  ;
		   return count ;
	}

	public User findById(Integer id) throws Exception {
		User user=null ;
		user=this.userDAO.findById(id)  ;
		String password = user.getPassword() ;  //ȡ�������ݿ��еõ�������
		password=this.encryptUtils.decBase64(password) ;   //���������
		password=password.split("@")[1]  ;     //�����ݿ��е������ֳ���
		user.setPassword(password);    //������������
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
		    password = user.getPassword();  //ȡ�������ݿ��еõ�������
		    password=this.encryptUtils.decBase64(password) ;   //���������
		    password=password.split("@")[1]  ;     //�����ݿ��е������ֳ���
		    user.setPassword(password);    //������������
		}
		return user;
	}

	/**
	 * ����û�н�������н���,��Ҫ���б��ѯй��
	 * 
	 */
	public List<User> findAll() throws Exception {
		List<User> users=this.userDAO.findAll()  ;    //��ѯȫ���û�
		return users;
	}



}
