package cn.zzu.wcj.service;

import java.util.Map;

import cn.zzu.wcj.entity.Teacher;

public interface ITeacherService extends IBaseService<Integer, Teacher> {

	//���Ӳ�ѯ
    Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord) throws Exception ;
	
}
