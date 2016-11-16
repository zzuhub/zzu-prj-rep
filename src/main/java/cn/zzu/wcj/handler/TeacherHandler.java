package cn.zzu.wcj.handler;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.zzu.wcj.entity.Teacher;
import cn.zzu.wcj.service.ITeacherService;


@RequestMapping("/Teacher")
@Controller
public class TeacherHandler {

	@Autowired
	private ITeacherService teacherService   ;
	
	//URL解析:http://localhost/ZZUPrj/Teacher/list
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1") Integer currentPage,
			                 @RequestParam(name="lineSize",defaultValue="5") Integer lineSize ,
			                 @RequestParam(name="keyWord",defaultValue="") String keyWord) 
			                 {
		   ModelAndView mv=new ModelAndView("teacher_list")  ;
	       Map<String,Object> map=null;
	       try{
	    	    map=this.teacherService.findAll(currentPage, lineSize, keyWord)   ;
	    	    mv.addObject("teachers", map.get("teachers"))   ;
	    	    /**
	    	     * 下面的分页参数一定要传过去,不然没法使用分页插件
	    	     */
	    	    mv.addObject("pageInfo", map.get("pageInfo"))   ;  
	    	    mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ;
	    	    mv.addObject("keyWord", keyWord)  ;
	       }catch(Exception e){
	    	    mv.setViewName("err") ;
	       }
		   return mv  ;
	}
	
	
	//URL解析:http://localhost/ZZUPrj/Teacher/findDetailsById/4
	@RequestMapping("/findDetailsById/{teacherId}")
    public ModelAndView findDetailsById(@PathVariable("teacherId") Integer teacherId){
		   ModelAndView mv=new ModelAndView("teacher_details")  ;
		   try{
			   Teacher teacher=this.teacherService.findById(teacherId)  ;   //根据ID查询
			   mv.addObject("teacher", teacher)   ;                        //添加教师
			   mv.addObject("courses", teacher.getCourses())   ;                       //增加课程信息 
		   }catch(Exception e){
			    mv.setViewName("err") ;   //出现异常就跳转到错误页
		   }
		   return mv ;
	}	
	
	//URL解析:http://localhost/ZZUPrj/Teacher/doEdit/4
	@RequestMapping("/doEdit/{teacherId}")
	public ModelAndView doEdit(@PathVariable("teacherId") Integer teacherId){
		   ModelAndView mv=new ModelAndView("teacher_update") ;
		   try{
			   Teacher teacher=this.teacherService.findById(teacherId)   ;
			   if(teacher==null){   //不存在,手工抛出异常
				   throw new Exception() ;
			   }else{    //教师存在
				    mv.addObject("teacher", teacher)   ;    //增加教师模型
			   }
		   }catch(Exception e){
			    mv.setViewName("err") ;    //出现异常就跳转到错误页
		   }
		   return mv ;
	}
	
	
	//URL解析:/ZZUPrj/Teacher/doUpdate
	@RequestMapping("/doUpdate")
	public ModelAndView doUpdate(Teacher teacher){
		   ModelAndView mv=new ModelAndView("redirect:/Teacher/list") ;
		   try{
			   Integer count=0;
			   count=this.teacherService.doUpdate(teacher)  ;   //修改教师
			   if(count==0){
				   throw new Exception() ;     //修改失败
			   }
		   }catch(Exception e){
			    mv.setViewName("err") ;    //跳转到错误页
		   }
		   return mv ;
	}
	
	//URL解析:http://localhost/ZZUPrj/Teacher/doRemove/4
	@RequestMapping("/doRemove/{teacherId}")
	public ModelAndView doRemove(@PathVariable("teacherId") Integer teacherId){
		   ModelAndView mv=new ModelAndView("redirect:/Teacher/list")  ;
		   try{
			   Integer count =0 ;
			   count=this.teacherService.doRemove(teacherId) ;  //删除教师
			   if(count==0){
				    throw new Exception() ;   //删除失败
			   }
		   }catch(Exception e){
			    mv.setViewName("err")  ;   //设置错误视图 
		   }
		   return mv ;
	}
	
	
	//URL解析:http://localhost/ZZUPrj/Teacher/doCreatePre
	@RequestMapping("doCreatePre")
	public String doCreatePre(){
		    return "teacher_insert"  ;   //跳转到增加页面 
	}
	
	//URL解析:${pageContext.request.contextPath}/Teacher/doCreate
	@RequestMapping("/doCreate")
    public String doCreate(Teacher teacher){
		   String page="redirect:/Teacher/list"  ;
		   try{
			   Integer count= 0;
			   count=this.teacherService.doCreate(teacher)   ;   //增加教师
			   if(count==0){
				   throw new Exception() ;    //手工抛出异常,因为增加失败
			   }
		   }catch(Exception e){
			    page="err"  ;
		   }
		   return page  ;
	}	
	
}
