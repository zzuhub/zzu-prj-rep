package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Clazz;

public interface IClazzService extends IBaseService<Integer, Clazz>{

	/**
	 * 
	 * @param currentPage ��ǰҳ
	 * @param lineSize    ÿҳ��ʾ������
	 * @param keyWord     �ؼ���
	 * @return            ����
	 * @throws Exception
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
	                                                                      throws Exception  ;
	Clazz findByName(String clazzName)throws Exception ;
	
	//����רҵ�����֤רҵ�Ƿ����  
    List<Clazz> findClazzsByMajorId(Integer majorId)throws Exception ;
	
	
}
