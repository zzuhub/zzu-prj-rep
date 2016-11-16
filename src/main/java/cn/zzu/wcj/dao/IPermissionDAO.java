package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Permission;

public interface IPermissionDAO extends IBaseDAO<Integer, Permission> {
	/**
	 *  �����û���Ų�ѯ���û�Ȩ�����Ƶļ���
	 * @param userName �û����
	 * @return Ȩ�����Ƶļ���
	 * @throws Exception ���쳣��ֱ���׳�
	 */
	List<String>  findByUserName(String userName)throws Exception;
	
	//��ѯȫ��Ȩ��
	List<Permission> findAll()throws Exception  ;

	
	
}
