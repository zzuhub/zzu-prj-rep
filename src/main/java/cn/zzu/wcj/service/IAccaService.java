package cn.zzu.wcj.service;

import java.io.Serializable;
import java.util.List;

import cn.zzu.wcj.entity.CreditPerTermAcca;

public interface IAccaService extends Serializable {

	/**
	 * ����ѧ���ҵ�ѧ������רҵ��ȫ������
	 * @param sutdentId ѧ��
	 * @return ѧ������רҵ��ȫ������
	 * @throws Exception �����쳣ֱ���׳�,�������Ʋ㴦�� 
	 */
	Integer findAllCount(String studentId)throws Exception  ;
	
	/**
	 * ����ѧ���ҵ�ѧ�������ڵ�רҵ������
	 * @param studentId ѧ��
	 * @return ѧ��������
	 * @throws Exception �����쳣ֱ���׳�,�������Ʋ㴦�� 
	 */
	Integer findRankByStudentId(String studentId)throws Exception   ;
	
	/**
	 * ����ѧ�Ų�ѯѧ��ÿѧ���޹���ѧ�ֺ���ѧ��,��������״ͼ
	 * @param studentId  ѧ�����
	 * @return ÿѧ��ѧ���б�
	 * @throws Exception
	 */
	List<CreditPerTermAcca> findCreditPerTermAccasByStudentId(String studentId)throws Exception ;
	
}
