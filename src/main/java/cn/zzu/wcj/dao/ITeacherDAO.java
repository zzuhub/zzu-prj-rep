package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Teacher;

public interface ITeacherDAO extends IBaseDAO<Integer, Teacher>{

	 Integer doRemovePre(Integer teacherId)throws Exception  ;    //ɾ��teach��Ӧ�ļ�¼ 
	 
	 List<Teacher> findAll(String keyWord)throws Exception ;   //���Ӳ�ѯ
	 
}
