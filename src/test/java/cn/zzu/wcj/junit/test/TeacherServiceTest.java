package cn.zzu.wcj.junit.test;


import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.entity.Teacher;
import cn.zzu.wcj.service.ITeacherService;

import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class TeacherServiceTest {

	 @Autowired
	 private ITeacherService teacherService   ;
	
	  @Test
	  public void testFindById() throws Exception{
		  Teacher teacher=this.teacherService.findById(4)   ;
		  HashSet<Course> courses=new HashSet<Course>(teacher.getCourses())   ;
		  System.out.println(teacher)  ;
		  System.out.println(courses);
	  }
	  
	  
	  @Test
	  public void testDoUpdate() throws Exception{
		  Teacher teacher=new Teacher()  ;
		  teacher.setTeacherId(2);
		  teacher.setTeacherName("刘王八");
		  Integer count= 0  ;
		  count=this.teacherService.doUpdate(teacher)  ;  //更新教师   
          System.out.println("更新行数"+count);   	
	  }
	  
	  @Test
	  public void testDoCreate() throws Exception{
		    Teacher teacher=new Teacher()  ;
		    teacher.setTeacherName("测试添加老师");
		    Integer count = this.teacherService.doCreate(teacher) ;
		    System.out.println("增加数据行数"+count) ;
	  }
	  
	  @Test
	  public void testDoRemove() throws Exception{
		     Integer count = this.teacherService.doRemove(2)  ;
		     System.out.println("删除数据行数:"+count);
	  }
	 
	 @SuppressWarnings(value={"unchecked"})
	 @Test
	 public void testFindAll() throws Exception{
		    Map<String,Object> map=this.teacherService.findAll(1, 5, "测试") ;
		    List<Teacher> teachers = (List<Teacher>)map.get("teachers") ;
		    for(Teacher teacher : teachers){
		    	System.out.println(teacher);
		    }
            PageInfo<Teacher> pageInfo=(PageInfo<Teacher>) map.get("pageInfo");
            System.out.println(pageInfo.getPageNum());
            System.out.println(pageInfo.getPages());
            System.out.println(pageInfo.getPageSize());
            System.out.println(pageInfo.getSize());
	 }
	  
	  

}
