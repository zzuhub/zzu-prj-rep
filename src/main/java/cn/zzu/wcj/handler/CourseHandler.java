package cn.zzu.wcj.handler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.service.ICourseService;
import cn.zzu.wcj.util.ExcelUtils;

import com.github.pagehelper.PageInfo;

@RequestMapping("/Course")
@Controller
public class CourseHandler {

	    @Autowired
	    private ICourseService courseService   ;    //ע��Service
	    
	    @Autowired
	    private ExcelUtils excelUtils ;
	    
	    @SuppressWarnings(value={"unchecked"})
	    @RequestMapping("/list")
	    public ModelAndView list(@RequestParam(name="currentPage",required=false,defaultValue="1") Integer currentPage,
	    		                 @RequestParam(name="lineSize",required=false,defaultValue="8") Integer lineSize  ,
	    		                 @RequestParam(name="keyWord",required=false,defaultValue="") String keyWord){
	    	   ModelAndView mv=new ModelAndView("course_list")  ;
	    	   try{
	    		   Map<String,Object> map=this.courseService
	    				                      .findAll(currentPage, lineSize, keyWord) ;  //���Ӳ�ѯ
	    		   PageInfo<Course> pageInfo=(PageInfo<Course>) map.get("pageInfo")  ;   //ȡ�÷�ҳ��Ϣ 
	    		   List<Course> courses=(List<Course>) map.get("courses");   //ȡ��ȫ���γ� 
	    		   mv.addObject("pageInfo", pageInfo)  ;   //����PageInfoģ��
	    		   mv.addObject("courses", courses)   ;    //����Coursesģ��
	    		   mv.addObject("keyWord", keyWord)   ;   //����KeyWord�ؼ��� 
	    		   mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ;
	    	   }catch(Exception e){
	    		    mv.setViewName("err") ;    //�����쳣����ת������ҳ
	    	   }
	    	   return mv ;
	    }
	    
	    
	    @RequestMapping("/doCreatePre")
	    public ModelAndView doCreatePre(){
	    	   ModelAndView mv=new ModelAndView("course_insert")  ;
	    	   Map<Integer,String> termMap=new HashMap<Integer,String>() ;
	    	   termMap.put(1, "��1ѧ��")  ;
	    	   termMap.put(2, "��2ѧ��")  ;
	    	   termMap.put(3, "��3ѧ��")  ;
	    	   termMap.put(4, "��4ѧ��")  ;
	    	   termMap.put(5, "��5ѧ��")   ;
	    	   termMap.put(6, "��6ѧ��")   ;
	    	   termMap.put(7, "��7ѧ��")   ;
	    	   termMap.put(8, "��8ѧ��")   ;
	    	   List<Integer> credits=Arrays.asList(1,2,3,4,5,15,25)   ;   //���ѧ�־ͱ�ҵ���25��
	    	   mv.addObject("termMap", termMap)  ;   //��װ����ģ��
	    	   mv.addObject("credits", credits)  ;   //��װ����ģ��
	    	   return mv   ;
	    }
	    
	    
	    //URL����:/ZZUPrj/Course/doCreate
	    @RequestMapping("/doCreate")
	    public String doCreate(Course course){
	    	   String page="redirect:/Course/list"   ;
	    	   try{
	    		   Integer count= 0 ;   
	    		   count=this.courseService.doCreate(course)  ;  //���ӿγ�
	    		   if(count==0){
	    			   throw new Exception()  ;    //�����������,�쳣ֱ���׳� 
	    		   }
	    	   }catch(Exception e){
	    		     page="err"   ;    //�����쳣��ת������ҳ 
	    	   }
	    	   return page ;
	    }
	    
	    
	    //URL����:http://localhost/ZZUPrj/Course/findDetailsById/2
	    @RequestMapping("/findDetailsById/{courseId}")
	    public ModelAndView findDetailsById(@PathVariable("courseId") Integer courseId){
	    	   ModelAndView mv=new ModelAndView("course_details")  ;
	    	   try{
	    		   Course course=this.courseService.findById(courseId) ;  //���ݿγ�ID���ҿγ���Ϣ(��ϸ)
	    		   mv.addObject("course", course)  ;   //���ӿγ�ģ�� 
	    	   }catch(Exception e){
	    		     mv.setViewName("err") ;   //�����쳣��Ҫ��ת������ҳ 
	    	   }
	    	   return mv ;
	    }
	    
	    //URL����:http://localhost/ZZUPrj/Course/doBatchCreatePre
	    @RequestMapping("/doBatchCreatePre")
	    public String doBatchCreatePre(HttpServletRequest req){
	    	   String page="course_batch_insert"  ;      //�����ϴ���ҳ��
	    	   return page  ;
	    }
	    
