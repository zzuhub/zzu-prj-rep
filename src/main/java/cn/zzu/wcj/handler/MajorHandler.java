package cn.zzu.wcj.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zzu.wcj.entity.Major;
import cn.zzu.wcj.service.IMajorService;


@RequestMapping("/Major")
@Controller
public class MajorHandler {

	 @Autowired
	 private IMajorService majorService   ;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
			                 @RequestParam(name="lineSize",defaultValue="10")Integer lineSize ,
			                 @RequestParam(name="keyWord",defaultValue="")String keyWord){
		   ModelAndView mv=new ModelAndView("major_list");
		   try{
			    Map<String, Object> map = this.majorService.findAll(currentPage, lineSize, keyWord)  ;
			    mv.addObject("majors",map.get("majors") )   ;
			    mv.addObject("pageInfo", map.get("pageInfo"))  ;
			    mv.addObject("keyWord", keyWord)  ;
			    mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ;
			    
		   }catch(Exception e){
			     mv.setViewName("err")  ;  //�����쳣ֱ����ת������ҳ 
		   }
		   return mv ;
		
	}
	
	//URL����:http://localhost/ZZUPrj/Major/findDetailsById/2
	@RequestMapping("/findDetailsById/{majorId}")
	public ModelAndView findDetailsById(@PathVariable("majorId")Integer majorId){
		   ModelAndView mv=new ModelAndView("major_details")  ;   //��ת������ҳ
		   try{
			     Major major = this.majorService.findById(majorId)  ;   //��ѯרҵ����
			     if(major==null){
			    	 throw new Exception()   ;    //ֱ���׳��쳣,��Ϊרҵ��Ϣ�Ѿ���ɾ����
			     }else{
			    	 mv.addObject("major", major) ;   //����רҵ��Ϣ
			     }
		   }catch(Exception e){
			     mv.setViewName("err") ;    //���ô�����Ϣ 
		   }
		   return mv ;
	}
	
	//URL����:http://localhost/ZZUPrj/Major/doCreatePre
	@RequestMapping("/doCreatePre")
	public String doCreatePre(){
		   return "major_insert"   ;   //��ת������רҵ��ҳ�� 
	}
	
	
	//URL����:/ZZUPrj/Major/doCreate
	@RequestMapping("/doCreate")
	public String doCreate(Major major){
		   String page="redirect:/Major/list"   ;
		   try{
			   Integer count=0 ;
			   count=this.majorService.doCreate(major) ;   //����רҵ
			   if(count==0){
				   throw new Exception()  ;   //����ʧ��,ֱ����ת������ҳ
			   }
		   }catch(Exception e){
			      page="err"    ;      //�����쳣����ת������ҳ
		   }
		   return page ;
	}
	
	
	//URL����:http://localhost/ZZUPrj/Major/doEdit/4
	@RequestMapping("/doEdit/{majorId}")
	public ModelAndView doEdit(@PathVariable("majorId") Integer majorId){
		   ModelAndView mv=new ModelAndView("major_update") ;   //��ת���޸�ҳ��
		   try{
			    Major major = this.majorService.findById(majorId)  ;  //����ID����רҵ
			    if(major==null){
			    	 throw new Exception()  ;    //רҵ�Ѿ���ɾ��,�׳��쳣,��ת������ҳ
			    }else{
			    	 mv.addObject("major", major)  ;   //����רҵģ��
			    }
		   }catch(Exception e){
			   mv.setViewName("err")  ;   //�����쳣����ת������ҳ��
		   }
		   return mv   ;
	}
	
	
	//URL����:/ZZUPrj/Major/doUpdate
	@RequestMapping("/doUpdate")
	public String doUpdate(Major major){
		   String page="redirect:/Major/list"  ;  //��ת��רҵ�б�ҳ
		   try{
			   Integer count=0 ;
			   count=this.majorService.doUpdate(major)  ;   //����רҵ
			   if(count==0){
				   throw new Exception()  ;   //������������,��ת������ҳ   
			   }
		   }catch(Exception e){
			     page="err"  ;     //��������쳣����ת������ҳ
		   }
		   return page ;
	}
	
	//url����:http://localhost/ZZUPrj/Major/doRemove/5
	@RequestMapping("/doRemove/{majorId}")
	public String doRemove(@PathVariable("majorId")Integer majorId){
		   String page="redirect:/Major/list"  ;
		   Integer count= 0 ;
		   try{
			   count=this.majorService.doRemove(majorId) ;  //ɾ��רҵ,����ҵ��ǳ�����
			   System.out.println("�۲�ɾ������:"+count);
			   if(count==0){
				   throw new Exception()  ;  //ɾ��ʧ��,��������ҳ
			   }
		   }catch(Exception e){
			   page="err"  ;     //�����쳣����ת������ҳ
		   }
		   return page ;
	}
	
	
	//URL����:/ZZUPrj/Major/validateMajorNameExist
	@RequestMapping("/validateMajorNameExist")
	@ResponseBody
	public String validateMajorNameExist(@RequestParam("majorName")String majorName){
		   String jsonStr=null ;
		   try{
			   majorName=new String(majorName.getBytes("ISO8859-1"),"UTF-8"); 
			   Major major = this.majorService.findMajorByName(majorName)  ;  //�������Ʋ���
			   jsonStr=new ObjectMapper().writeValueAsString(major) ;//дJSON
		   }catch(Exception e){
			    jsonStr="err"  ;
		   }
		   return jsonStr ;
	}
	
	
	
}
