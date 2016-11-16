package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Clazz;

public interface IClazzDAO extends IBaseDAO<Integer, Clazz>{
    
	//关键词查询
	List<Clazz> findAll(String keyWord) throws Exception ;
	
	
	/**
	 * 删除班级是一项非常复杂的业务,需要包含以下子业务
	 * @param clazzId 班级代码
	 * @return
	 * @throws Exception  出现异常直接抛出
	 */
	Integer doRemoveTeach(Integer clazzId)throws Exception  ;
	Integer doRemoveScore(Integer clazzId)throws Exception  ;
	Integer doRemoveStudent(Integer clazzId)throws Exception  ;
	
	//Ajax验证用户名是否存在 
	Clazz findByName(String clazzName)throws Exception   ;
	
	//根据专业编号验证专业是否存在  
	List<Clazz> findClazzsByMajorId(Integer majorId)throws Exception ;
	
}
