package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Score;

public interface IScoreService extends IBaseService<Integer, Score> {

	/**
	 * 
	 * @param currentPage  当前页
	 * @param lineSize     每页显示的数据量
	 * @param keyWord      查询关键词
	 * @return             成绩列表和分页信息(PageInfo)
	 * @throws Exception   遇到异常直接抛出,交给控制层处理
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
	                                                                     throws Exception   ;
	
	/**
	 * 
	 * @param scores 成绩列表
	 * @return 增加数据的行数
	 * @throws Exception 程序出现的异常直接向上抛出,交给控制层处置
	 */
	Integer doCreateBatch(List<Score> scores)
	                                        throws Exception ;
	
	/**
	 * 根据学号和学期查询学生的成绩
	 * @param map key1=studentId,k2=term
	 * @return  成绩列表
	 * @throws Exception  程序出现异常直接抛出,交给控制层处理
	 * 
	 */
	List<Score> findByStudentIdAndTerm(Map<String,Object> map)throws Exception  ;  //传递多个参数
	
}
