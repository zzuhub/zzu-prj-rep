package cn.zzu.wcj.service;

import java.util.Map;

import cn.zzu.wcj.entity.Major;

public interface IMajorService extends IBaseService<Integer, Major>{
	
	/**
	 * 
	 * @param currentPage ��ǰҳ
	 * @param lineSize   ÿҳ��ʾ��������
	 * @param keyWord   �ؼ���
	 * @return          ����������רҵ���Ϻͷ�ҳ��Ϣ
	 * @throws Exception ���������쳣ֱ���׳�,�������Ʋ㴦�� 
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
			                                                         throws Exception ;
	
	//�������Ʋ���רҵ��Ϣ
	Major findMajorByName(String majorName) throws Exception ;  
	
	
	
}
