package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Major;

public interface IMajorDAO extends IBaseDAO<Integer, Major> {
	
	//��ҳֻ��Ҫ��ģ����ѯ�Ĺؼ��ʴ���SQL�����,Service��ʹ��PageHelper���з�ҳ
	List<Major> findAll(String keyWord)throws Exception  ;
	
	/**
	 * 
	 * @param majorIdרҵ����
	 * @return
	 * @throws Exception �쳣ֱ���׳����Ʋ㴦�� 
	 */
	Integer doRemoveScore(Integer majorId)throws Exception   ;
	Integer doRemoveStudent(Integer majorId)throws Exception ;
	Integer doRemoveTeach(Integer majorId)throws Exception   ;
	Integer doRemoveClazz(Integer majorId)throws Exception   ;
	
	//����רҵ���Ʋ���רҵ
	Major findMajorByName(String majorName) throws Exception ;
	
}
