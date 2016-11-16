package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Course;

/**
 * 
 * @author ZZU��WangChengJian
 * ����ʱ��:2016/10/30 18:41
 * ����:2827724252@qq.com
 *
 */
public interface ICourseDAO extends IBaseDAO<Integer,Course>{
	
	/**
	 * 
	 * @param keyWord ģ����ѯ�Ĺؼ���
	 * @return �γ��б� 
	 * @throws Exception �����쳣������ 
	 */
	List<Course> findAll(String keyWord)throws Exception ;     //��ѯȫ�� 
	
	/**
	 * ����ʹ��Excel������������ 
	 * @param coursesͬʱ���Ӷ��ſγ� 
	 * @return ���ӵ����� 
	 * @throws Exception  �����쳣��ֱ���׳� 
	 */
	Integer doCreateBatch(List<Course> courses)throws Exception ;  //�������� 
	
	/**
	 * ɾ���γ�ʱ��ҪԤ��ִ�е���ҵ��
	 * @param courseId �γ̱��
	 * @return ɾ������
	 * @throws Exception  �����쳣ֱ���׳��������Ʋ㴦�� 
	 */
	Integer doRemoveScore(Integer courseId)throws Exception  ;     //ɾ��score���ж�Ӧ�ļ�¼ 
	
	
	/**
	 * ɾ���γ�ʱ��ҪԤ��ִ�е���ҵ��
	 * @param courseId �γ̱��
	 * @return ɾ������
	 * @throws Exception  �����쳣ֱ���׳��������Ʋ㴦�� 
	 */
	Integer doRemoveTeach(Integer courseId)throws Exception  ;     //ɾ��teach���ж�Ӧ�ļ�¼ 
	
	List<Course> findByTerm(Integer term)throws Exception    ;     //����ѧ�ڲ��ҿγ� 
	
	
}
