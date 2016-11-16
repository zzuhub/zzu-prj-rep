package cn.zzu.wcj.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zzu.wcj.entity.Clazz;
import cn.zzu.wcj.entity.Major;
import cn.zzu.wcj.service.IClazzService;
import cn.zzu.wcj.service.IMajorService;

@RequestMapping("/Clazz")
@Controller
public class ClazzHandler {

	  @Autowired
	  private IClazzService clazzService   ;   //ע��Clazz�����
	  
	  @Autowired
	  private IMajorService majorService   ;   //ע��Major�����
	
	  //URL����:http://localhost/ZZUPrj/Clazz/list
	  @RequestMapping("/list")
	  public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
			                   @RequestParam(name="lineSize",defaultValue="5")Integer lineSize,
			                   @RequestParam(name="keyWord",defaultValue="")String keyWord){
		     ModelAndView mv=new ModelAndView("clazz_list")  ;
		     try{
		    	  Map<String, Object>  map =null;
		    	  map=this.clazzService.findAll(currentPage, lineSize, keyWord)  ; //���ղ���
		    	  mv.addObject("clazzs", map.get("clazzs"))  ;   //����༶ģ��
		    	  mv.addObject("pageInfo", map.get("pageInfo"))  ;  //�����ҳ��Ϣ 
		    	  mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ;
		    	  mv.addObject("keyWord", keyWord)  ;
		     }catch(Exception e){
		    	  mv.setViewName("err")  ;    //�����쳣����ת������ҳ
		     }
		     return mv ;
	  }
	  
	  //URL����:http://localhost/ZZUPrj/Clazz/doCreatePre
	  @SuppressWarnings(value={"unchecked"})
	  @RequestMapping("/doCreatePre")
	  public ModelAndView doCreatePre(){
		     ModelAndView mv=new ModelAndView("clazz_insert") ;
		     try{
				List<Major> majors=(List<Major>)(this.majorService.findAll(1, 0, "").get("majors"));
		    	mv.addObject("majors", majors) ;   //���רҵ����,������Ӱ༶��Ϣʱѡ��
		     }catch(Exception e){
		    	   mv.setViewName("err") ;     //��������쳣ֱ������
		     }
		     return mv;
	  }
	  
	  //URL����:http://localhost/ZZUPrj/Clazz/findDetailsById/2
	  @RequestMapping("/findDetailsById/{clazzId}")
	  public ModelAndView findDetailsById(@PathVariable("clazzId")Integer clazzId){
		     ModelAndView mv=new ModelAndView("clazz_details") ;
		     try{
		    	 Clazz clazz = this.clazzService.findById(clazzId)  ;   //����ID��ѯ
		    	 if(null==clazz){
		    		  throw new Exception()  ;   //�����ѯ����ֱ���׳��쳣
		    	 }else{
		    		  mv.addObject("clazz", clazz) ;   //��Ӱ༶ģ�� 
		    	 }
		     }catch(Exception e){
		    	   mv.setViewName("err") ;    //�����쳣ֱ����ת������ҳ��
		     }
		     return mv ;
	  }
	  
	  //URL����:/ZZUPrj/Clazz/validateClazzNameExist
	  @ResponseBody
	  @RequestMapping("/validateClazzNameExist")
	  public String validateClazzNameExist(@RequestParam(name="clazzName",defaultValue="")String clazzName){
		     String jsonStr=null ;
		     try{
		    	 clazzName=new String(clazzName.getBytes("ISO8859-1"),"UTF-8");
		    	 Clazz clazz = this.clazzService.findByName(clazzName) ; //���Ұ༶
		    	 jsonStr=new ObjectMapper().writeValueAsString(clazz) ; //дJSON
		     }catch(Exception e){
		    	  jsonStr="err"    ;   //ϵͳ�쳣
		     }
		     return jsonStr ;
	  }
	  
	  //URL����:/ZZUPrj/Clazz/doCreate
	  @RequestMapping("/doCreate")
	  public String doCreate(Clazz clazz){
		     String page="redirect:/Clazz/list"  ;
		     try{
		    	 Integer count=0 ;
		    	 count=this.clazzService.doCreate(clazz)  ;   //���Ӱ༶
		    	 if(count==0){
		    		 throw new Exception()  ;//δ�ܳɹ����Ӿ��׳��쳣
		    	 }
		     }catch(Exception e){
		    	  page="err"   ;      //�����쳣ֱ������
		     }
		     return page ;
	  }
	  
	  //URL����:http://localhost/ZZUPrj/Clazz/doEdit/10
	  @SuppressWarnings("unchecked")
	  @RequestMapping("/doEdit/{clazzId}")
	  public ModelAndView doEdit(@PathVariable("clazzId")Integer clazzId){
		     ModelAndView mv=new ModelAndView("clazz_update")   ;  //��ת���޸�ҳ��
		     try{
		    	   Clazz clazz=this.clazzService.findById(clazzId)  ;
		    	   List<Major> majors=(List<Major>)(this.majorService.findAll(1, 0, "").get("majors"));
		    	   if(clazz==null){
		    		   throw new Exception()  ;
		    	   }else{
		    		   mv.addObject("clazz", clazz)  ;    //���ð༶ 
		    		   mv.addObject("majors", majors)  ;   //����רҵ
		    	   }
		     }catch(Exception e){
		    	   mv.setViewName("err")  ;    //�����쳣����ת������ҳ
		     }
		     return mv ;
	  }	 
	  
	  //URL����:/ZZUPrj/Clazz/doUpdate
	  @RequestMapping("/doUpdate")
	  public String doUpdate(Clazz clazz){
		     String page="redirect:/Clazz/list"   ;
		     try{
            	 Integer count=0;
            	 count=this.clazzService.doUpdate(clazz)  ;    //�޸İ༶
            	 if(count==0){
            		 throw new Exception()  ;   //�޸�ʧ��,ֱ���׳��쳣
            	 }
		     }catch(Exception e){
		    	   page="err"  ;     //�����쳣ֱ����ת������ҳ
		     }
		     return page  ;
	  }
	  
	  //URL����:http://localhost/ZZUPrj/Clazz/doRemove/10
	  @RequestMapping("doRemove/{clazzId}")
	  public ModelAndView doRemove(@PathVariable("clazzId")Integer clazzId){
		     ModelAndView mv=new ModelAndView("redirect:/Clazz/list")  ;
		     try{
		    	  Integer count=0 ;
		    	  count=this.clazzService.doRemove(clazzId)  ;
		    	  if(count==0){
		    		  throw new Exception() ;   //ɾ��ʧ��,ֱ���׳��쳣,��ת������ҳ
		    	  }
		     }catch(Exception e){
		    	   mv.setViewName("err")  ;   //�����쳣����ת������ҳ
		     }
		     return mv ;
	  }
	  
	  
	  
}
