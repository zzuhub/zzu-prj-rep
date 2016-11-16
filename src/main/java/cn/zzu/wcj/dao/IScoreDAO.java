package cn.zzu.wcj.dao;

import java.util.List;

import java.util.Map;

import cn.zzu.wcj.entity.Score;

public interface IScoreDAO extends IBaseDAO<Integer, Score>{
	
	
	/**
	 * 
	 * @param keyWord 可以根据学生姓名、学号、课程号、课程名查询成绩
	 * @return 成绩列表
	 * @throws Exception   异常直接抛出,交给控制层处理 
	 */
	List<Score> findAll(String keyWord)throws Exception   ;      //查询全部 
	
	//批量增加 
	Integer doCreateBatch(List<Score> score)throws Exception ;    //批量增加
	
	/**
	 * 根据学号和学期查询学生的成绩
	 * @param map key1=studentId,k2=term
	 * @return  成绩列表
	 * @throws Exception  程序出现异常直接抛出,交给控制层处理
	 * 
	 */
	List<Score> findByStudentIdAndTerm(Map<String,Object> map)throws Exception  ;  //传递多个参数
	
	
	
}
