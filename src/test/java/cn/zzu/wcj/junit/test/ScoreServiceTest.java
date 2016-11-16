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
		 assertNotNull(scoreService);     //优先测试是否为空 
	}
	
	@Test
	public void testFindById() throws Exception{     
		  Score score = this.scoreService.findById(3);
		  System.out.println("☆☆☆☆☆☆☆成绩单:"+score);
	}
	
	
	@Test
	public void testDoCreate()throws Exception{   
		 Score score=new Score()  ;
		 Student student=new Student()  ;
		 student.setStudentId("2014888") ;
		 Course course=new Course()   ;
		 course.setCourseId(18) ;
		 score.setCourse(course);     //1门成绩对应1门课程
		 score.setStudent(student);  //1门成绩对应1位学生
		 score.setScore(100) ;      //设置分数 
		 Integer count = this.scoreService.doCreate(score)  ;  //增加分数
		 System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆增加数据量:"+count);
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		  Score score=this.scoreService.findById(2)  ;  //查找成绩
		  score.setScore(99) ;
		  Integer count=this.scoreService.doUpdate(score)  ;
		  System.out.println("☆☆☆☆☆☆☆☆☆更新数据量:"+count);
	}
	
	@Test
	public void testDoRemove() throws Exception{
		   Integer count=0;
		   count=this.scoreService.doRemove(2) ;
		   System.out.println("☆☆☆☆☆☆☆☆☆删除数据量:"+count);
	}
	
	@SuppressWarnings(value={"unchecked"})
	@Test
	public void testFindAll()throws Exception{
		    Map<String, Object> map = this.scoreService.findAll(1, 10, "linux");  
		    List<Score> scores=(List<Score>) map.get("scores");
		    PageInfo<Score> pageInfo=(PageInfo<Score>) map.get("pageInfo")   ;   
		    System.out.println("☆☆☆☆☆☆☆☆☆分页信息↓☆☆☆☆☆☆☆☆☆");
		    System.out.println("页码:"+pageInfo.getPageNum());
		    System.out.println("每页显示数据量:"+pageInfo.getPageSize());
		    System.out.println("每页实际显示数据量:"+pageInfo.getSize());
		    System.out.println("总数据量:"+pageInfo.getTotal());
		    System.out.println("总页数:"+pageInfo.getPages());
		    System.out.println("☆☆☆☆☆☆☆☆☆分页信息↑☆☆☆☆☆☆☆☆☆");
		    System.out.println("☆☆☆☆☆☆☆☆☆分数集合↓☆☆☆☆☆☆☆☆☆");
		    for(Score score : scores){
		    	System.out.println(score);
		    }
		    System.out.println("☆☆☆☆☆☆☆☆☆分数集合↑☆☆☆☆☆☆☆☆☆");
		    
	}
	
	
	@Test
	public void testDoCreateBatch() throws Exception{
		  Score score=new Score() ;
		  Student student=new Student() ;
		  Course  course=new Course() ;
		  score.setScore(66) ;
		  student.setStudentId("2014888");
		  course.setCourseId(16) ;
		  
		  score.setStudent(student);   //1行成绩对应1个学生
		  score.setCourse(course);     //1行成绩对应1门课程
          		  
		  List<Score> scores=Arrays.asList(score)  ;  //成绩列表
		  Integer count = this.scoreService.doCreateBatch(scores)   ;  //批量增加
		  System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆批量增加数据行数:"+count);
		  
	}
	
	@Test
	public void testFindByStudentIdAndTerm()throws Exception{
		    Map<String,Object> map=new HashMap<String, Object>()  ;  //保存多参数
		    map.put("studentId", "2014888") ;   //学号为2014888
		    map.put("term", 8)   ;    //学期为第一学期
		    List<Score> scores = this.scoreService.findByStudentIdAndTerm(map);
		    System.out.println(scores);
		    System.out.println(scores.size());
		    System.out.println("☆☆☆☆☆☆☆☆☆分数集合↓☆☆☆☆☆☆☆☆☆");
		    for(Score score : scores){
		    	System.out.println(score);
		    }
		    System.out.println("☆☆☆☆☆☆☆☆☆分数集合↑☆☆☆☆☆☆☆☆☆");
	}
	
	

}
