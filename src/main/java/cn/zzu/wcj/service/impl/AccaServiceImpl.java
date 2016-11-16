package cn.zzu.wcj.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zzu.wcj.dao.IAccaDAO;
import cn.zzu.wcj.entity.CreditPerTermAcca;
import cn.zzu.wcj.service.IAccaService;

@Service
public class AccaServiceImpl implements IAccaService {

	@Autowired
	private IAccaDAO accaDAO    ;
	
	private static final long serialVersionUID = 1L;

	public Integer findAllCount(String studentId) throws Exception {
		Integer count=0 ; 
		count=this.accaDAO.findAllCount(studentId) ;  //����ѧ�Ų���ѧ������רҵ������
		return count  ;
	}

	public Integer findRankByStudentId(String studentId) throws Exception {
		Integer rank = 0  ;
		rank=this.accaDAO.findRankByStudentId(studentId)  ;   //����ѧ�Ų���ѧ��������
		return rank  ;
	}

	public List<CreditPerTermAcca> findCreditPerTermAccasByStudentId(String studentId)
			                                                                    throws Exception {
		List<CreditPerTermAcca> creditPerTermAccas=null ;
		creditPerTermAccas=this.accaDAO.findCreditPerTermAccasByStudentId(studentId) ;//����ѧ�Ų�ѧ������
		return creditPerTermAccas ;
	}

}
