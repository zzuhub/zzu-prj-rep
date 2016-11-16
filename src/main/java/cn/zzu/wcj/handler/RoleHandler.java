package cn.zzu.wcj.handler;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zzu.wcj.entity.Permission;
import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.service.IPermissionService;
import cn.zzu.wcj.service.IRoleService;

import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/Role")
@Controller
public class RoleHandler {

	  @Autowired
	  private IRoleService roleService  ; 
	  
	  @Autowired
	  private IPermissionService permissionService  ;
	
	  @RequestMapping("/list")
	  public ModelAndView findAll(){
		     ModelAndView mv=new ModelAndView("role_list") ;
		     try{
		    	 List<Role> roles = roleService.findAll()  ;  //取得全部角色
		    	 mv.addObject("roles", roles)  ;   //增加模型
		     }catch(Exception e){
		    	 mv.setViewName("err");   //跳转到错误页
		     }
		     return mv ;
	  }
	  
	  @RequestMapping("/findDetailsById/{roleId}")
	  public ModelAndView findDetailsById(@PathVariable("roleId") Integer roleId){
		     ModelAndView mv=new ModelAndView("role_details")  ;
		     try{
		    	 Role role=this.roleService.findById(roleId)   ;   //查询出更为详细的内容
		    	 mv.addObject("role", role)  ;     //添加模型
		     }catch(Exception e){
                  mv.setViewName("err");
		     }
		     return mv   ;
	  }
	  
	  @RequestMapping("/doCreatePre")
	  public ModelAndView doCreatePre(){
		     ModelAndView mv=new ModelAndView("role_insert") ;
		     try{
		    	 Map<String, Object> map = this.permissionService.findAll(-1, 0) ; //查询全部
		    	 mv.addObject("permissions",map.get("permissions")) ;
		     }catch(Exception e){
		    	  mv.setViewName("err") ;
		     }
		     return mv ;
	  }
	  
	  @ResponseBody
	  @RequestMapping("/ValidateRoleNameExist")
	  public String ValidateRoleNameExist(@RequestParam("roleName") String roleName){
		     String jsonRole="" ;
		     try{
		    	 Role role=this.roleService.findByRoleName(roleName)  ;   //根据角色名称查询角色
		    	 jsonRole=new ObjectMapper().writeValueAsString(role) ;  //将对象打包成JSON格式
		     }catch(Exception e){
		    	 e.printStackTrace();
		     }
		     return jsonRole  ;
	  }
	  
	  
	  @RequestMapping("/doCreate")
	  public String doCreate(Role role){
		     String page="redirect:/Role/list" ;
		     try{
		    	 List<Permission> oldPermissions = role.getPermissions();  //原有权限
		    	 List<Permission> newPermissions=this.clearNullPermission(oldPermissions) ;  //清理空值
		    	 role.setPermissions(newPermissions);   //设置权限列表为新的权限列表
		    	 Integer count=this.roleService.doCreate(role)  ;   //增加角色
		    	  if(count==0){    //增加失败
		    		  throw new Exception();
		    	  }
		     }catch(Exception e){
		    	 page="err"   ;     //出现异常就跳转到错误页
		     }
		     return page;
	  }
	  
	  //解析:URLhttp://localhost/ZZUPrj/Role/doEdit/3
	  @RequestMapping("/doEdit/{roleId}")
	  public ModelAndView doEdit(@PathVariable("roleId") Integer roleId){
		     ModelAndView mv=new ModelAndView("role_update")  ;
		     try{
		    	 Role role=this.roleService.findById(roleId) ;   //查找角色
		    	 Map<String, Object> map = this.permissionService.findAll(-1, 0) ; //查询全部权限
		    	 mv.addObject("role", role)  ;     //保存角色
		    	 mv.addObject("myPerms",role.getPermissions()) ;    //保存角色的权限
		    	 mv.addObject("allPerms",map.get("permissions")) ;  //保存全部的权限 
		     }catch(Exception e){
		    	 mv.setViewName("err") ;   //设置视图为错误页面
		     }
		     return mv  ;
	  }
	  
	  @RequestMapping("/doUpdate")
	  public String doUpdate(Role role){
		     String page="redirect:/Role/list" ;
		     try{
		    	 List<Permission> oldPermissions = role.getPermissions();  //原有权限
		    	 List<Permission> newPermissions=this.clearNullPermission(oldPermissions) ;  //清理空值
		    	 role.setPermissions(newPermissions);   //设置权限列表为新的权限列表
		    	 Integer count= 0 ;
		    	 count=this.roleService.doUpdate(role) ;   //更新角色
		    	 if(count==0){
		    		 throw new Exception() ;    //更新失败,手工抛出异常
		    	 }
		     }catch(Exception e){
		    	 page="err"  ;     //出现异常就跳转到错误页
		     }
		     return page  ;
	  }
	  
	  @RequestMapping("doRemove/{roleId}")
	  public String doRemove(@PathVariable("roleId") Integer roleId){
		     String page="redirect:/Role/list"  ;
		     try{
		    	 Integer count=0  ;   //统计删除个数
		    	 count=this.roleService.doRemove(roleId) ;  //删除角色
		    	 if(count==0){
		    		 throw new Exception() ;  //抛出异常,因为删除失败了,要跳转到错误页
		    	 }
		     }catch(Exception e){
		    	 page="err"   ;
		     }
		     return page ;
	  }
	  
	  /**
	   * 这个方法主要是内部清洗更新和增加角色时的空值的权限
	   * @param oldPermissions 原有权限列表,这里要将其中的空值过滤掉
	   * @return 非空值的权限集合
	   */
	  private List<Permission> clearNullPermission(List<Permission> oldPermissions){
		     List<Permission> newPermissions=new ArrayList<Permission>()  ;     //保存新权限
		     for(int x=0;x<oldPermissions.size();x++){
	    		 Permission permission=oldPermissions.get(x) ;   //取得原有权限
	    		 if(permission.getPermissionId()!=null){   //权限存在
	    			 newPermissions.add(permission) ;
	    		 }
	    	 }
		     return newPermissions  ;
	  }
	  
	  
}
