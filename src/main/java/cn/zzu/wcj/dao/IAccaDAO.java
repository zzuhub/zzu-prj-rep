package cn.zzu.wcj.dao;

import java.io.Serializable;
import java.util.List;

import cn.zzu.wcj.entity.CreditPerTermAcca;

public interface IAccaDAO extends Serializable{
       
	/**
	 * 根据学号找到学生所在专业的全部人数
	 * @param sutdentId 学号
	 * @return 学号所在专业的全部人数
	 * @throws Exception 遇到异常直接抛出,交给控制层处理 
	 */
	Integer findAllCount(String sutdentId)throws Exception  ;
	
	/**
	 * 根据学号找到学生在所在的专业的排名
	 * @param studentId 学号
	 * @return 学生的排名
	 * @throws Exception 遇到异常直接抛出,交给控制层处理 
	 */
	Integer findRankByStudentId(String studentId)throws Exception   ;
	
	/**
	 * 根据学号查询学生每学期修够的学分和总学分,方便做柱状图
	 * @param studentId  学生编号
	 * @return 每学期学分列表
	 * @throws Exception
	 */
	List<CreditPerTermAcca> findCreditPerTermAccasByStudentId(String studentId)throws Exception ;
	
	
}
