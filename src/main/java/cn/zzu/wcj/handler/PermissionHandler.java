package cn.zzu.wcj.handler;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.zzu.wcj.service.IPermissionService;

@Controller
public class PermissionHandler {

	  @Autowired
	  private IPermissionService permissionService  ;
	  
	  
	
	  @RequestMapping(value="/Permission")
	  public ModelAndView findAll(HttpServletRequest req){
		    ModelAndView modelAndView=new ModelAndView("permission_list");
		    Integer currentPage=1 ;     //默认当前页
		    Integer lineSize= 10    ;    //默认每页显示数据量
		    String strCurrentPage=req.getParameter("currentPage")  ;
		    String strLineSize=req.getParameter("lineSize")  ;
		    try{
		    	currentPage=Integer.parseInt(strCurrentPage);      //格式化
		    	lineSize=Integer.parseInt(strLineSize);           //格式化
		    }catch(Exception e){}
		    try{
		    	Map<String,Object> map=this.permissionService.findAll(currentPage, lineSize)  ;
		    	modelAndView.addObject("permissions", map.get("permissions"));      //保存数据
		    	modelAndView.addObject("pageInfo", map.get("pageInfo"));      //保存分页信息
		    	modelAndView.addObject("lineSizes", new int[]{5,10,15,20,25,30});      //保存分页信息
		    }catch(Exception e){
		    	modelAndView.setViewName("err");
		    }
		    return  modelAndView ;
	  }
	
}
