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
		    List<Course> courses=new ArrayList<Course>()    ;   //����γ�
		    HSSFWorkbook wb=new HSSFWorkbook(input)   ;      //����������
		    HSSFSheet sheet=wb.getSheetAt(0)  ;             //ȡ��sheetҳ
		    for(int x=1;x<=sheet.getLastRowNum();x++){   //�ӵڶ��п�ʼ,��һ���Ǳ���
		    	 HSSFRow row=sheet.getRow(x)  ;   //ȡ��ÿ�� 
		    	 Cell courseNameCell=row.getCell(0)  ;   //ȡ�ÿγ���
		    	 Cell noteCell=row.getCell(1)   ;    //ȡ�ÿγ̼�� 
		    	 Cell termCell=row.getCell(2)   ;       //ȡ��ѧ��
		    	 Cell creditCell=row.getCell(3) ;       //ȡ��ѧ��
		    	 Course course=new Course()  ;
		    	 course.setCourseName(getStringByCellType(courseNameCell)) ;
		    	 course.setNote(getStringByCellType(noteCell))  ;
		    	 course.setTerm(Integer.parseInt(getStringByCellType(termCell))) ;
		    	 course.setCredit(Integer.parseInt(getStringByCellType(creditCell))) ;
		    	 courses.add(course)  ;   //���ӿγ� 
		    }
		    input.close() ;   //�ر��� 
		    wb.close();     //�رչ����� 
		    return courses ;
	 }
	 
	 public List<Student> readAsStudentList(InputStream input)throws Exception{
		    List<Student> students=new ArrayList<Student>() ;  //����ѧ���б� 
		    HSSFWorkbook workBook=new HSSFWorkbook(input)  ;     //����������
		    HSSFSheet sheet=workBook.getSheetAt(0)   ;          //ȡ�õ�1ҳ
		    for(int x=1;x<=sheet.getLastRowNum();x++){   //�ӵڶ��п�ʼ,��һ���Ǳ���
		    	HSSFRow row=sheet.getRow(x)   ;          //�����кŻ�ȡÿһ��
		    	Cell studentIdCell=row.getCell(0)  ;     //ȡ�õ�һ����Ԫ��:ѧ��
		    	Cell studentNameCell=row.getCell(1) ;    //ȡ�õڶ�����Ԫ��:����
		    	Cell sexCell=row.getCell(2)     ;        //ȡ�õ�������Ԫ��:�Ա�
		    	Cell birthCell=row.getCell(3)   ;        //ȡ�õ��ĸ���Ԫ��:��������
		    	Cell passwordCell=row.getCell(4) ;       //ȡ�õ������Ԫ��:����
		    	Cell telCell=row.getCell(5)  ;          //ȡ�õ�������Ԫ��:�绰
		    	Cell photoCell=row.getCell(6) ;         //ȡ�õ��߸���Ԫ��:��Ƭ
		    	Cell addressCell=row.getCell(7)  ;      //ȡ�õڰ˸���Ԫ��:��ַ
		    	Cell noteCell=row.getCell(8)   ;        //ȡ�õھŸ���Ԫ��:���
		    	Cell clazzIdCell=row.getCell(9) ;       //ȡ�õ�ʮ����Ԫ��:�༶����
		    	Student student=new Student()  ;    //ʵ����ѧ��
		    	student.setStudentId(this.getStringByCellType(studentIdCell));  //����ѧ��
		    	student.setStudentName(this.getStringByCellType(studentNameCell));  //��������
		    	student.setSex(this.getStringByCellType(sexCell)) ;   //�����Ա�
		    	student.setBirth(new SimpleDateFormat("yyyy-MM-dd")
		    	                     .parse(this.getStringByCellType(birthCell))) ; //���ó�������
		    	student.setPassword(this.getStringByCellType(passwordCell)) ;  //��������
		    	student.setTel(this.getStringByCellType(telCell)) ;   //������ϵ�绰
		    	student.setPhoto(this.getStringByCellType(photoCell)) ;   //������Ƭ
		    	student.setAddress(this.getStringByCellType(addressCell)) ;   //���õ�ַ
		    	student.setNote(this.getStringByCellType(noteCell))  ;   //���ü��
		    	student.setClazzId(Integer.parseInt(this.getStringByCellType(clazzIdCell))) ;//���ð༶����
		    	//��������age����,����age��Oracle�������birth�����Զ�����
		    	students.add(student)  ;    //����ѧ��
		    }
		    input.close() ;   //�ر��� 
		    workBook.close();     //�رչ����� 
		    return students  ;
	 }
	 
	 
	 public List<Score> readAsScoreList(InputStream input)throws Exception{
		    List<Score> scores=new ArrayList<Score>()  ;   //����ɼ�
		    HSSFWorkbook workBook=new HSSFWorkbook(input)  ;   //����������
		    HSSFSheet sheet=workBook.getSheetAt(0)  ;        //ȡ�õ�1ҳ
		    for(int x=1;x<=sheet.getLastRowNum();x++){   //��һ���Ǳ���,����ȡ
		    	HSSFRow row=sheet.getRow(x)  ;    //ȡ��һ��
		    	Cell studentIdCell=row.getCell(0) ;  //ȡ�õ�һ����Ԫ��:studentId
		    	Cell courseIdCell=row.getCell(1)  ;  //ȡ�õڶ�����Ԫ��:courseId
		    	Cell scoreCell=row.getCell(2)   ;    //ȡ�õ�������Ԫ��:scoreCell
		    	String studentId=this.getStringByCellType(studentIdCell) ;  //ȡ�ø�ʽ�����ѧ��
		    	Integer courseId=Integer
		    			              .parseInt(this.getStringByCellType(courseIdCell)) ; //��ʽ���γ̺�
		    	Integer score=Integer
		    			            .parseInt(this.getStringByCellType(scoreCell)) ;  //��ʽ������
		    	Student student=new Student() ;     //����ѧ������
		    	Course course=new Course() ;         //�����γ̶���
		    	Score scoreObj=new Score()    ;         //�����ɼ�����
                student.setStudentId(studentId);      //����ѧ�� 
                course.setCourseId(courseId);         //���ÿγ̺�
                scoreObj.setScore(score);             //���÷���
                scoreObj.setStudent(student) ;    //1���ɼ���Ӧ1λѧ��
                scoreObj.setCourse(course);       //1���ɼ���Ӧ1�ſγ�
                scores.add(scoreObj)  ;     //������б�����ӷ�������
		    }
		    input.close() ;   //�ر��� 
		    workBook.close();     //�رչ����� 
		    return scores ;
	 }
	 
	 
	    
	public  String getStringByCellType(Cell cell){
		      String target=null ;   //Ĭ���ַ���
		      if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType()){         //������
		    	  target=String.valueOf(cell.getBooleanCellValue() )  ;
		      }else if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()){      
		    	   if(HSSFDateUtil.isCellDateFormatted(cell)){   //������
		    		   target=new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
		    	   }else{                                        //��ֵ��
		    		   target=new DecimalFormat("##.##").format(cell.getNumericCellValue());
		    	   }
		      }else{                                                       //�ַ�����
		    	  target=cell.getStringCellValue() ;
		      }
		      return target   ;
	   }  
	  
	 
	
}
