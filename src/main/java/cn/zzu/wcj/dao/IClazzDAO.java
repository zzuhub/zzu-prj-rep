package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Clazz;

public interface IClazzDAO extends IBaseDAO<Integer, Clazz>{
    
	//�ؼ��ʲ�ѯ
	List<Clazz> findAll(String keyWord) throws Exception ;
	
	
	/**
	 * ɾ���༶��һ��ǳ����ӵ�ҵ��,��Ҫ����������ҵ��
	 * @param clazzId �༶����
	 * @return
	 * @throws Exception  �����쳣ֱ���׳�
	 */
	Integer doRemoveTeach(Integer clazzId)throws Exception  ;
	Integer doRemoveScore(Integer clazzId)throws Exception  ;
	Integer doRemoveStudent(Integer clazzId)throws Exception  ;
	
	//Ajax��֤�û����Ƿ���� 
	Clazz findByName(String clazzName)throws Exception   ;
	
	//����רҵ�����֤רҵ�Ƿ����  
	List<Clazz> findClazzsByMajorId(Integer majorId)throws Exception ;
	
}
