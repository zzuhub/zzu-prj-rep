package cn.zzu.wcj.junit.test;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.entity.Student;
import cn.zzu.wcj.service.IStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class StudentServiceTest {

	@Autowired
	private IStudentService studentService  ;
	
	@Test
	public void test() {
		assertNotNull(this.studentService)  ;
	}
	
	@Test
	public void testDoCreate() throws Exception{
		  Student student=null;
		  student=new Student("201477E0332", "测试数据", 4, "女", "郑州",new SimpleDateFormat("yyyy-MM-dd").parse("1994-10-10"), 
				                null, "888888", "110", "noPhoto.jpg", "仅仅测试") ;
		  Integer count = this.studentService.doCreate(student) ;  //增加学生信息
		  System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆增加数据量:"+count);
	}
	
	@Test
	public void testDoRemove()throws Exception{
		   String studentId="201477E0332"   ;
		   Integer count=0 ;
		   count=this.studentService.doRemove(studentId)  ;
		   System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆删除数据量:"+count);
	}
	
	@Test
	public void testFindById() throws Exception{
		   String studentId="201477E0332" ;   //学生编号
		   Student student=this.studentService.findById(studentId)  ;
		   if(null != student){
			    System.out.println("☆☆☆☆☆☆☆☆☆☆☆学生信息↓☆☆☆☆☆☆☆☆☆☆☆☆") ;
			    System.out.println("编号:"+student.getStudentId());
			    System.out.println("姓名:"+student.getStudentName());
			    System.out.println("地址:"+student.getAddress());
			    System.out.println("密码:"+student.getPassword());
			    System.out.println("年龄:"+student.getAge());
			    System.out.println("生日:"+student.getBirth());
			    System.out.println("电话:"+student.getTel());
			    System.out.println("照片"+student.getPhoto());
			    System.out.println("性别:"+student.getSex());
			    System.out.println("班级代码:"+student.getClazzId());
			    System.out.println("班级名称:"+student.getClazz().getClazzName());
			    System.out.println("☆☆☆☆☆☆☆☆☆☆☆学生信息↑☆☆☆☆☆☆☆☆☆☆☆☆") ;
		   }
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		  Student student=this.studentService.findById("201477E0332") ;
		  student.setStudentName("测试修改") ;
		  student.setAddress("ZhengZhou") ;
		  student.setNote("数据修改了") ;
		  student.setSex("男")   ;
		  student.setTel("119")  ;
		  student.setPhoto("nophoto.jpg") ;
		  student.setClazzId(3) ;
		  student.setPassword("168168");
		  student.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1998-08-08")) ;
		  Integer count=this.studentService.doUpdate(student) ;
		  System.out.println("☆☆☆☆☆修改数据行数:"+count) ;
	}
	
	
	@Test
	public void testDoCreateBatch()throws Exception{
		 Student student1=null;
		  student1=new Student("201477E0319", "批量测试1", 2, "女", "郑州",new SimpleDateFormat("yyyy-MM-dd").parse("1995-03-03"), 
				                null, "888888", "666", "noPhoto.jpg", "批量1") ;
		  Student student2=null;
		  student2=new Student("201477E0329", "批量测试2", 3, "女", "ZZ",new SimpleDateFormat("yyyy-MM-dd").parse("1990-06-06"), 
				  null, "111111", "888", "noPhoto.jpg", "批量2") ;
		  
		  List<Student> students=Arrays.asList(student1,student2)  ;
		  Integer count=this.studentService.doCreateBatch(students) ;  //批量增加
		  System.out.println("☆☆☆☆☆批量增加数据行数:"+count);
		  
	}
	
	@SuppressWarnings(value={"unchecked"})
	@Test
	public void testFindAll()throws Exception{
		 Integer currentPage=1  ;
		 Integer lineSize=5     ;
		 String keyWord="修改"    ;
		 Map<String,Object> map=this.studentService.findAll(currentPage, lineSize, keyWord);
		 List<Student> students=(List<Student>) map.get("students") ;
		 PageInfo<Student> pageInfo=(PageInfo<Student>) map.get("pageInfo") ;
		 System.out.println("☆☆☆☆☆☆☆☆☆☆分页信息↓☆☆☆☆☆☆☆☆☆☆");
		 System.out.println("页码:"+pageInfo.getPageNum());
		 System.out.println("每页显示的数据量:"+pageInfo.getPageSize());
		 System.out.println("实际当前页的数据量:"+pageInfo.getSize());
		 System.out.println("总数据量:"+pageInfo.getTotal());
		 System.out.println("总页数:"+pageInfo.getPages());
		 System.out.println("☆☆☆☆☆☆☆☆☆☆分页信息↑☆☆☆☆☆☆☆☆☆☆");
		 System.out.println();
		 System.out.println();
		 System.out.println();
		 System.out.println("☆☆☆☆☆☆☆☆☆☆数据↓☆☆☆☆☆☆☆☆☆☆");
		 for(Student student : students){
			 System.out.println(student);
		 }
		 System.out.println("☆☆☆☆☆☆☆☆☆☆数据↑☆☆☆☆☆☆☆☆☆☆");
		 
	}
	
	@Test
	public void testFindLogin() throws Exception{
		  Student student=new Student()   ;
		  student.setStudentId("2014888") ;
		  student.setPassword("168168")   ;
		  Integer count=this.studentService.findLogin(student)  ;   //登录验证
		  System.out.println("☆☆☆☆☆☆☆登录状态:"+(count!=0));
	}
	
	@Test
	public void testDoUpdatePassword()throws Exception{
		   Student student=new Student()  ;
		   student.setStudentId("2014888") ;
		   student.setPassword("168168") ;
		   Integer count=this.studentService.doUpdatePassword(student) ;
		   System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆更新数据行数:"+count);
	}
	
	
	
	
	

}
