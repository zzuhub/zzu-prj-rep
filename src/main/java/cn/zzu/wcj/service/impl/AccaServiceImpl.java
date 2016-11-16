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
		count=this.accaDAO.findAllCount(studentId) ;  //根据学号查找学生所在专业总人数
		return count  ;
	}

	public Integer findRankByStudentId(String studentId) throws Exception {
		Integer rank = 0  ;
		rank=this.accaDAO.findRankByStudentId(studentId)  ;   //根据学号查找学生的排名
		return rank  ;
	}

	public List<CreditPerTermAcca> findCreditPerTermAccasByStudentId(String studentId)
			                                                                    throws Exception {
		List<CreditPerTermAcca> creditPerTermAccas=null ;
		creditPerTermAccas=this.accaDAO.findCreditPerTermAccasByStudentId(studentId) ;//根据学号查学分详情
		return creditPerTermAccas ;
	}

}
