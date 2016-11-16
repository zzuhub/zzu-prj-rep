package cn.zzu.wcj.service;

import java.util.Map;

import cn.zzu.wcj.entity.Teach;

public interface ITeachService extends IBaseService<Integer, Teach>{
	
	Map<String,Object> findAll(Integer currentPage,Integer lineSize)throws Exception ;
	
}
