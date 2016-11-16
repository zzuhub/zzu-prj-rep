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
			System.out.println("---------------------�γ̻�����Ϣ--------------------");
			System.out.println("���:"+course.getCourseId());
			System.out.println("����:"+course.getCourseName());
			System.out.println("ѧ��:"+course.getCredit());
			System.out.println("ѧ��:"+course.getTerm());
			System.out.println("���:"+course.getNote());
			System.out.println("�ڿ���ʦ:"+course.getTeachers());
			System.out.println("����רҵ:"+course.getMajors());
			System.out.println("---------------------�γ̻�����Ϣ--------------------");
		}
	}
	
	@Test
	public void testDoCreate()throws Exception{
		   Course course=new Course()  ;
		   course.setCourseName("��׿����")  ;
		   course.setNote("�й��ȫ�װ�׿�γ�")    ;
		   course.setCredit(4) ;
		   course.setTerm(5) ;  
		   Integer count=0 ;
		   count=this.courseService.doCreate(course)   ;
		   System.out.println("������������:"+count);
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		   Course course=new Course()  ;   //�γ�
		   course.setCourseId(18);
		   course.setCourseName("SSH���");
		   course.setNote("Struts2+Spring+Hibernate") ;
		   course.setCredit(5) ;
		   course.setTerm(5);
		   Integer count=0 ;
		   count=this.courseService.doUpdate(course) ;  //����
		   System.out.println("��������:"+count);
	}
	
	@Test
	public void testDoRemove() throws Exception{
		  Integer count= 0 ;
		  count=this.courseService.doRemove(22)  ;
		  System.out.println("ɾ������:"+count);
	}
	
	@SuppressWarnings(value={"unchecked"})
	@Test
	public void testFindAll()throws Exception{
		   String keyWord=""  ;
		   Map<String,Object> map=this.courseService.findAll(2, 100, keyWord) ;
		   PageInfo<Course> pageInfo=(PageInfo<Course>) map.get("pageInfo"); //��ҳ��Ϣ
		   List<Course> courses=(List<Course>) map.get("courses") ;
		   System.out.println("-------------------��ҳ��Ϣ-------------");
		   System.out.println("��ҳ��:"+pageInfo.getPages());
		   System.out.println("ÿҳ��ʾ��������:"+pageInfo.getPageSize());
		   System.out.println("��ǰҳ:"+pageInfo.getPageNum());
		   System.out.println("��������"+pageInfo.getTotal());
		   System.out.println("��ǰҳ������"+pageInfo.getSize());
		   System.out.println("-------------------��ҳ��Ϣ-------------");
		   for(Course course : courses){
			   System.out.println(course);
		   }
	}
	
	@Test
	public void testDoCreateBatch()throws Exception{
		  Course history=new Course()  ;
		  history.setCourseName("�й�����ʷ");
		  history.setCredit(1);
		  history.setNote("�����й���ʷ���������");
		  history.setTerm(5);
		  
		  Course php=new Course()  ;
		  php.setCourseName("LAMP");
		  php.setCredit(5);
		  php.setNote("Linux+Apache+MySQL+PHP�������");
		  php.setTerm(5);
		  
		  List<Course> courses=Arrays.asList(history,php)  ;
		  
		  Integer count= 0  ;
		  count=this.courseService.doCreateBatch(courses)  ;  //�������� 
		  
		  System.out.println("����������:"+count);
		   
		
	}
	
	
	@Test
	public void testFindByTerm()throws Exception{
		  System.out.println(this.courseService.findByTerm(4));
	}
	
	
	
	
	
	
}
