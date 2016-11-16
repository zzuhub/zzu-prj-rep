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
		  student=new Student("201477E0332", "��������", 4, "Ů", "֣��",new SimpleDateFormat("yyyy-MM-dd").parse("1994-10-10"), 
				                null, "888888", "110", "noPhoto.jpg", "��������") ;
		  Integer count = this.studentService.doCreate(student) ;  //����ѧ����Ϣ
		  System.out.println("���������������������������:"+count);
	}
	
	@Test
	public void testDoRemove()throws Exception{
		   String studentId="201477E0332"   ;
		   Integer count=0 ;
		   count=this.studentService.doRemove(studentId)  ;
		   System.out.println("�����������������ɾ��������:"+count);
	}
	
	@Test
	public void testFindById() throws Exception{
		   String studentId="201477E0332" ;   //ѧ�����
		   Student student=this.studentService.findById(studentId)  ;
		   if(null != student){
			    System.out.println("������������ѧ����Ϣ���������������") ;
			    System.out.println("���:"+student.getStudentId());
			    System.out.println("����:"+student.getStudentName());
			    System.out.println("��ַ:"+student.getAddress());
			    System.out.println("����:"+student.getPassword());
			    System.out.println("����:"+student.getAge());
			    System.out.println("����:"+student.getBirth());
			    System.out.println("�绰:"+student.getTel());
			    System.out.println("��Ƭ"+student.getPhoto());
			    System.out.println("�Ա�:"+student.getSex());
			    System.out.println("�༶����:"+student.getClazzId());
			    System.out.println("�༶����:"+student.getClazz().getClazzName());
			    System.out.println("������������ѧ����Ϣ���������������") ;
		   }
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		  Student student=this.studentService.findById("201477E0332") ;
		  student.setStudentName("�����޸�") ;
		  student.setAddress("ZhengZhou") ;
		  student.setNote("�����޸���") ;
		  student.setSex("��")   ;
		  student.setTel("119")  ;
		  student.setPhoto("nophoto.jpg") ;
		  student.setClazzId(3) ;
		  student.setPassword("168168");
		  student.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1998-08-08")) ;
		  Integer count=this.studentService.doUpdate(student) ;
		  System.out.println("�������޸���������:"+count) ;
	}
	
	
	@Test
	public void testDoCreateBatch()throws Exception{
		 Student student1=null;
		  student1=new Student("201477E0319", "��������1", 2, "Ů", "֣��",new SimpleDateFormat("yyyy-MM-dd").parse("1995-03-03"), 
				                null, "888888", "666", "noPhoto.jpg", "����1") ;
		  Student student2=null;
		  student2=new Student("201477E0329", "��������2", 3, "Ů", "ZZ",new SimpleDateFormat("yyyy-MM-dd").parse("1990-06-06"), 
				  null, "111111", "888", "noPhoto.jpg", "����2") ;
		  
		  List<Student> students=Arrays.asList(student1,student2)  ;
		  Integer count=this.studentService.doCreateBatch(students) ;  //��������
		  System.out.println("����������������������:"+count);
		  
	}
	
	@SuppressWarnings(value={"unchecked"})
	@Test
	public void testFindAll()throws Exception{
		 Integer currentPage=1  ;
		 Integer lineSize=5     ;
		 String keyWord="�޸�"    ;
		 Map<String,Object> map=this.studentService.findAll(currentPage, lineSize, keyWord);
		 List<Student> students=(List<Student>) map.get("students") ;
		 PageInfo<Student> pageInfo=(PageInfo<Student>) map.get("pageInfo") ;
		 System.out.println("������������ҳ��Ϣ�������������");
		 System.out.println("ҳ��:"+pageInfo.getPageNum());
		 System.out.println("ÿҳ��ʾ��������:"+pageInfo.getPageSize());
		 System.out.println("ʵ�ʵ�ǰҳ��������:"+pageInfo.getSize());
		 System.out.println("��������:"+pageInfo.getTotal());
		 System.out.println("��ҳ��:"+pageInfo.getPages());
		 System.out.println("������������ҳ��Ϣ�������������");
		 System.out.println();
		 System.out.println();
		 System.out.println();
		 System.out.println("��������������ݡ������������");
		 for(Student student : students){
			 System.out.println(student);
		 }
		 System.out.println("��������������ݡ������������");
		 
	}
	
	@Test
	public void testFindLogin() throws Exception{
		  Student student=new Student()   ;
		  student.setStudentId("2014888") ;
		  student.setPassword("168168")   ;
		  Integer count=this.studentService.findLogin(student)  ;   //��¼��֤
		  System.out.println("���������¼״̬:"+(count!=0));
	}
	
	@Test
	public void testDoUpdatePassword()throws Exception{
		   Student student=new Student()  ;
		   student.setStudentId("2014888") ;
		   student.setPassword("168168") ;
		   Integer count=this.studentService.doUpdatePassword(student) ;
		   System.out.println("��������������������������:"+count);
	}
	
	
	
	
	

}
