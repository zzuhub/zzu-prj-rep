package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Student;

public interface IStudentDAO extends IBaseDAO<String, Student>{
	
	//根据学生ID删除分数表中对应的数据 
	Integer doRemoveScore(String studentId)throws Exception ;    
	
	
	//批量导入学生信息
	Integer doCreateBatch(List<Student> students)throws Exception   ;
	
	
	List<Student> findAll(String keyWord)throws Exception    ;
	
	/**
	 * 学生登录模块验证
	 * @param student 传入的蹙额生实体
	 * @return ①返回值是1,表示登录成功 ②返回值是0表示登录失败
	 * @throws Exception 异常直接抛出,交给控制层处理 
	 */
	Integer findLogin(Student student)throws Exception  ;    
	
	/**
	 * 学生自己修改密码
	 * @param student 学生实体
	 * @return 更新数据行数
	 * @throws Exception  异常直接抛出,交给控制层处理 
	 */
	Integer doUpdatePassword(Student student)throws Exception ;
	
	
	
	
}
