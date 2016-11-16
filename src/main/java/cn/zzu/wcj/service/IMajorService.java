package cn.zzu.wcj.service;

import java.util.Map;

import cn.zzu.wcj.entity.Major;

public interface IMajorService extends IBaseService<Integer, Major>{
	
	/**
	 * 
	 * @param currentPage 当前页
	 * @param lineSize   每页显示的数据量
	 * @param keyWord   关键词
	 * @return          符合条件的专业集合和分页信息
	 * @throws Exception 遇到问题异常直接抛出,交给控制层处理 
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
			                                                         throws Exception ;
	
	//根据名称查找专业信息
	Major findMajorByName(String majorName) throws Exception ;  
	
	
	
}
