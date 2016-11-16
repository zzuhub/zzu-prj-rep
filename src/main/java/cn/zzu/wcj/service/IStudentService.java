package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Student;

public interface IStudentService extends IBaseService<String, Student> {

	    //批量导入学生信息
		Integer doCreateBatch(List<Student> students)throws Exception   ;
		
		//分页查询
		/**
		 * 
		 * @param currentPage 当前页
		 * @param lineSize  每页显示的数据量
		 * @param keyWord   查询关键词
		 * @return 分页信息PageInfo和数据集合java.util.List<Student>
		 * @throws Exception 异常直接抛出,交给控制层处理 
		 */
		Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
		                                                                   throws Exception ;
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
