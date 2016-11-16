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
	    private ICourseService courseService   ;    //注入Service
	    
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
	    				                      .findAll(currentPage, lineSize, keyWord) ;  //复杂查询
	    		   PageInfo<Course> pageInfo=(PageInfo<Course>) map.get("pageInfo")  ;   //取得分页信息 
	    		   List<Course> courses=(List<Course>) map.get("courses");   //取得全部课程 
	    		   mv.addObject("pageInfo", pageInfo)  ;   //增加PageInfo模型
	    		   mv.addObject("courses", courses)   ;    //增加Courses模型
	    		   mv.addObject("keyWord", keyWord)   ;   //增加KeyWord关键词 
	    		   mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ;
	    	   }catch(Exception e){
	    		    mv.setViewName("err") ;    //出现异常就跳转到错误页
	    	   }
	    	   return mv ;
	    }
	    
	    
	    @RequestMapping("/doCreatePre")
	    public ModelAndView doCreatePre(){
	    	   ModelAndView mv=new ModelAndView("course_insert")  ;
	    	   Map<Integer,String> termMap=new HashMap<Integer,String>() ;
	    	   termMap.put(1, "第1学期")  ;
	    	   termMap.put(2, "第2学期")  ;
	    	   termMap.put(3, "第3学期")  ;
	    	   termMap.put(4, "第4学期")  ;
	    	   termMap.put(5, "第5学期")   ;
	    	   termMap.put(6, "第6学期")   ;
	    	   termMap.put(7, "第7学期")   ;
	    	   termMap.put(8, "第8学期")   ;
	    	   List<Integer> credits=Arrays.asList(1,2,3,4,5,15,25)   ;   //最高学分就毕业设计25分
	    	   mv.addObject("termMap", termMap)  ;   //加装数据模型
	    	   mv.addObject("credits", credits)  ;   //加装数据模型
	    	   return mv   ;
	    }
	    
	    
	    //URL解析:/ZZUPrj/Course/doCreate
	    @RequestMapping("/doCreate")
	    public String doCreate(Course course){
	    	   String page="redirect:/Course/list"   ;
	    	   try{
	    		   Integer count= 0 ;   
	    		   count=this.courseService.doCreate(course)  ;  //增加课程
	    		   if(count==0){
	    			   throw new Exception()  ;    //程序出现问题,异常直接抛出 
	    		   }
	    	   }catch(Exception e){
	    		     page="err"   ;    //出现异常跳转到错误页 
	    	   }
	    	   return page ;
	    }
	    
	    
	    //URL解析:http://localhost/ZZUPrj/Course/findDetailsById/2
	    @RequestMapping("/findDetailsById/{courseId}")
	    public ModelAndView findDetailsById(@PathVariable("courseId") Integer courseId){
	    	   ModelAndView mv=new ModelAndView("course_details")  ;
	    	   try{
	    		   Course course=this.courseService.findById(courseId) ;  //根据课程ID查找课程信息(详细)
	    		   mv.addObject("course", course)  ;   //增加课程模型 
	    	   }catch(Exception e){
	    		     mv.setViewName("err") ;   //出现异常就要跳转到错误页 
	    	   }
	    	   return mv ;
	    }
	    
	    //URL解析:http://localhost/ZZUPrj/Course/doBatchCreatePre
	    @RequestMapping("/doBatchCreatePre")
	    public String doBatchCreatePre(HttpServletRequest req){
	    	   String page="course_batch_insert"  ;      //批量上传的页面
	    	   return page  ;
	    }
	    
	    //req.getServletContext().getRealPath("/upload/courseTemplate.xls")
	    //URL解析:
	    @RequestMapping("/downloadTemplate")
	    public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest HttpReq){
	    	byte[] body=null;
	        String path=HttpReq.getServletContext().getRealPath("/upload/courseTemplate.xls")  ;   //取得XLS文件位置
	        try{
	        	InputStream in=new FileInputStream(path) ;   //创建输入流
		        body=new byte[in.available()];               //保存文件
		        in.read(body);                               //读取文件
		        in.close();                     //读取结束就关闭,释放资源 
	        }catch(Exception e){
	        	e.printStackTrace();   //暂时测试程序打印异常
	        }
	        
	        HttpHeaders headers=new HttpHeaders();
	        //响应头的名字和响应头的值
	        headers.add("Content-Disposition", "attachment;filename=courseTemplate.xls");
	        HttpStatus statusCode=HttpStatus.OK;
	        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	        return response;
	    }
	    
	    
	    //URL解析:${pageContext.request.contextPath}/Course/doBatchCreate
	    @RequestMapping("/doBatchCreate")
	    public String doBatchCreate(@RequestParam("dataFile") MultipartFile dataFile){
	    	   String page="redirect:/Course/list"  ;
	    	   try{
	    		   InputStream input = dataFile.getInputStream();  //输入流
	    		   List<Course> courses = this.excelUtils.readAsCourseList(input)  ; //读取课程信息
	    		   this.courseService.doCreateBatch(courses) ;  //批量增加
	    	   }catch(Exception e){
	    		   e.printStackTrace();
	    		    page="err"   ;   //出现异常跳转到错误页
	    	   }
	    	   return page  ;
	    }
	    
	   //URL解析:http://localhost/ZZUPrj/Course/doEdit/25
	   @RequestMapping("/doEdit/{courseId}")
	   public ModelAndView doEdit(@PathVariable("courseId") Integer courseId){
		      ModelAndView mv=new ModelAndView("course_update") ;
		      try{
		    	  Course course = this.courseService.findById(courseId) ;   //根据ID查询课程
		    	  if(course==null){
		    		  throw new Exception() ;   //查询不到,就跳转到错误页
		    	  }else{
		    		  mv.addObject("course", course)  ;   //添加课程模型
		    		  
		    		  Map<Integer,String> termMap=new HashMap<Integer,String>() ;
			    	   termMap.put(1, "第1学期")  ;
			    	   termMap.put(2, "第2学期")  ;
			    	   termMap.put(3, "第3学期")  ;
			    	   termMap.put(4, "第4学期")  ;
			    	   termMap.put(5, "第5学期")   ;
			    	   termMap.put(6, "第6学期")   ;
			    	   termMap.put(7, "第7学期")   ;
			    	   termMap.put(8, "第8学期")   ;
			    	   List<Integer> credits=Arrays.asList(1,2,3,4,5,15,25)   ;   //最高学分就毕业设计25分
			    	   mv.addObject("termMap", termMap)  ;   //加装数据模型
			    	   mv.addObject("credits", credits)  ;   //加装数据模型
		    	  }
		      }catch(Exception e){
		    	  mv.setViewName("err") ;   //出现异常就跳转到错误页
		      }
		      return mv ;
	   } 
	   
	   
	   //URL解析:/ZZUPrj/Course/doUpdate
	   @RequestMapping("/doUpdate")
	   public String doUpdate(Course course){
		      String page="redirect:/Course/list" ;
		      try{
		    	   Integer count=0;
		    	   count=this.courseService.doUpdate(course) ;      //执行更新
		    	   if(count==0){
		    		   throw new Exception()  ;    //增加失败,手工抛出异常,跳转到错误页
		    	   }
		    	 }catch(Exception e){
		    	   page="err"  ;     //出现异常就跳转到错误页 
		      }
		      return page ;
	   }
	   
	   //URL解析:http://localhost/ZZUPrj/Course/doRemove/25
	   @RequestMapping("/doRemove/{courseId}")
	   public String doRemove(@PathVariable("courseId") Integer courseId){
		       String page="redirect:/Course/list"  ;
		       try{
		    	   Integer count =0;
		    	   count=this.courseService.doRemove(courseId) ;//根据ID删除课程
		    	   if(count==0){
		    		   throw new Exception()  ;
		    	   }
		       }catch(Exception e){
		    	   page="err"  ;     //出现异常就跳转到错误页
		       }
		       return page   ;
	   }
	   
	
	   
	    
	
}
