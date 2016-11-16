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
		    Integer currentPage=1 ;     //Ĭ�ϵ�ǰҳ
		    Integer lineSize= 10    ;    //Ĭ��ÿҳ��ʾ������
		    String strCurrentPage=req.getParameter("currentPage")  ;
		    String strLineSize=req.getParameter("lineSize")  ;
		    try{
		    	currentPage=Integer.parseInt(strCurrentPage);      //��ʽ��
		    	lineSize=Integer.parseInt(strLineSize);           //��ʽ��
		    }catch(Exception e){}
		    try{
		    	Map<String,Object> map=this.permissionService.findAll(currentPage, lineSize)  ;
		    	modelAndView.addObject("permissions", map.get("permissions"));      //��������
		    	modelAndView.addObject("pageInfo", map.get("pageInfo"));      //�����ҳ��Ϣ
		    	modelAndView.addObject("lineSizes", new int[]{5,10,15,20,25,30});      //�����ҳ��Ϣ
		    }catch(Exception e){
		    	modelAndView.setViewName("err");
		    }
		    return  modelAndView ;
	  }
	
}
