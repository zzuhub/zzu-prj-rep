package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Teacher;

public interface ITeacherDAO extends IBaseDAO<Integer, Teacher>{

	 Integer doRemovePre(Integer teacherId)throws Exception  ;    //删除teach对应的记录 
	 
	 List<Teacher> findAll(String keyWord)throws Exception ;   //复杂查询
	 
}