	    //req.getServletContext().getRealPath("/upload/courseTemplate.xls")
	    //URL����:
	    @RequestMapping("/downloadTemplate")
	    public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest HttpReq){
	    	byte[] body=null;
	        String path=HttpReq.getServletContext().getRealPath("/upload/courseTemplate.xls")  ;   //ȡ��XLS�ļ�λ��
	        try{
	        	InputStream in=new FileInputStream(path) ;   //����������
		        body=new byte[in.available()];               //�����ļ�
		        in.read(body);                               //��ȡ�ļ�
		        in.close();                     //��ȡ�����͹ر�,�ͷ���Դ 
	        }catch(Exception e){
	        	e.printStackTrace();   //��ʱ���Գ����ӡ�쳣
	        }
	        
	        HttpHeaders headers=new HttpHeaders();
	        //��Ӧͷ�����ֺ���Ӧͷ��ֵ
	        headers.add("Content-Disposition", "attachment;filename=courseTemplate.xls");
	        HttpStatus statusCode=HttpStatus.OK;
	        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	        return response;
	    }
	    
	    
	    //URL����:${pageContext.request.contextPath}/Course/doBatchCreate
	    @RequestMapping("/doBatchCreate")
	    public String doBatchCreate(@RequestParam("dataFile") MultipartFile dataFile){
	    	   String page="redirect:/Course/list"  ;
	    	   try{
	    		   InputStream input = dataFile.getInputStream();  //������
	    		   List<Course> courses = this.excelUtils.readAsCourseList(input)  ; //��ȡ�γ���Ϣ
	    		   this.courseService.doCreateBatch(courses) ;  //��������
	    	   }catch(Exception e){
	    		   e.printStackTrace();
	    		    page="err"   ;   //�����쳣��ת������ҳ
	    	   }
	    	   return page  ;
	    }
	    
	   //URL����:http://localhost/ZZUPrj/Course/doEdit/25
	   @RequestMapping("/doEdit/{courseId}")
	   public ModelAndView doEdit(@PathVariable("courseId") Integer courseId){
		      ModelAndView mv=new ModelAndView("course_update") ;
		      try{
		    	  Course course = this.courseService.findById(courseId) ;   //����ID��ѯ�γ�
		    	  if(course==null){
		    		  throw new Exception() ;   //��ѯ����,����ת������ҳ
		    	  }else{
		    		  mv.addObject("course", course)  ;   //��ӿγ�ģ��
		    		  
		    		  Map<Integer,String> termMap=new HashMap<Integer,String>() ;
			    	   termMap.put(1, "��1ѧ��")  ;
			    	   termMap.put(2, "��2ѧ��")  ;
			    	   termMap.put(3, "��3ѧ��")  ;
			    	   termMap.put(4, "��4ѧ��")  ;
			    	   termMap.put(5, "��5ѧ��")   ;
			    	   termMap.put(6, "��6ѧ��")   ;
			    	   termMap.put(7, "��7ѧ��")   ;
			    	   termMap.put(8, "��8ѧ��")   ;
			    	   List<Integer> credits=Arrays.asList(1,2,3,4,5,15,25)   ;   //���ѧ�־ͱ�ҵ���25��
			    	   mv.addObject("termMap", termMap)  ;   //��װ����ģ��
			    	   mv.addObject("credits", credits)  ;   //��װ����ģ��
		    	  }
		      }catch(Exception e){
		    	  mv.setViewName("err") ;   //�����쳣����ת������ҳ
		      }
		      return mv ;
	   } 
	   
	   
	   //URL����:/ZZUPrj/Course/doUpdate
	   @RequestMapping("/doUpdate")
	   public String doUpdate(Course course){
		      String page="redirect:/Course/list" ;
		      try{
		    	   Integer count=0;
		    	   count=this.courseService.doUpdate(course) ;      //ִ�и���
		    	   if(count==0){
		    		   throw new Exception()  ;    //����ʧ��,�ֹ��׳��쳣,��ת������ҳ
		    	   }
		    	 }catch(Exception e){
		    	   page="err"  ;     //�����쳣����ת������ҳ 
		      }
		      return page ;
	   }
	   
	   //URL����:http://localhost/ZZUPrj/Course/doRemove/25
	   @RequestMapping("/doRemove/{courseId}")
	   public String doRemove(@PathVariable("courseId") Integer courseId){
		       String page="redirect:/Course/list"  ;
		       try{
		    	   Integer count =0;
		    	   count=this.courseService.doRemove(courseId) ;//����IDɾ���γ�
		    	   if(count==0){
		    		   throw new Exception()  ;
		    	   }
		       }catch(Exception e){
		    	   page="err"  ;     //�����쳣����ת������ҳ
		       }
		       return page   ;
	   }
	   
	
	   
	    
	
}
