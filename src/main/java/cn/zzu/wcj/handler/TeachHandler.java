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
	   * ����:��ҳ���黹û������,2016/11/6�ż�����ɴ���
	   * ����:2016/11/6����д����
	   * @param currentPage ��ǰҳ
	   * @param lineSize ÿҳ��ʾ�������� 
	   * @return
	   */
	  //URL����:http://localhost/ZZUPrj/Teach/list
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
		    	 mv.addObject("teachs", teachs)    ;   //�ڿ��б�
		    	 mv.addObject("pageInfo", pageInfo)  ;    //��ҳ��Ϣ
		    	 mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ; //ÿҳ��ʾ��������
		     }catch(Exception e){
		    	  mv.setViewName("err")  ;    //���ô���ҳ
		     }
		     return mv ;
	  }
	  
	  
	//URL����:http://localhost/ZZUPrj/Teach/doCreatePre
	@RequestMapping("/doCreatePre")
	  public ModelAndView doCreatePre(){
		     ModelAndView mv=new ModelAndView("teach_insert") ;
		     try{
		    	   Map<Integer,String> termMap=new HashMap<Integer,String>() ;
		    	   termMap.put(1, "��1ѧ��")  ;
		    	   termMap.put(2, "��2ѧ��")  ;
		    	   termMap.put(3, "��3ѧ��")  ;
		    	   termMap.put(4, "��4ѧ��")  ;
		    	   termMap.put(5, "��5ѧ��")   ;
		    	   termMap.put(6, "��6ѧ��")   ;
		    	   termMap.put(7, "��7ѧ��")   ;
		    	   termMap.put(8, "��8ѧ��")   ;
		    	   mv.addObject("termMap", termMap)  ;   //��װ����ģ��
		    	   Map<String, Object> map = this.majorService.findAll(1, 0, "")   ;   //����ȫ��רҵ�ͷ�ҳ��Ϣ 
		    	   mv.addObject("majors", map.get("majors"))  ;
		     }catch(Exception e){
		    	  mv.setViewName("err")  ;   //�����쳣����ת������ҳ
		     }
		     return  mv ;
	  }
	  
	  
	 //URL����:ZZUPrj/Teach/validateTeacherExist
     @ResponseBody
	 @RequestMapping("/validateTeacherExist")
	 public String validateTeacherExist(@RequestParam("teacherId")Integer teacherId){
		   String data=null ;
		   try{
			   Teacher teacher = this.teacherService.findById(teacherId)  ;   //����ID���ҽ�ʦ��Ϣ
			   data=new ObjectMapper().writeValueAsString(teacher)  ;     //�����ݴ����JSON��ʽ 
		   }catch(Exception e){
			    data="{\"err\":\"err\"}"   ;
		   }
		   return data ;
	 }
	  
	 //URL����:/ZZUPrj/Term/getCoursesByTerm
     @RequestMapping("/getCoursesByTerm")
     @ResponseBody
     public String getCoursesByTerm(@RequestParam("term")Integer term){
    	    String jsonStr=null ;
    	    try{
    	    	List<Course> courses=this.courseService.findByTerm(term)  ; //�ҵ��γ�
    	    	jsonStr=new ObjectMapper().writeValueAsString(courses)   ;  
    	    }catch(Exception e){
    	    	jsonStr="{\"err\":\"err\"}"   ;   //������Ϣ 
    	    }
    	    return jsonStr ;
     }
	  
	 //URL��ַ����:/ZZUPrj/Teach/getClazzsByMajorId 
	 @RequestMapping("/getClazzsByMajorId ")
	 @ResponseBody
	 public String getClazzsByMajorId(@RequestParam("majorId")Integer majorId){
		    String jsonStr =null;
		    try{ 
		    	List<Clazz> clazzs=this.clazzService.findClazzsByMajorId(majorId);
		    	jsonStr=new ObjectMapper().writeValueAsString(clazzs)  ;
		    }catch(Exception e){
		    	jsonStr="{\"err\":\"err\"}"   ;   //������Ϣ 
		    }
		    return jsonStr ;
	 }
	  
	//URL����:/ZZUPrj/Teach/doCreate
    @RequestMapping("/doCreate")
	public String doCreate(Teach teach){
    	   String page="redirect:/Teach/list"  ;
    	   try{
    		    Integer count=0 ;
    		    count=this.teachService.doCreate(teach) ;
    		    if(count==0){
    		    	throw new Exception()  ;    //����ʧ��ֱ���׳��쳣
    		    }
    	   }catch(Exception e){
    		     page="err"   ;
    	   }
    	   return page ;
    } 
    
    //URL����:http://localhost/ZZUPrj/Teach/doEdit/3
    @RequestMapping("/doEdit/{teachId}")
    public ModelAndView doEdit(@PathVariable("teachId")Integer teachId){
    	   ModelAndView mv=new ModelAndView("teach_update")  ;
    	   try{
    		      Teach teach = this.teachService.findById(teachId); //����ID��ѯ
    		      if(teach==null){
    		    	   throw new Exception() ;   //��ѧ��¼������,ֱ���׳��쳣
    		      }else{
    		    	   mv.addObject("teach", teach)    ;  //���ӽ�ѧģ�� 
    		      }
    		       Map<Integer,String> termMap=new HashMap<Integer,String>() ;
		    	   termMap.put(1, "��1ѧ��")  ;
		    	   termMap.put(2, "��2ѧ��")  ;
		    	   termMap.put(3, "��3ѧ��")  ;
		    	   termMap.put(4, "��4ѧ��")  ;
		    	   termMap.put(5, "��5ѧ��")   ;
		    	   termMap.put(6, "��6ѧ��")   ;
		    	   termMap.put(7, "��7ѧ��")   ;
		    	   termMap.put(8, "��8ѧ��")   ;
		    	   mv.addObject("termMap", termMap)  ;   //��װ����ģ��
		    	   Map<String, Object> map = this.majorService.findAll(1, 0, "")   ;   //����ȫ��רҵ�ͷ�ҳ��Ϣ 
		    	   mv.addObject("majors", map.get("majors"))  ;
    	   }catch(Exception e){
    		       mv.setViewName("err")  ;    //�����쳣����ת������ҳ�� 
    	   }
    	   return mv ;
    }
    
    //URL����:/ZZUPrj/Teach/doUpdate
    @RequestMapping("/doUpdate")
    public String doUpdate(Teach teach){
    	   String page="redirect:/Teach/list"  ;
    	   try{
    		   Integer count=0 ;
    		   count=this.teachService.doUpdate(teach) ;  //�޸�
    		   if(count==0){
    			    throw new Exception()  ;   //�޸�ʧ��,ֱ���׳��쳣
    		   }
    	   }catch(Exception e){
    		   page="err"  ;        //�����쳣ֱ����ת������ҳ 
    	   }
    	   return page ;
    }
    
   //URL����:http://localhost/ZZUPrj/Teach/doRemove/3
    @RequestMapping("/doRemove/{teachId}")
    public String doRemove(@PathVariable("teachId")Integer teachId){
    	  String page="redirect:/Teach/list"  ;
    	  try{
    		   Integer count=0 ;
    		   count=this.teachService.doRemove(teachId) ;  //����IDɾ��
    		   if(count==0){
    			   throw new Exception() ;   //ɾ��ʧ��,ֱ������
    		   }
    	  }catch(Exception e){
    		    page="err"  ;       //������ִ������ת������ҳ
    	  }
    	  return page ;
    }
    
	         
}
