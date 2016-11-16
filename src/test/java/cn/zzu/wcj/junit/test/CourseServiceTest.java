package cn.zzu.wcj.junit.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.service.ICourseService;

import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class CourseServiceTest {

	@Autowired
	private ApplicationContext ctx ;
	
	
	
	@Autowired
	private ICourseService courseService   ;
	
	
	@Test
	public void testCtx(){
		System.out.println("--------------------"+ctx);
	}
	
	@Test
	public void testFindById() throws Exception{
		Course course=courseService.findById(23)  ;
		if(course!=null){
			System.out.println("---------------------课程基本信息--------------------");
			System.out.println("编号:"+course.getCourseId());
			System.out.println("名称:"+course.getCourseName());
			System.out.println("学分:"+course.getCredit());
			System.out.println("学期:"+course.getTerm());
			System.out.println("简介:"+course.getNote());
			System.out.println("授课老师:"+course.getTeachers());
			System.out.println("开设专业:"+course.getMajors());
			System.out.println("---------------------课程基本信息--------------------");
		}
	}
	
	@Test
	public void testDoCreate()throws Exception{
		   Course course=new Course()  ;
		   course.setCourseName("安卓开发")  ;
		   course.setNote("尚硅谷全套安卓课程")    ;
		   course.setCredit(4) ;
		   course.setTerm(5) ;  
		   Integer count=0 ;
		   count=this.courseService.doCreate(course)   ;
		   System.out.println("增加数据行数:"+count);
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		   Course course=new Course()  ;   //课程
		   course.setCourseId(18);
		   course.setCourseName("SSH框架");
		   course.setNote("Struts2+Spring+Hibernate") ;
		   course.setCredit(5) ;
		   course.setTerm(5);
		   Integer count=0 ;
		   count=this.courseService.doUpdate(course) ;  //更新
		   System.out.println("更新行数:"+count);
	}
	
	@Test
	public void testDoRemove() throws Exception{
		  Integer count= 0 ;
		  count=this.courseService.doRemove(22)  ;
		  System.out.println("删除行数:"+count);
	}
	
	@SuppressWarnings(value={"unchecked"})
	@Test
	public void testFindAll()throws Exception{
		   String keyWord=""  ;
		   Map<String,Object> map=this.courseService.findAll(2, 100, keyWord) ;
		   PageInfo<Course> pageInfo=(PageInfo<Course>) map.get("pageInfo"); //分页信息
		   List<Course> courses=(List<Course>) map.get("courses") ;
		   System.out.println("-------------------分页信息-------------");
		   System.out.println("总页数:"+pageInfo.getPages());
		   System.out.println("每页显示的数据量:"+pageInfo.getPageSize());
		   System.out.println("当前页:"+pageInfo.getPageNum());
		   System.out.println("总数据量"+pageInfo.getTotal());
		   System.out.println("当前页数据量"+pageInfo.getSize());
		   System.out.println("-------------------分页信息-------------");
		   for(Course course : courses){
			   System.out.println(course);
		   }
	}
	
	@Test
	public void testDoCreateBatch()throws Exception{
		  Course history=new Course()  ;
		  history.setCourseName("中国近代史");
		  history.setCredit(1);
		  history.setNote("介绍中国历史的嘛哈哈哈");
		  history.setTerm(5);
		  
		  Course php=new Course()  ;
		  php.setCourseName("LAMP");
		  php.setCredit(5);
		  php.setNote("Linux+Apache+MySQL+PHP程序设计");
		  php.setTerm(5);
		  
		  List<Course> courses=Arrays.asList(history,php)  ;
		  
		  Integer count= 0  ;
		  count=this.courseService.doCreateBatch(courses)  ;  //批量增加 
		  
		  System.out.println("批处理行数:"+count);
		   
		
	}
	
	
	@Test
	public void testFindByTerm()throws Exception{
		  System.out.println(this.courseService.findByTerm(4));
	}
	
	
	
	
	
	
}
