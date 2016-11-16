package cn.zzu.wcj.service;


import java.util.List;

import cn.zzu.wcj.entity.User;

public interface IUserService extends IBaseService<Integer, User>{
	
	
	//�����û���(UNIQUE)������ȥ�������һ�ε�½��ʱ��,��Ӱ������ȥ�ж��û��Ƿ����
	Integer doUpdateLastLogin(User user)throws Exception ;
	
	
	//�����û������ҵ��û�
	User findByUserName(String userName) throws Exception   ;
	
	//��ѯȫ���û�
	List<User> findAll()throws Exception  ;
	
}
