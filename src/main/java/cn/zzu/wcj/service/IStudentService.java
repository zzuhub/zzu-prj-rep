package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Student;

public interface IStudentService extends IBaseService<String, Student> {

	    //��������ѧ����Ϣ
		Integer doCreateBatch(List<Student> students)throws Exception   ;
		
		//��ҳ��ѯ
		/**
		 * 
		 * @param currentPage ��ǰҳ
		 * @param lineSize  ÿҳ��ʾ��������
		 * @param keyWord   ��ѯ�ؼ���
		 * @return ��ҳ��ϢPageInfo�����ݼ���java.util.List<Student>
		 * @throws Exception �쳣ֱ���׳�,�������Ʋ㴦�� 
		 */
		Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
		                                                                   throws Exception ;
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
