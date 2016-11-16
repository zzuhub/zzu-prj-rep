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
		    	 List<Role> roles = roleService.findAll()  ;  //ȡ��ȫ����ɫ
		    	 mv.addObject("roles", roles)  ;   //����ģ��
		     }catch(Exception e){
		    	 mv.setViewName("err");   //��ת������ҳ
		     }
		     return mv ;
	  }
	  
	  @RequestMapping("/findDetailsById/{roleId}")
	  public ModelAndView findDetailsById(@PathVariable("roleId") Integer roleId){
		     ModelAndView mv=new ModelAndView("role_details")  ;
		     try{
		    	 Role role=this.roleService.findById(roleId)   ;   //��ѯ����Ϊ��ϸ������
		    	 mv.addObject("role", role)  ;     //���ģ��
		     }catch(Exception e){
                  mv.setViewName("err");
		     }
		     return mv   ;
	  }
	  
	  @RequestMapping("/doCreatePre")
	  public ModelAndView doCreatePre(){
		     ModelAndView mv=new ModelAndView("role_insert") ;
		     try{
		    	 Map<String, Object> map = this.permissionService.findAll(-1, 0) ; //��ѯȫ��
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
		    	 Role role=this.roleService.findByRoleName(roleName)  ;   //���ݽ�ɫ���Ʋ�ѯ��ɫ
		    	 jsonRole=new ObjectMapper().writeValueAsString(role) ;  //����������JSON��ʽ
		     }catch(Exception e){
		    	 e.printStackTrace();
		     }
		     return jsonRole  ;
	  }
	  
	  
	  @RequestMapping("/doCreate")
	  public String doCreate(Role role){
		     String page="redirect:/Role/list" ;
		     try{
		    	 List<Permission> oldPermissions = role.getPermissions();  //ԭ��Ȩ��
		    	 List<Permission> newPermissions=this.clearNullPermission(oldPermissions) ;  //�����ֵ
		    	 role.setPermissions(newPermissions);   //����Ȩ���б�Ϊ�µ�Ȩ���б�
		    	 Integer count=this.roleService.doCreate(role)  ;   //���ӽ�ɫ
		    	  if(count==0){    //����ʧ��
		    		  throw new Exception();
		    	  }
		     }catch(Exception e){
		    	 page="err"   ;     //�����쳣����ת������ҳ
		     }
		     return page;
	  }
	  
	  //����:URLhttp://localhost/ZZUPrj/Role/doEdit/3
	  @RequestMapping("/doEdit/{roleId}")
	  public ModelAndView doEdit(@PathVariable("roleId") Integer roleId){
		     ModelAndView mv=new ModelAndView("role_update")  ;
		     try{
		    	 Role role=this.roleService.findById(roleId) ;   //���ҽ�ɫ
		    	 Map<String, Object> map = this.permissionService.findAll(-1, 0) ; //��ѯȫ��Ȩ��
		    	 mv.addObject("role", role)  ;     //�����ɫ
		    	 mv.addObject("myPerms",role.getPermissions()) ;    //�����ɫ��Ȩ��
		    	 mv.addObject("allPerms",map.get("permissions")) ;  //����ȫ����Ȩ�� 
		     }catch(Exception e){
		    	 mv.setViewName("err") ;   //������ͼΪ����ҳ��
		     }
		     return mv  ;
	  }
	  
	  @RequestMapping("/doUpdate")
	  public String doUpdate(Role role){
		     String page="redirect:/Role/list" ;
		     try{
		    	 List<Permission> oldPermissions = role.getPermissions();  //ԭ��Ȩ��
		    	 List<Permission> newPermissions=this.clearNullPermission(oldPermissions) ;  //�����ֵ
		    	 role.setPermissions(newPermissions);   //����Ȩ���б�Ϊ�µ�Ȩ���б�
		    	 Integer count= 0 ;
		    	 count=this.roleService.doUpdate(role) ;   //���½�ɫ
		    	 if(count==0){
		    		 throw new Exception() ;    //����ʧ��,�ֹ��׳��쳣
		    	 }
		     }catch(Exception e){
		    	 page="err"  ;     //�����쳣����ת������ҳ
		     }
		     return page  ;
	  }
	  
	  @RequestMapping("doRemove/{roleId}")
	  public String doRemove(@PathVariable("roleId") Integer roleId){
		     String page="redirect:/Role/list"  ;
		     try{
		    	 Integer count=0  ;   //ͳ��ɾ������
		    	 count=this.roleService.doRemove(roleId) ;  //ɾ����ɫ
		    	 if(count==0){
		    		 throw new Exception() ;  //�׳��쳣,��Ϊɾ��ʧ����,Ҫ��ת������ҳ
		    	 }
		     }catch(Exception e){
		    	 page="err"   ;
		     }
		     return page ;
	  }
	  
	  /**
	   * ���������Ҫ���ڲ���ϴ���º����ӽ�ɫʱ�Ŀ�ֵ��Ȩ��
	   * @param oldPermissions ԭ��Ȩ���б�,����Ҫ�����еĿ�ֵ���˵�
	   * @return �ǿ�ֵ��Ȩ�޼���
	   */
	  private List<Permission> clearNullPermission(List<Permission> oldPermissions){
		     List<Permission> newPermissions=new ArrayList<Permission>()  ;     //������Ȩ��
		     for(int x=0;x<oldPermissions.size();x++){
	    		 Permission permission=oldPermissions.get(x) ;   //ȡ��ԭ��Ȩ��
	    		 if(permission.getPermissionId()!=null){   //Ȩ�޴���
	    			 newPermissions.add(permission) ;
	    		 }
	    	 }
		     return newPermissions  ;
	  }
	  
	  
}
