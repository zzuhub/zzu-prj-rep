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
			     mv.setViewName("err")  ;  //出现异常直接跳转到错误页 
		   }
		   return mv ;
		
	}
	
	//URL解析:http://localhost/ZZUPrj/Major/findDetailsById/2
	@RequestMapping("/findDetailsById/{majorId}")
	public ModelAndView findDetailsById(@PathVariable("majorId")Integer majorId){
		   ModelAndView mv=new ModelAndView("major_details")  ;   //跳转到详情页
		   try{
			     Major major = this.majorService.findById(majorId)  ;   //查询专业详情
			     if(major==null){
			    	 throw new Exception()   ;    //直接抛出异常,因为专业信息已经被删除了
			     }else{
			    	 mv.addObject("major", major) ;   //增加专业信息
			     }
		   }catch(Exception e){
			     mv.setViewName("err") ;    //设置错误信息 
		   }
		   return mv ;
	}
	
	//URL解析:http://localhost/ZZUPrj/Major/doCreatePre
	@RequestMapping("/doCreatePre")
	public String doCreatePre(){
		   return "major_insert"   ;   //提转到增加专业的页面 
	}
	
	
	//URL解析:/ZZUPrj/Major/doCreate
	@RequestMapping("/doCreate")
	public String doCreate(Major major){
		   String page="redirect:/Major/list"   ;
		   try{
			   Integer count=0 ;
			   count=this.majorService.doCreate(major) ;   //增加专业
			   if(count==0){
				   throw new Exception()  ;   //增加失败,直接跳转到错误页
			   }
		   }catch(Exception e){
			      page="err"    ;      //出现异常就跳转到错误页
		   }
		   return page ;
	}
	
	
	//URL解析:http://localhost/ZZUPrj/Major/doEdit/4
	@RequestMapping("/doEdit/{majorId}")
	public ModelAndView doEdit(@PathVariable("majorId") Integer majorId){
		   ModelAndView mv=new ModelAndView("major_update") ;   //跳转到修改页面
		   try{
			    Major major = this.majorService.findById(majorId)  ;  //根据ID查找专业
			    if(major==null){
			    	 throw new Exception()  ;    //专业已经被删除,抛出异常,跳转到错误页
			    }else{
			    	 mv.addObject("major", major)  ;   //增加专业模型
			    }
		   }catch(Exception e){
			   mv.setViewName("err")  ;   //出现异常就跳转到错误页面
		   }
		   return mv   ;
	}
	
	
	//URL解析:/ZZUPrj/Major/doUpdate
	@RequestMapping("/doUpdate")
	public String doUpdate(Major major){
		   String page="redirect:/Major/list"  ;  //跳转到专业列表页
		   try{
			   Integer count=0 ;
			   count=this.majorService.doUpdate(major)  ;   //更新专业
			   if(count==0){
				   throw new Exception()  ;   //更新遇到错误,跳转到错误页   
			   }
		   }catch(Exception e){
			     page="err"  ;     //程序出现异常就跳转到错误页
		   }
		   return page ;
	}
	
	//url解析:http://localhost/ZZUPrj/Major/doRemove/5
	@RequestMapping("/doRemove/{majorId}")
	public String doRemove(@PathVariable("majorId")Integer majorId){
		   String page="redirect:/Major/list"  ;
		   Integer count= 0 ;
		   try{
			   count=this.majorService.doRemove(majorId) ;  //删除专业,这项业务非常复杂
			   System.out.println("观察删除个数:"+count);
			   if(count==0){
				   throw new Exception()  ;  //删除失败,跳到错误页
			   }
		   }catch(Exception e){
			   page="err"  ;     //出现异常就跳转到错误页
		   }
		   return page ;
	}
	
	
	//URL解析:/ZZUPrj/Major/validateMajorNameExist
	@RequestMapping("/validateMajorNameExist")
	@ResponseBody
	public String validateMajorNameExist(@RequestParam("majorName")String majorName){
		   String jsonStr=null ;
		   try{
			   majorName=new String(majorName.getBytes("ISO8859-1"),"UTF-8"); 
			   Major major = this.majorService.findMajorByName(majorName)  ;  //根据名称查找
			   jsonStr=new ObjectMapper().writeValueAsString(major) ;//写JSON
		   }catch(Exception e){
			    jsonStr="err"  ;
		   }
		   return jsonStr ;
	}
	
	
	
}
