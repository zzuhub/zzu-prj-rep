package cn.zzu.wcj.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zzu.wcj.entity.Clazz;
import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.entity.Teach;
import cn.zzu.wcj.entity.Teacher;
import cn.zzu.wcj.service.IClazzService;
import cn.zzu.wcj.service.ICourseService;
import cn.zzu.wcj.service.IMajorService;
import cn.zzu.wcj.service.ITeachService;
import cn.zzu.wcj.service.ITeacherService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

@RequestMapping("/Teach")
@Controller
public class TeachHandler {
  
	  @Autowired
	  private ITeachService teachService  ;
	  
	  @Autowired
	  private ITeacherService teacherService   ;
	  
	  @Autowired
	  private ICourseService courseService   ;
	  
	  @Autowired
	  private IMajorService majorService   ;
	  
	  @Autowired
	  private IClazzService clazzService   ;
	  
	  
	
	  /**
	   * 交代:分页数组还没有设置,2016/11/6号继续完成代码
	   * 交接:2016/11/6继续写代码
	   * @param currentPage 当前页
	   * @param lineSize 每页显示的数据量 
	   * @return
	   */
	  //URL解析:http://localhost/ZZUPrj/Teach/list
	  @SuppressWarnings("unchecked")
	  @RequestMapping("/list")
	  public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
			                   @RequestParam(name="lineSize",defaultValue="5")Integer lineSize){
		     ModelAndView mv=new ModelAndView("teach_list")  ;
		     try{
		    	 Map<String, Object> map = this.teachService.findAll(currentPage, lineSize)  ;
				 List<Teach> teachs=(List<Teach>) map.get("teachs");
		    	 PageInfo<Teach> pageInfo=null ;
		    	 pageInfo=new PageInfo<Teach>(teachs)  ;
		    	 mv.addObject("teachs", teachs)    ;   //授课列表
		    	 mv.addObject("pageInfo", pageInfo)  ;    //分页信息
		    	 mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ; //每页显示的数据量
		     }catch(Exception e){
		    	  mv.setViewName("err")  ;    //设置错误页
		     }
		     return mv ;
	  }
	  
	  
	//URL解析:http://localhost/ZZUPrj/Teach/doCreatePre
	@RequestMapping("/doCreatePre")
	  public ModelAndView doCreatePre(){
		     ModelAndView mv=new ModelAndView("teach_insert") ;
		     try{
		    	   Map<Integer,String> termMap=new HashMap<Integer,String>() ;
		    	   termMap.put(1, "第1学期")  ;
		    	   termMap.put(2, "第2学期")  ;
		    	   termMap.put(3, "第3学期")  ;
		    	   termMap.put(4, "第4学期")  ;
		    	   termMap.put(5, "第5学期")   ;
		    	   termMap.put(6, "第6学期")   ;
		    	   termMap.put(7, "第7学期")   ;
		    	   termMap.put(8, "第8学期")   ;
		    	   mv.addObject("termMap", termMap)  ;   //加装数据模型
		    	   Map<String, Object> map = this.majorService.findAll(1, 0, "")   ;   //查找全部专业和分页信息 
		    	   mv.addObject("majors", map.get("majors"))  ;
		     }catch(Exception e){
		    	  mv.setViewName("err")  ;   //出现异常就跳转到错误页
		     }
		     return  mv ;
	  }
	  
	  
	 //URL解析:ZZUPrj/Teach/validateTeacherExist
     @ResponseBody
	 @RequestMapping("/validateTeacherExist")
	 public String validateTeacherExist(@RequestParam("teacherId")Integer teacherId){
		   String data=null ;
		   try{
			   Teacher teacher = this.teacherService.findById(teacherId)  ;   //根据ID查找教师信息
			   data=new ObjectMapper().writeValueAsString(teacher)  ;     //将数据打包成JSON格式 
		   }catch(Exception e){
			    data="{\"err\":\"err\"}"   ;
		   }
		   return data ;
	 }
	  
	 //URL解析:/ZZUPrj/Term/getCoursesByTerm
     @RequestMapping("/getCoursesByTerm")
     @ResponseBody
     public String getCoursesByTerm(@RequestParam("term")Integer term){
    	    String jsonStr=null ;
    	    try{
    	    	List<Course> courses=this.courseService.findByTerm(term)  ; //找到课程
    	    	jsonStr=new ObjectMapper().writeValueAsString(courses)   ;  
    	    }catch(Exception e){
    	    	jsonStr="{\"err\":\"err\"}"   ;   //错误信息 
    	    }
    	    return jsonStr ;
     }
	  
	 //URL地址解析:/ZZUPrj/Teach/getClazzsByMajorId 
	 @RequestMapping("/getClazzsByMajorId ")
	 @ResponseBody
	 public String getClazzsByMajorId(@RequestParam("majorId")Integer majorId){
		    String jsonStr =null;
		    try{ 
		    	List<Clazz> clazzs=this.clazzService.findClazzsByMajorId(majorId);
		    	jsonStr=new ObjectMapper().writeValueAsString(clazzs)  ;
		    }catch(Exception e){
		    	jsonStr="{\"err\":\"err\"}"   ;   //错误信息 
		    }
		    return jsonStr ;
	 }
	  
	//URL解析:/ZZUPrj/Teach/doCreate
    @RequestMapping("/doCreate")
	public String doCreate(Teach teach){
    	   String page="redirect:/Teach/list"  ;
    	   try{
    		    Integer count=0 ;
    		    count=this.teachService.doCreate(teach) ;
    		    if(count==0){
    		    	throw new Exception()  ;    //增加失败直接抛出异常
    		    }
    	   }catch(Exception e){
    		     page="err"   ;
    	   }
    	   return page ;
    } 
    
    //URL解析:http://localhost/ZZUPrj/Teach/doEdit/3
    @RequestMapping("/doEdit/{teachId}")
    public ModelAndView doEdit(@PathVariable("teachId")Integer teachId){
    	   ModelAndView mv=new ModelAndView("teach_update")  ;
    	   try{
    		      Teach teach = this.teachService.findById(teachId); //根据ID查询
    		      if(teach==null){
    		    	   throw new Exception() ;   //教学记录不存在,直接抛出异常
    		      }else{
    		    	   mv.addObject("teach", teach)    ;  //增加教学模型 
    		      }
    		       Map<Integer,String> termMap=new HashMap<Integer,String>() ;
		    	   termMap.put(1, "第1学期")  ;
		    	   termMap.put(2, "第2学期")  ;
		    	   termMap.put(3, "第3学期")  ;
		    	   termMap.put(4, "第4学期")  ;
		    	   termMap.put(5, "第5学期")   ;
		    	   termMap.put(6, "第6学期")   ;
		    	   termMap.put(7, "第7学期")   ;
		    	   termMap.put(8, "第8学期")   ;
		    	   mv.addObject("termMap", termMap)  ;   //加装数据模型
		    	   Map<String, Object> map = this.majorService.findAll(1, 0, "")   ;   //查找全部专业和分页信息 
		    	   mv.addObject("majors", map.get("majors"))  ;
    	   }catch(Exception e){
    		       mv.setViewName("err")  ;    //出现异常就跳转到错误页面 
    	   }
    	   return mv ;
    }
    
    //URL解析:/ZZUPrj/Teach/doUpdate
    @RequestMapping("/doUpdate")
    public String doUpdate(Teach teach){
    	   String page="redirect:/Teach/list"  ;
    	   try{
    		   Integer count=0 ;
    		   count=this.teachService.doUpdate(teach) ;  //修改
    		   if(count==0){
    			    throw new Exception()  ;   //修改失败,直接抛出异常
    		   }
    	   }catch(Exception e){
    		   page="err"  ;        //出现异常直接跳转到错误页 
    	   }
    	   return page ;
    }
    
   //URL解析:http://localhost/ZZUPrj/Teach/doRemove/3
    @RequestMapping("/doRemove/{teachId}")
    public String doRemove(@PathVariable("teachId")Integer teachId){
    	  String page="redirect:/Teach/list"  ;
    	  try{
    		   Integer count=0 ;
    		   count=this.teachService.doRemove(teachId) ;  //根据ID删除
    		   if(count==0){
    			   throw new Exception() ;   //删除失败,直接跳出
    		   }
    	  }catch(Exception e){
    		    page="err"  ;       //程序出现错误就跳转到错误页
    	  }
    	  return page ;
    }
    
	         
}
