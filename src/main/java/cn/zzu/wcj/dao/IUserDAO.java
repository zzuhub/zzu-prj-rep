package cn.zzu.wcj.dao;

import java.util.List;



import cn.zzu.wcj.entity.User;
import cn.zzu.wcj.entity.UserRoleRecord;

public interface IUserDAO extends IBaseDAO<Integer, User> {
	
	//�����û���(UNIQUE)������ȥ�������һ�ε�½��ʱ��
	Integer doUpdateLastLogin(User user)throws Exception ;
	
	//���м��shiro_user_role���Ӽ�¼
	Integer doCreateAfter(List<UserRoleRecord> userRoleRecords)throws Exception ;
	
	//ɾ���û�ʱ��ɾ���м��ļ�¼
	Integer doRemovePre(Integer userId)throws Exception ;   
	
	//���м����������֮ǰ,�Ƚ����λỰ���еĵ�ǰֵȡ����(userId)
	Integer findSeqCurrVal()throws Exception ;
	
	//�����û�����ѯ�û�
	User findByUserName(String userName)throws Exception  ;
	
	//��ѯȫ���û�
	List<User> findAll()throws Exception  ;
	
	
	
	
}
