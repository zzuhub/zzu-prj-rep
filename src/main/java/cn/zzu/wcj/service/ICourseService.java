package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Course;

public interface ICourseService extends IBaseService<Integer, Course> {
	
	 //分页查询和模糊查询使用 
	 Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)throws Exception ;
	 
	 Integer doCreateBatch(List<Course> courses)throws Exception ;  //批量增加数据使用
	 
	 List<Course> findByTerm(Integer term) throws Exception  ;  //根据学期选择
	 
	 
	 
}
