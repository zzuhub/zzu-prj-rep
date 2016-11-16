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
	  private IClazzService clazzService   ;   //注入Clazz服务层
	  
	  @Autowired
	  private IMajorService majorService   ;   //注入Major服务层
	
	  //URL解析:http://localhost/ZZUPrj/Clazz/list
	  @RequestMapping("/list")
	  public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
			                   @RequestParam(name="lineSize",defaultValue="5")Integer lineSize,
			                   @RequestParam(name="keyWord",defaultValue="")String keyWord){
		     ModelAndView mv=new ModelAndView("clazz_list")  ;
		     try{
		    	  Map<String, Object>  map =null;
		    	  map=this.clazzService.findAll(currentPage, lineSize, keyWord)  ; //接收参数
		    	  mv.addObject("clazzs", map.get("clazzs"))  ;   //放入班级模型
		    	  mv.addObject("pageInfo", map.get("pageInfo"))  ;  //放入分页信息 
		    	  mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ;
		    	  mv.addObject("keyWord", keyWord)  ;
		     }catch(Exception e){
		    	  mv.setViewName("err")  ;    //出现异常就跳转到错误页
		     }
		     return mv ;
	  }
	  
	  //URL解析:http://localhost/ZZUPrj/Clazz/doCreatePre
	  @SuppressWarnings(value={"unchecked"})
	  @RequestMapping("/doCreatePre")
	  public ModelAndView doCreatePre(){
		     ModelAndView mv=new ModelAndView("clazz_insert") ;
		     try{
				List<Major> majors=(List<Major>)(this.majorService.findAll(1, 0, "").get("majors"));
		    	mv.addObject("majors", majors) ;   //添加专业集合,方便添加班级信息时选择
		     }catch(Exception e){
		    	   mv.setViewName("err") ;     //程序出现异常直接跳出
		     }
		     return mv;
	  }
	  
	  //URL解析:http://localhost/ZZUPrj/Clazz/findDetailsById/2
	  @RequestMapping("/findDetailsById/{clazzId}")
	  public ModelAndView findDetailsById(@PathVariable("clazzId")Integer clazzId){
		     ModelAndView mv=new ModelAndView("clazz_details") ;
		     try{
		    	 Clazz clazz = this.clazzService.findById(clazzId)  ;   //根据ID查询
		    	 if(null==clazz){
		    		  throw new Exception()  ;   //如果查询不到直接抛出异常
		    	 }else{
		    		  mv.addObject("clazz", clazz) ;   //添加班级模型 
		    	 }
		     }catch(Exception e){
		    	   mv.setViewName("err") ;    //出现异常直接跳转到错误页面
		     }
		     return mv ;
	  }
	  
	  //URL解析:/ZZUPrj/Clazz/validateClazzNameExist
	  @ResponseBody
	  @RequestMapping("/validateClazzNameExist")
	  public String validateClazzNameExist(@RequestParam(name="clazzName",defaultValue="")String clazzName){
		     String jsonStr=null ;
		     try{
		    	 clazzName=new String(clazzName.getBytes("ISO8859-1"),"UTF-8");
		    	 Clazz clazz = this.clazzService.findByName(clazzName) ; //查找班级
		    	 jsonStr=new ObjectMapper().writeValueAsString(clazz) ; //写JSON
		     }catch(Exception e){
		    	  jsonStr="err"    ;   //系统异常
		     }
		     return jsonStr ;
	  }
	  
	  //URL解析:/ZZUPrj/Clazz/doCreate
	  @RequestMapping("/doCreate")
	  public String doCreate(Clazz clazz){
		     String page="redirect:/Clazz/list"  ;
		     try{
		    	 Integer count=0 ;
		    	 count=this.clazzService.doCreate(clazz)  ;   //增加班级
		    	 if(count==0){
		    		 throw new Exception()  ;//未能成功增加就抛出异常
		    	 }
		     }catch(Exception e){
		    	  page="err"   ;      //出现异常直接跳出
		     }
		     return page ;
	  }
	  
	  //URL解析:http://localhost/ZZUPrj/Clazz/doEdit/10
	  @SuppressWarnings("unchecked")
	  @RequestMapping("/doEdit/{clazzId}")
	  public ModelAndView doEdit(@PathVariable("clazzId")Integer clazzId){
		     ModelAndView mv=new ModelAndView("clazz_update")   ;  //跳转到修改页面
		     try{
		    	   Clazz clazz=this.clazzService.findById(clazzId)  ;
		    	   List<Major> majors=(List<Major>)(this.majorService.findAll(1, 0, "").get("majors"));
		    	   if(clazz==null){
		    		   throw new Exception()  ;
		    	   }else{
		    		   mv.addObject("clazz", clazz)  ;    //设置班级 
		    		   mv.addObject("majors", majors)  ;   //设置专业
		    	   }
		     }catch(Exception e){
		    	   mv.setViewName("err")  ;    //出现异常就跳转到错误页
		     }
		     return mv ;
	  }	 
	  
	  //URL解析:/ZZUPrj/Clazz/doUpdate
	  @RequestMapping("/doUpdate")
	  public String doUpdate(Clazz clazz){
		     String page="redirect:/Clazz/list"   ;
		     try{
            	 Integer count=0;
            	 count=this.clazzService.doUpdate(clazz)  ;    //修改班级
            	 if(count==0){
            		 throw new Exception()  ;   //修改失败,直接抛出异常
            	 }
		     }catch(Exception e){
		    	   page="err"  ;     //出现异常直接跳转到错误页
		     }
		     return page  ;
	  }
	  
	  //URL解析:http://localhost/ZZUPrj/Clazz/doRemove/10
	  @RequestMapping("doRemove/{clazzId}")
	  public ModelAndView doRemove(@PathVariable("clazzId")Integer clazzId){
		     ModelAndView mv=new ModelAndView("redirect:/Clazz/list")  ;
		     try{
		    	  Integer count=0 ;
		    	  count=this.clazzService.doRemove(clazzId)  ;
		    	  if(count==0){
		    		  throw new Exception() ;   //删除失败,直接抛出异常,跳转到错误页
		    	  }
		     }catch(Exception e){
		    	   mv.setViewName("err")  ;   //出现异常就跳转到错误页
		     }
		     return mv ;
	  }
	  
	  
	  
}
