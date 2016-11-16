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
		System.out.println("������������:"+count);
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
		  System.out.println("----------------��ҳ������-------------------");
		  System.out.println("��ҳ��:"+pageInfo.getPages());
		  System.out.println("��������:"+pageInfo.getTotal());
		  System.out.println("��ǰҳ��:"+pageInfo.getPageNum());
		  System.out.println("ÿҳ��ʾ������:"+pageInfo.getPageSize());
		  System.out.println("ʵ����ʾ������:"+pageInfo.getSize());
		  System.out.println("----------------��ҳ������-------------------");
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
		   System.out.println("�޸���������:"+count);
	}
	
	@Test
	public void testDoRomove() throws Exception{
		 Integer count = this.teachService.doRemove(2) ;
		 System.out.println("ɾ����������:"+count);
	}
	
	
	
	

	
	
}
