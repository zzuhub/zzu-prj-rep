package cn.zzu.wcj.service;

import java.util.List;

import cn.zzu.wcj.entity.Role;

public interface IRoleService extends IBaseService<Integer, Role> {
	   
	  /**
	   * �����û�������ȫ����ɫ����
	   * @param userName �û���
	   * @return ��ɫ���Ƽ���
	   * @throws Exception �쳣ֱ���׳�
	   */
	  List<String> findByUserName(String userName) throws Exception   ;
	
	  List<Role> findAll()throws Exception   ;    //����ȫ����ɫ,���÷�ҳ,��Ϊ��ɫ����̫��
	  
	  Role findByRoleName(String roleName)throws Exception   ;
	  
}
