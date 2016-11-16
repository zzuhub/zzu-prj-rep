package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Course;

public interface ICourseService extends IBaseService<Integer, Course> {
	
	 //��ҳ��ѯ��ģ����ѯʹ�� 
	 Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)throws Exception ;
	 
	 Integer doCreateBatch(List<Course> courses)throws Exception ;  //������������ʹ��
	 
	 List<Course> findByTerm(Integer term) throws Exception  ;  //����ѧ��ѡ��
	 
	 
	 
}
