package cn.zzu.wcj.entity;

import java.io.Serializable;

/**
 * ͳ��ÿѧ����ѧ�ֺ��޹���ѧ��
 * @author ZZU��WangChengJian
 *
 */
public class CreditPerTermAcca implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Integer term   ;   //ѧ��
	private Integer sumCredit      ;   //ÿѧ����ѧ��
	private Integer sumPassCredit  ;   //ÿѧ��ͨ����ѧ��
	
	
	public Integer getTerm() {
		return term;
	}
	
	public void setTerm(Integer term) {
		this.term = term;
	}
	
	public Integer getSumCredit() {
		return sumCredit;
	}
	
	public void setSumCredit(Integer sumCredit) {
		this.sumCredit = sumCredit;
	}
	
	public Integer getSumPassCredit() {
		return sumPassCredit;
	}
	
	public void setSumPassCredit(Integer sumPassCredit) {
		this.sumPassCredit = sumPassCredit;
	}
	
	
	
	
	
}
