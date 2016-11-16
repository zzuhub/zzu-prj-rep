package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Clazz;

public interface IClazzService extends IBaseService<Integer, Clazz>{

	/**
	 * 
	 * @param currentPage 当前页
	 * @param lineSize    每页显示的数量
	 * @param keyWord     关键词
	 * @return            返回
	 * @throws Exception
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
	                                                                      throws Exception  ;
	Clazz findByName(String clazzName)throws Exception ;
	
	//根据专业编号验证专业是否存在  
    List<Clazz> findClazzsByMajorId(Integer majorId)throws Exception ;
	
	
}
