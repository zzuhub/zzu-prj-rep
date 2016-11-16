package cn.zzu.wcj.entity;

import java.io.Serializable;

/**
 * 统计每学期总学分和修够的学分
 * @author ZZU·WangChengJian
 *
 */
public class CreditPerTermAcca implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Integer term   ;   //学期
	private Integer sumCredit      ;   //每学期总学分
	private Integer sumPassCredit  ;   //每学期通过的学分
	
	
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
