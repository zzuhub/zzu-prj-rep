package cn.zzu.wcj.junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.entity.CreditPerTermAcca;
import cn.zzu.wcj.service.IAccaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class AccaServiceTest {

	@Autowired
	private IAccaService accaService  ;
	
	@Test
	public void test() {
		assertNotNull(accaService);
	}

	@Test
	public void testFindAllCount() throws Exception{
		   Integer count=this.accaService.findAllCount("2014888")  ;
		   System.out.println("������������רҵ������:"+count)    ;
	}
	
	
	@Test
	public void testFindRankByStudentId() throws Exception{
		    Integer rank=this.accaService.findRankByStudentId("2014666")   ;
		    System.out.println("������������רҵ����:"+rank)    ;
	}
	
	@Test
	public void testFindCreditPerTermAccasByStudentId()throws Exception{
		    List<CreditPerTermAcca> creditPerTermAccas = null;
		    creditPerTermAccas=this.accaService.findCreditPerTermAccasByStudentId("2014888");
		    System.out.println("������������ѧ��ͳ�ơ�������������")    ;
		    for(CreditPerTermAcca cpta:creditPerTermAccas){
		    	 System.out.println("��"+cpta.getTerm()+"ѧ��,ѧ��:"+cpta.getSumPassCredit()+",��ѧ��:"
		                          +cpta.getSumCredit()); 
		    }
		    System.out.println("������������ѧ��ͳ�ơ�������������")    ;
	}
	
	
	
}
