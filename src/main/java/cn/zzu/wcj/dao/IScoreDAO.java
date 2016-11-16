package cn.zzu.wcj.dao;

import java.util.List;

import java.util.Map;

import cn.zzu.wcj.entity.Score;

public interface IScoreDAO extends IBaseDAO<Integer, Score>{
	
	
	/**
	 * 
	 * @param keyWord ���Ը���ѧ��������ѧ�š��γ̺š��γ�����ѯ�ɼ�
	 * @return �ɼ��б�
	 * @throws Exception   �쳣ֱ���׳�,�������Ʋ㴦�� 
	 */
	List<Score> findAll(String keyWord)throws Exception   ;      //��ѯȫ�� 
	
	//�������� 
	Integer doCreateBatch(List<Score> score)throws Exception ;    //��������
	
	/**
	 * ����ѧ�ź�ѧ�ڲ�ѯѧ���ĳɼ�
	 * @param map key1=studentId,k2=term
	 * @return  �ɼ��б�
	 * @throws Exception  ��������쳣ֱ���׳�,�������Ʋ㴦��
	 * 
	 */
	List<Score> findByStudentIdAndTerm(Map<String,Object> map)throws Exception  ;  //���ݶ������
	
	
	
}
