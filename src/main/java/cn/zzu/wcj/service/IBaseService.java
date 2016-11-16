package cn.zzu.wcj.service;

import java.io.Serializable;
/**
 * 
 * @author WangChengJian(SoftWareCollege Of ZhengZhouUniversity)
 * @createTime 2016/10/22 17:36
 * @updateTime null
 * @param <T>  ��������
 * @param <E>  ʵ������
 * @introduce ����Service�߱��Ļ�������
 */
public interface IBaseService<T,E> extends Serializable{
	Integer doCreate(E entity)throws Exception  ;       //����    
	
	Integer doUpdate(E entity)throws Exception   ;       //�޸�
	
	Integer doRemove(T id)throws Exception    ;      //����IDɾ��
	
	E findById(T id)throws Exception     ;           //����ID����
}
