package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Major;

public interface IMajorDAO extends IBaseDAO<Integer, Major> {
	
	//分页只需要把模糊查询的关键词传到SQL语句中,Service层使用PageHelper进行分页
	List<Major> findAll(String keyWord)throws Exception  ;
	
	/**
	 * 
	 * @param majorId专业代码
	 * @return
	 * @throws Exception 异常直接抛出控制层处理 
	 */
	Integer doRemoveScore(Integer majorId)throws Exception   ;
	Integer doRemoveStudent(Integer majorId)throws Exception ;
	Integer doRemoveTeach(Integer majorId)throws Exception   ;
	Integer doRemoveClazz(Integer majorId)throws Exception   ;
	
	//根据专业名称查找专业
	Major findMajorByName(String majorName) throws Exception ;
	
}
