package cn.zzu.wcj.junit.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.entity.Teach;
import cn.zzu.wcj.service.ITeachService;

import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class TeachServiceTest {

	@Autowired
	private ITeachService teachService   ;
	
	@Test
	public void test() {
		assertNotNull(teachService)          ;
	}
	
	@Test
	public void testDoCreate() throws Exception{
		Teach teach=new Teach() ;
		teach.setTeacherId(5);
		teach.setCourseId(9) ;
		teach.setClazzId(11);
		Integer count=0 ;
		count=this.teachService.doCreate(teach)  ;
		System.out.println("增加数据行数:"+count);
	}
	
	@Test
	public void testFindById() throws Exception{
		 Teach teach = this.teachService.findById(2)  ;
		 System.out.println("******************"+teach);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAll()throws Exception{
		  Map<String, Object> map = this.teachService.findAll(1, 5)  ;
		  PageInfo<Teach> pageInfo=(PageInfo<Teach>) map.get("pageInfo")  ;
		  List<Teach>  teachs=(List<Teach>) map.get("teachs") ;
		  System.out.println("----------------分页参数↓-------------------");
		  System.out.println("总页数:"+pageInfo.getPages());
		  System.out.println("总数据量:"+pageInfo.getTotal());
		  System.out.println("当前页码:"+pageInfo.getPageNum());
		  System.out.println("每页显示数据量:"+pageInfo.getPageSize());
		  System.out.println("实际显示数据量:"+pageInfo.getSize());
		  System.out.println("----------------分页参数↑-------------------");
		  for(Teach teach : teachs){
			  System.out.print(teach.getTeacher().getTeacherName()+"---");
			  System.out.print(teach.getCourse().getCourseName()+"---");
			  System.out.print(teach.getClazz().getClazzName()+"---");
			  System.out.println();
		  }
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		   Teach teach=this.teachService.findById(2) ;
		   teach.setTeacherId(5);
		   teach.setClazzId(11); 
		   teach.setCourseId(5); 
		   Integer count = this.teachService.doUpdate(teach);
		   System.out.println("修改数据行数:"+count);
	}
	
	@Test
	public void testDoRomove() throws Exception{
		 Integer count = this.teachService.doRemove(2) ;
		 System.out.println("删除数据行数:"+count);
	}
	
	
	
	

	
	
}
