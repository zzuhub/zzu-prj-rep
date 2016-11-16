package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Score;

public interface IScoreService extends IBaseService<Integer, Score> {

	/**
	 * 
	 * @param currentPage  ��ǰҳ
	 * @param lineSize     ÿҳ��ʾ��������
	 * @param keyWord      ��ѯ�ؼ���
	 * @return             �ɼ��б�ͷ�ҳ��Ϣ(PageInfo)
	 * @throws Exception   �����쳣ֱ���׳�,�������Ʋ㴦��
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)
	                                                                     throws Exception   ;
	
	/**
	 * 
	 * @param scores �ɼ��б�
	 * @return �������ݵ�����
	 * @throws Exception ������ֵ��쳣ֱ�������׳�,�������Ʋ㴦��
	 */
	Integer doCreateBatch(List<Score> scores)
	                                        throws Exception ;
	
	/**
	 * ����ѧ�ź�ѧ�ڲ�ѯѧ���ĳɼ�
	 * @param map key1=studentId,k2=term
	 * @return  �ɼ��б�
	 * @throws Exception  ��������쳣ֱ���׳�,�������Ʋ㴦��
	 * 
	 */
	List<Score> findByStudentIdAndTerm(Map<String,Object> map)throws Exception  ;  //���ݶ������
	
}
