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
	
	//URL����:http://localhost/ZZUPrj/Teacher/list
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
	    	     * ����ķ�ҳ����һ��Ҫ����ȥ,��Ȼû��ʹ�÷�ҳ���
	    	     */
	    	    mv.addObject("pageInfo", map.get("pageInfo"))   ;  
	    	    mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ;
	    	    mv.addObject("keyWord", keyWord)  ;
	       }catch(Exception e){
	    	    mv.setViewName("err") ;
	       }
		   return mv  ;
	}
	
	
	//URL����:http://localhost/ZZUPrj/Teacher/findDetailsById/4
	@RequestMapping("/findDetailsById/{teacherId}")
    public ModelAndView findDetailsById(@PathVariable("teacherId") Integer teacherId){
		   ModelAndView mv=new ModelAndView("teacher_details")  ;
		   try{
			   Teacher teacher=this.teacherService.findById(teacherId)  ;   //����ID��ѯ
			   mv.addObject("teacher", teacher)   ;                        //��ӽ�ʦ
			   mv.addObject("courses", teacher.getCourses())   ;                       //���ӿγ���Ϣ 
		   }catch(Exception e){
			    mv.setViewName("err") ;   //�����쳣����ת������ҳ
		   }
		   return mv ;
	}	
	
	//URL����:http://localhost/ZZUPrj/Teacher/doEdit/4
	@RequestMapping("/doEdit/{teacherId}")
	public ModelAndView doEdit(@PathVariable("teacherId") Integer teacherId){
		   ModelAndView mv=new ModelAndView("teacher_update") ;
		   try{
			   Teacher teacher=this.teacherService.findById(teacherId)   ;
			   if(teacher==null){   //������,�ֹ��׳��쳣
				   throw new Exception() ;
			   }else{    //��ʦ����
				    mv.addObject("teacher", teacher)   ;    //���ӽ�ʦģ��
			   }
		   }catch(Exception e){
			    mv.setViewName("err") ;    //�����쳣����ת������ҳ
		   }
		   return mv ;
	}
	
	
	//URL����:/ZZUPrj/Teacher/doUpdate
	@RequestMapping("/doUpdate")
	public ModelAndView doUpdate(Teacher teacher){
		   ModelAndView mv=new ModelAndView("redirect:/Teacher/list") ;
		   try{
			   Integer count=0;
			   count=this.teacherService.doUpdate(teacher)  ;   //�޸Ľ�ʦ
			   if(count==0){
				   throw new Exception() ;     //�޸�ʧ��
			   }
		   }catch(Exception e){
			    mv.setViewName("err") ;    //��ת������ҳ
		   }
		   return mv ;
	}
	
	//URL����:http://localhost/ZZUPrj/Teacher/doRemove/4
	@RequestMapping("/doRemove/{teacherId}")
	public ModelAndView doRemove(@PathVariable("teacherId") Integer teacherId){
		   ModelAndView mv=new ModelAndView("redirect:/Teacher/list")  ;
		   try{
			   Integer count =0 ;
			   count=this.teacherService.doRemove(teacherId) ;  //ɾ����ʦ
			   if(count==0){
				    throw new Exception() ;   //ɾ��ʧ��
			   }
		   }catch(Exception e){
			    mv.setViewName("err")  ;   //���ô�����ͼ 
		   }
		   return mv ;
	}
	
	
	//URL����:http://localhost/ZZUPrj/Teacher/doCreatePre
	@RequestMapping("doCreatePre")
	public String doCreatePre(){
		    return "teacher_insert"  ;   //��ת������ҳ�� 
	}
	
	//URL����:${pageContext.request.contextPath}/Teacher/doCreate
	@RequestMapping("/doCreate")
    public String doCreate(Teacher teacher){
		   String page="redirect:/Teacher/list"  ;
		   try{
			   Integer count= 0;
			   count=this.teacherService.doCreate(teacher)   ;   //���ӽ�ʦ
			   if(count==0){
				   throw new Exception() ;    //�ֹ��׳��쳣,��Ϊ����ʧ��
			   }
		   }catch(Exception e){
			    page="err"  ;
		   }
		   return page  ;
	}	
	
}
