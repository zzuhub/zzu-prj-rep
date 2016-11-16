package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Teach;

public interface ITeachDAO extends IBaseDAO<Integer, Teach>{
	 
	
	List<Teach> findAll()throws Exception ;
	
}
