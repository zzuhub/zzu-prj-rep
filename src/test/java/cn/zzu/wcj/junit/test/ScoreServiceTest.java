package cn.zzu.wcj.junit.test;

import static org.junit.Assert.assertNotNull;








import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.entity.Score;
import cn.zzu.wcj.entity.Student;
import cn.zzu.wcj.service.IScoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class ScoreServiceTest {

	@Autowired
	private IScoreService scoreService   ;
	
	@Test
	public void test() {
		 assertNotNull(scoreService);     //���Ȳ����Ƿ�Ϊ�� 
	}
	
	@Test
	public void testFindById() throws Exception{     
		  Score score = this.scoreService.findById(3);
		  System.out.println("��������ɼ���:"+score);
	}
	
	
	@Test
	public void testDoCreate()throws Exception{   
		 Score score=new Score()  ;
		 Student student=new Student()  ;
		 student.setStudentId("2014888") ;
		 Course course=new Course()   ;
		 course.setCourseId(18) ;
		 score.setCourse(course);     //1�ųɼ���Ӧ1�ſγ�
		 score.setStudent(student);  //1�ųɼ���Ӧ1λѧ��
		 score.setScore(100) ;      //���÷��� 
		 Integer count = this.scoreService.doCreate(score)  ;  //���ӷ���
		 System.out.println("���������������������������:"+count);
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		  Score score=this.scoreService.findById(2)  ;  //���ҳɼ�
		  score.setScore(99) ;
		  Integer count=this.scoreService.doUpdate(score)  ;
		  System.out.println("�������������������:"+count);
	}
	
	@Test
	public void testDoRemove() throws Exception{
		   Integer count=0;
		   count=this.scoreService.doRemove(2) ;
		   System.out.println("����������ɾ��������:"+count);
	}
	
	@SuppressWarnings(value={"unchecked"})
	@Test
	public void testFindAll()throws Exception{
		    Map<String, Object> map = this.scoreService.findAll(1, 10, "linux");  
		    List<Score> scores=(List<Score>) map.get("scores");
		    PageInfo<Score> pageInfo=(PageInfo<Score>) map.get("pageInfo")   ;   
		    System.out.println("�����������ҳ��Ϣ������������");
		    System.out.println("ҳ��:"+pageInfo.getPageNum());
		    System.out.println("ÿҳ��ʾ������:"+pageInfo.getPageSize());
		    System.out.println("ÿҳʵ����ʾ������:"+pageInfo.getSize());
		    System.out.println("��������:"+pageInfo.getTotal());
		    System.out.println("��ҳ��:"+pageInfo.getPages());
		    System.out.println("�����������ҳ��Ϣ������������");
		    System.out.println("����������������ϡ�����������");
		    for(Score score : scores){
		    	System.out.println(score);
		    }
		    System.out.println("����������������ϡ�����������");
		    
	}
	
	
	@Test
	public void testDoCreateBatch() throws Exception{
		  Score score=new Score() ;
		  Student student=new Student() ;
		  Course  course=new Course() ;
		  score.setScore(66) ;
		  student.setStudentId("2014888");
		  course.setCourseId(16) ;
		  
		  score.setStudent(student);   //1�гɼ���Ӧ1��ѧ��
		  score.setCourse(course);     //1�гɼ���Ӧ1�ſγ�
          		  
		  List<Score> scores=Arrays.asList(score)  ;  //�ɼ��б�
		  Integer count = this.scoreService.doCreateBatch(scores)   ;  //��������
		  System.out.println("�����������������������������:"+count);
		  
	}
	
	@Test
	public void testFindByStudentIdAndTerm()throws Exception{
		    Map<String,Object> map=new HashMap<String, Object>()  ;  //��������
		    map.put("studentId", "2014888") ;   //ѧ��Ϊ2014888
		    map.put("term", 8)   ;    //ѧ��Ϊ��һѧ��
		    List<Score> scores = this.scoreService.findByStudentIdAndTerm(map);
		    System.out.println(scores);
		    System.out.println(scores.size());
		    System.out.println("����������������ϡ�����������");
		    for(Score score : scores){
		    	System.out.println(score);
		    }
		    System.out.println("����������������ϡ�����������");
	}
	
	

}
