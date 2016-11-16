package cn.zzu.wcj.service;

import java.io.Serializable;
/**
 * 
 * @author WangChengJian(SoftWareCollege Of ZhengZhouUniversity)
 * @createTime 2016/10/22 17:36
 * @updateTime null
 * @param <T>  主键类型
 * @param <E>  实体类型
 * @introduce 定义Service具备的基本功能
 */
public interface IBaseService<T,E> extends Serializable{
	Integer doCreate(E entity)throws Exception  ;       //增加    
	
	Integer doUpdate(E entity)throws Exception   ;       //修改
	
	Integer doRemove(T id)throws Exception    ;      //根据ID删除
	
	E findById(T id)throws Exception     ;           //根据ID查找
}
