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
		   System.out.println("☆☆☆☆☆☆☆☆☆☆☆专业总人数:"+count)    ;
	}
	
	
	@Test
	public void testFindRankByStudentId() throws Exception{
		    Integer rank=this.accaService.findRankByStudentId("2014666")   ;
		    System.out.println("☆☆☆☆☆☆☆☆☆☆☆专业排名:"+rank)    ;
	}
	
	@Test
	public void testFindCreditPerTermAccasByStudentId()throws Exception{
		    List<CreditPerTermAcca> creditPerTermAccas = null;
		    creditPerTermAccas=this.accaService.findCreditPerTermAccasByStudentId("2014888");
		    System.out.println("☆☆☆☆☆☆☆☆☆☆☆学分统计↓☆☆☆☆☆☆☆☆☆☆☆")    ;
		    for(CreditPerTermAcca cpta:creditPerTermAccas){
		    	 System.out.println("第"+cpta.getTerm()+"学期,学分:"+cpta.getSumPassCredit()+",总学分:"
		                          +cpta.getSumCredit()); 
		    }
		    System.out.println("☆☆☆☆☆☆☆☆☆☆☆学分统计↑☆☆☆☆☆☆☆☆☆☆☆")    ;
	}
	
	
	
}
