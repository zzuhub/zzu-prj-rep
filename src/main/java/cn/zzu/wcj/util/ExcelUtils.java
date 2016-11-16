package cn.zzu.wcj.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.entity.Score;
import cn.zzu.wcj.entity.Student;

@Component
public class ExcelUtils {

	 public List<Course> readAsCourseList(InputStream input)throws Exception{
		    List<Course> courses=new ArrayList<Course>()    ;   //保存课程
		    HSSFWorkbook wb=new HSSFWorkbook(input)   ;      //创建工作簿
		    HSSFSheet sheet=wb.getSheetAt(0)  ;             //取得sheet页
		    for(int x=1;x<=sheet.getLastRowNum();x++){   //从第二行开始,第一行是标题
		    	 HSSFRow row=sheet.getRow(x)  ;   //取得每行 
		    	 Cell courseNameCell=row.getCell(0)  ;   //取得课程名
		    	 Cell noteCell=row.getCell(1)   ;    //取得课程简介 
		    	 Cell termCell=row.getCell(2)   ;       //取得学期
		    	 Cell creditCell=row.getCell(3) ;       //取得学分
		    	 Course course=new Course()  ;
		    	 course.setCourseName(getStringByCellType(courseNameCell)) ;
		    	 course.setNote(getStringByCellType(noteCell))  ;
		    	 course.setTerm(Integer.parseInt(getStringByCellType(termCell))) ;
		    	 course.setCredit(Integer.parseInt(getStringByCellType(creditCell))) ;
		    	 courses.add(course)  ;   //增加课程 
		    }
		    input.close() ;   //关闭流 
		    wb.close();     //关闭工作簿 
		    return courses ;
	 }
	 
	 public List<Student> readAsStudentList(InputStream input)throws Exception{
		    List<Student> students=new ArrayList<Student>() ;  //保存学生列表 
		    HSSFWorkbook workBook=new HSSFWorkbook(input)  ;     //创建工作簿
		    HSSFSheet sheet=workBook.getSheetAt(0)   ;          //取得第1页
		    for(int x=1;x<=sheet.getLastRowNum();x++){   //从第二行开始,第一行是标题
		    	HSSFRow row=sheet.getRow(x)   ;          //根据行号获取每一行
		    	Cell studentIdCell=row.getCell(0)  ;     //取得第一个单元格:学号
		    	Cell studentNameCell=row.getCell(1) ;    //取得第二个单元格:姓名
		    	Cell sexCell=row.getCell(2)     ;        //取得第三个单元格:性别
		    	Cell birthCell=row.getCell(3)   ;        //取得第四个单元格:出生日期
		    	Cell passwordCell=row.getCell(4) ;       //取得第五个单元格:密码
		    	Cell telCell=row.getCell(5)  ;          //取得第六个单元格:电话
		    	Cell photoCell=row.getCell(6) ;         //取得第七个单元格:照片
		    	Cell addressCell=row.getCell(7)  ;      //取得第八个单元格:地址
		    	Cell noteCell=row.getCell(8)   ;        //取得第九个单元格:简介
		    	Cell clazzIdCell=row.getCell(9) ;       //取得第十个单元格:班级代码
		    	Student student=new Student()  ;    //实例化学生
		    	student.setStudentId(this.getStringByCellType(studentIdCell));  //设置学号
		    	student.setStudentName(this.getStringByCellType(studentNameCell));  //设置姓名
		    	student.setSex(this.getStringByCellType(sexCell)) ;   //设置性别
		    	student.setBirth(new SimpleDateFormat("yyyy-MM-dd")
		    	                     .parse(this.getStringByCellType(birthCell))) ; //设置出生日期
		    	student.setPassword(this.getStringByCellType(passwordCell)) ;  //设置密码
		    	student.setTel(this.getStringByCellType(telCell)) ;   //设置联系电话
		    	student.setPhoto(this.getStringByCellType(photoCell)) ;   //设置照片
		    	student.setAddress(this.getStringByCellType(addressCell)) ;   //设置地址
		    	student.setNote(this.getStringByCellType(noteCell))  ;   //设置简介
		    	student.setClazzId(Integer.parseInt(this.getStringByCellType(clazzIdCell))) ;//设置班级代码
		    	//不必设置age属性,属性age由Oracle函数结合birth属性自动生成
		    	students.add(student)  ;    //增加学生
		    }
		    input.close() ;   //关闭流 
		    workBook.close();     //关闭工作簿 
		    return students  ;
	 }
	 
	 
	 public List<Score> readAsScoreList(InputStream input)throws Exception{
		    List<Score> scores=new ArrayList<Score>()  ;   //保存成绩
		    HSSFWorkbook workBook=new HSSFWorkbook(input)  ;   //创建工作簿
		    HSSFSheet sheet=workBook.getSheetAt(0)  ;        //取得第1页
		    for(int x=1;x<=sheet.getLastRowNum();x++){   //第一行是标题,不读取
		    	HSSFRow row=sheet.getRow(x)  ;    //取得一行
		    	Cell studentIdCell=row.getCell(0) ;  //取得第一个单元格:studentId
		    	Cell courseIdCell=row.getCell(1)  ;  //取得第二个单元格:courseId
		    	Cell scoreCell=row.getCell(2)   ;    //取得第三个单元格:scoreCell
		    	String studentId=this.getStringByCellType(studentIdCell) ;  //取得格式化后的学号
		    	Integer courseId=Integer
		    			              .parseInt(this.getStringByCellType(courseIdCell)) ; //格式化课程号
		    	Integer score=Integer
		    			            .parseInt(this.getStringByCellType(scoreCell)) ;  //格式化分数
		    	Student student=new Student() ;     //创建学生对象
		    	Course course=new Course() ;         //创建课程对象
		    	Score scoreObj=new Score()    ;         //创建成绩对象
                student.setStudentId(studentId);      //设置学号 
                course.setCourseId(courseId);         //设置课程号
                scoreObj.setScore(score);             //设置分数
                scoreObj.setStudent(student) ;    //1个成绩对应1位学生
                scoreObj.setCourse(course);       //1个成绩对应1门课程
                scores.add(scoreObj)  ;     //向分数列表中添加分数对象
		    }
		    input.close() ;   //关闭流 
		    workBook.close();     //关闭工作簿 
		    return scores ;
	 }
	 
	 
	    
	public  String getStringByCellType(Cell cell){
		      String target=null ;   //默认字符串
		      if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType()){         //布尔型
		    	  target=String.valueOf(cell.getBooleanCellValue() )  ;
		      }else if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()){      
		    	   if(HSSFDateUtil.isCellDateFormatted(cell)){   //日期型
		    		   target=new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
		    	   }else{                                        //数值型
		    		   target=new DecimalFormat("##.##").format(cell.getNumericCellValue());
		    	   }
		      }else{                                                       //字符串型
		    	  target=cell.getStringCellValue() ;
		      }
		      return target   ;
	   }  
	  
	 
	
}
