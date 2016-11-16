package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.entity.RolePermissionRecord;



public interface IRoleDAO extends IBaseDAO<Integer, Role> {
	  
	  /**
	   * �����û�������ȫ����ɫ����
	   * @param userName �û���
	   * @return ��ɫ���Ƽ���
	   * @throws Exception �쳣ֱ���׳�
	   */
	  List<String> findByUserName(String userName) throws Exception   ;
	
	  /**
	   * ���ӽ�ɫ�������м��shiro_role_permission�ļ�¼
	   * @param rolePermissionRecords�����м������
	   * @return �����м��������� 
	   * @throws Exception ���쳣ֱ���׳�
	   */
	  Integer doCreateAfter(List<RolePermissionRecord> rolePermissionRecords)throws Exception ;
	  
	  //���ұ��ζԻ��е�����ֵ
	  Integer findCurrSeq()throws Exception   ;
	  
	  //��ɾ��shiro_role֮ǰ,�Ƚ�shiro_role_permission���е��������
	  Integer doRemovePre(Integer roleId)throws Exception   ;
	  
	  //ɾ��shiro_user_role���еĹ�������
	  Integer doRemoveUserRoleTabById(Integer id)throws Exception   ;
	  
	  //��ѯȫ����ɫ
	  List<Role> findAll()throws Exception ; 
	  
	  //�������Ʋ�ѯ��ɫ
	  Role findByName(String roleName)throws Exception   ;
	  
}
