package cn.zzu.wcj.junit.test;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.entity.Major;
import cn.zzu.wcj.service.IMajorService;

import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class MajorServiceTest {

	  @Autowired
	  private IMajorService majorService  ;
	
	  @Test
	  public void testFindById() throws Exception{
		    Major major = this.majorService.findById(2)  ;
		    System.out.println(major);
	  }
	  
	  @Test
	  public void testDoCreate()throws Exception{
		    Major major=new Major()  ;
		    major.setMajorName("����רҵ") ;
		    major.setNote("JUnit��Ԫ����-רҵ");
		    major.setCreateTime(new Date());
		    Integer count=this.majorService.doCreate(major)  ;
		    System.out.println("������������:"+count) ;
	  }
	  
	  @Test
	  public void testDoUpdate() throws Exception{
		   Major major=this.majorService.findById(4)   ;
		   major.setMajorName("�����޸�רҵ") ;
		   major.setNote("JUnit��Ԫ����")    ;
		   Integer count = this.majorService.doUpdate(major);
		   System.out.println("�޸���������:"+count);
	  }
	  
	  @SuppressWarnings(value={"unchecked"})
	  @Test
	  public void testFindAll() throws Exception{
		   String keyWord=null ;
		   Integer currentPage=1 ;
		   Integer lineSize=2    ;
		   Map<String, Object> map = null;
		   map=this.majorService.findAll(currentPage, lineSize, keyWord) ;
		   PageInfo<Major> pageInfo = (PageInfo<Major>)map.get("pageInfo") ;
		   List<Major> majors=(List<Major>)map.get("majors") ;
		   System.out.println("---------------------��ҳ��Ϣ------------");
		   System.out.println("��ǰҳ��:"+pageInfo.getPageNum());
		   System.out.println("ÿҳ��ʾ������:"+pageInfo.getPageSize());
		   System.out.println("��ǰҳʵ��������:"+pageInfo.getSize());
		   System.out.println("�ܹ�������:"+pageInfo.getTotal());
		   System.out.println("��ҳ��:"+pageInfo.getPages());
		   System.out.println("---------------------��ҳ��Ϣ------------");
		   for(Major major : majors){
			   System.out.println(major);
		   }
	  }
	  
	  @Test
	  public void testDoRemove() throws Exception{
		    Integer count = this.majorService.doRemove(3) ;
		    System.out.println("ɾ��������:"+count);
	  }
	  
	  @Test
	  public void testFindMajorByName() throws Exception{
		     Major major=this.majorService.findMajorByName("��ľ");
		     System.out.println("*********"+major);
	  }
	  

}
