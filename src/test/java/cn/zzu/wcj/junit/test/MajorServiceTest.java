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
		    major.setMajorName("测试专业") ;
		    major.setNote("JUnit单元测试-专业");
		    major.setCreateTime(new Date());
		    Integer count=this.majorService.doCreate(major)  ;
		    System.out.println("增加数据行数:"+count) ;
	  }
	  
	  @Test
	  public void testDoUpdate() throws Exception{
		   Major major=this.majorService.findById(4)   ;
		   major.setMajorName("测试修改专业") ;
		   major.setNote("JUnit单元测试")    ;
		   Integer count = this.majorService.doUpdate(major);
		   System.out.println("修改数据行数:"+count);
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
		   System.out.println("---------------------分页信息------------");
		   System.out.println("当前页码:"+pageInfo.getPageNum());
		   System.out.println("每页显示数据量:"+pageInfo.getPageSize());
		   System.out.println("当前页实际数据量:"+pageInfo.getSize());
		   System.out.println("总共数据量:"+pageInfo.getTotal());
		   System.out.println("总页数:"+pageInfo.getPages());
		   System.out.println("---------------------分页信息------------");
		   for(Major major : majors){
			   System.out.println(major);
		   }
	  }
	  
	  @Test
	  public void testDoRemove() throws Exception{
		    Integer count = this.majorService.doRemove(3) ;
		    System.out.println("删除数据量:"+count);
	  }
	  
	  @Test
	  public void testFindMajorByName() throws Exception{
		     Major major=this.majorService.findMajorByName("土木");
		     System.out.println("*********"+major);
	  }
	  

}
