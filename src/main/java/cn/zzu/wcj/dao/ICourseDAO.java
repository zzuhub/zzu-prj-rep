package cn.zzu.wcj.dao;

import java.util.List;

import cn.zzu.wcj.entity.Course;

/**
 * 
 * @author ZZU·WangChengJian
 * 更新时间:2016/10/30 18:41
 * 邮箱:2827724252@qq.com
 *
 */
public interface ICourseDAO extends IBaseDAO<Integer,Course>{
	
	/**
	 * 
	 * @param keyWord 模糊查询的关键字
	 * @return 课程列表 
	 * @throws Exception 出现异常就跳出 
	 */
	List<Course> findAll(String keyWord)throws Exception ;     //查询全部 
	
	/**
	 * 用于使用Excel批量导入数据 
	 * @param courses同时增加多门课程 
	 * @return 增加的数量 
	 * @throws Exception  出现异常就直接抛出 
	 */
	Integer doCreateBatch(List<Course> courses)throws Exception ;  //批量增加 
	
	/**
	 * 删除课程时需要预先执行的子业务
	 * @param courseId 课程编号
	 * @return 删除行数
	 * @throws Exception  遇到异常直接抛出交给控制层处理 
	 */
	Integer doRemoveScore(Integer courseId)throws Exception  ;     //删除score表中对应的记录 
	
	
	/**
	 * 删除课程时需要预先执行的子业务
	 * @param courseId 课程编号
	 * @return 删除行数
	 * @throws Exception  遇到异常直接抛出交给控制层处理 
	 */
	Integer doRemoveTeach(Integer courseId)throws Exception  ;     //删除teach表中对应的记录 
	
	List<Course> findByTerm(Integer term)throws Exception    ;     //根据学期查找课程 
	
	
}
