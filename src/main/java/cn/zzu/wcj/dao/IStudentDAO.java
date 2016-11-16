package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Student;

public interface IStudentDAO extends IBaseDAO<String, Student>{
	
	//����ѧ��IDɾ���������ж�Ӧ������ 
	Integer doRemoveScore(String studentId)throws Exception ;    
	
	
	//��������ѧ����Ϣ
	Integer doCreateBatch(List<Student> students)throws Exception   ;
	
	
	List<Student> findAll(String keyWord)throws Exception    ;
	
	/**
	 * ѧ����¼ģ����֤
	 * @param student �����������ʵ��
	 * @return �ٷ���ֵ��1,��ʾ��¼�ɹ� �ڷ���ֵ��0��ʾ��¼ʧ��
	 * @throws Exception �쳣ֱ���׳�,�������Ʋ㴦�� 
	 */
	Integer findLogin(Student student)throws Exception  ;    
	
	/**
	 * ѧ���Լ��޸�����
	 * @param student ѧ��ʵ��
	 * @return ������������
	 * @throws Exception  �쳣ֱ���׳�,�������Ʋ㴦�� 
	 */
	Integer doUpdatePassword(Student student)throws Exception ;
	
	
	
	
}
