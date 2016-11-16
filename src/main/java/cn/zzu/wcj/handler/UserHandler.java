package cn.zzu.wcj.handler;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.entity.User;
import cn.zzu.wcj.service.IRoleService;
import cn.zzu.wcj.service.IUserService;
import cn.zzu.wcj.util.EncryptUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


@RequestMapping("/User")
@Controller
public class UserHandler {
    
	@Autowired
	private EncryptUtils encryptUtils  ;    //���ܹ���
	
	@Autowired
	private IRoleService roleService   ;    //��ɫ�����
	
	
	@Autowired
	private IUserService userService   ;    //�û������
	

	@RequestMapping("/login")
	public String login(User user,HttpServletRequest httpReq){
		 String page="index"  ;     //û�г��ֵ�½�쳣,��ת����̨��ҳ
		 String userName=user.getUserName()  ;   //��ȡ�û���
		 String password=user.getPassword()  ;   //��ȡ����
		 Subject currentUser=SecurityUtils.getSubject()  ;   //CurrentUser
		 UsernamePasswordToken token=null ;
		 token=new UsernamePasswordToken(userName, password)  ;   //��������
		 HttpSession session = httpReq.getSession();   //��ȡSession
		 try{
			currentUser.login(token);    //��½��֤
		    password=userName+"@"+password ;    //���δ���
		    password=this.encryptUtils.encBase64(password)  ;   //��������м���
		    user.setPassword(password);    //������������,��ΪҪ�������ݿ����޸����һ�ε�¼ʱ��,�϶���Ҫ������ת�������Ĳ����޸ĳɹ�
			this.userService.doUpdateLastLogin(user)  ;   //�����������õ�½ʱ��
			user=this.userService.findByUserName(userName)  ;   //���»�ȡUser(��Ϊ����ID����ִ��CRUD)
			session.setAttribute("user", user); //��Session�б�������
		 }catch(UnknownAccountException e1){
			 session.setAttribute("errMsg", "�û�������,�����µ�½!");   //���ô�����Ϣ
			 page="redirect:/login.jsp"  ;
		 }catch(IncorrectCredentialsException e2){
			 session.setAttribute("errMsg", "�������,�����µ�½!");   //���ô�����Ϣ
			 page="redirect:/login.jsp"  ;
		 }catch(Exception e3){
			 session.setAttribute("errMsg", "ϵͳ�����쳣,�����µ�½!");   //���ô�����Ϣ
			 page="redirect:/login.jsp"  ;
		 }
		 return page ;
	}
	
	
   @RequestMapping("/logout")
   public String logout(){
	      String page="redirect:/login.jsp" ;  
	      try{
	    	      Subject subject=SecurityUtils.getSubject()  ;
	    	      subject.logout();                                //�˳�ʱ���Զ�����Session
	      }catch(Exception e){
		         page="err"  ;
	      }
	      return page ;
   }
   
   @RequestMapping("/list")
   public ModelAndView findAll(){
	      ModelAndView mv=new ModelAndView("user_list")  ;
	      try{
	    	   List<User> users=this.userService.findAll()  ;      //��ѯ��ȫ���û���Ϣ
	    	   mv.addObject("users", users) ;   //����û�ģ��
	      }catch(Exception e){
	    	   mv.setViewName("err") ;   //�����쳣����ת������ҳ
	      }
	      return mv ;
   }
   
   //URL����:http://localhost/ZZUPrj/Role/findDetailsById/17
   @RequestMapping("/findDetailsById/{userId}")
   public ModelAndView findDetailsById(@PathVariable("userId") Integer userId){
	      ModelAndView mv=new ModelAndView("user_details")  ;   //Ĭ����ͼΪuser_details.jsp
	      try{
	    	   User user=this.userService.findById(userId)   ;  //����ID��ѯ�û�
	    	   String newPass=this.encryptUtils.encBase64(user.getUserName()+"@"+user.getPassword()) ;
	    	   user.setPassword(newPass);
	    	   mv.addObject("user", user)  ;   //���Userģ��
	      }catch(Exception e){
	    	   mv.setViewName("err") ;                    //�����쳣����ת������ҳ��
	      }
	      return mv ;
   }
   
   //URL����:http://localhost/ZZUPrj/User/doCreatePre
   @RequestMapping("/doCreatePre")
   public ModelAndView doCreatePre(){
	      ModelAndView mv=new ModelAndView("user_insert") ;   //Ĭ����ת�������û���ҳ��
	      try{
	    	  List<Role> role=this.roleService.findAll()  ;     //�ҵ�ȫ����ɫ
	    	  mv.addObject("roles", role) ;   //���ӽ�ɫģ��
	      }catch(Exception e){
	    	  mv.setViewName("err") ;   //�����쳣����ת������ҳ��
	      }
	      return mv ;
   }
   
   //URL����:/ZZUPrj/User/ValidateUserNameExist
   @RequestMapping("/validateUserNameExist")
   @ResponseBody
   public String ValidateUserNameExist(@RequestParam("userName") String userName){
	      String jsonStr=null ;   //JSON�ַ���
	      try{
	    	  User user=this.userService.findByUserName(userName) ;   //�����û��������û�
	    	  jsonStr=new ObjectMapper().writeValueAsString(user) ;  //��װJSON��
	      }catch(Exception e){
	    	   jsonStr="err"  ;   //���ش�����Ϣ 
	      }
	      return jsonStr  ;
   }
   
   //URL����:http://localhost/ZZUPrj/User/doCreate
   @RequestMapping("/doCreate")
   public String doCreate(User user){
	      String page="redirect:/User/list" ;
	      try{
	    	   System.out.println("������ϴ��");
	    	   List<Role> oldRoles=user.getRoles() ;    //��ȡ��ɫ�б� 
	    	   List<Role> newRoles=this.clearNullRoles(oldRoles) ;   //������ϴ 
	    	   user.setRoles(newRoles);    //����װ�ؽ�ɫ 
	    	   Integer count=0 ;
	    	   count=this.userService.doCreate(user) ;  //�����û�
	    	   if(count==0){
	    		   throw new Exception();
	    	   }
	      }catch(Exception e){
	    	  System.out.println(e);
	    	  page="/err"  ;    //�����쳣����ת������ҳ�� 
	      }
	      return page;
   }
   
   
   private List<Role> clearNullRoles(List<Role> oldRoles){     //��ϴ��ɫ�б� 
	       List<Role> newRoles=new ArrayList<Role>()  ;       
	       for(Role role : oldRoles){
	    	    if(role.getRoleId()!=null){   //��ɫѡ�� 
	    	    	newRoles.add(role) ;     //����ɫ���
	    	    }
	       }
	       return newRoles  ;
   }
   
    
   
   //URL����:http://localhost/ZZUPrj/User/doEdit/6
   @RequestMapping("/doEdit/{userId}")
   public ModelAndView doEdit(@PathVariable("userId") Integer userId){
	      ModelAndView mv=new ModelAndView("user_update");  //�ƻ���ת����ʾ�û����б� 
	      try{
	    	  User user = this.userService.findById(userId) ;   //����ID�����û�������Ϣ
	    	  List<Role> myRoles=user.getRoles()  ;          //�û�ӵ�еĽ�ɫ
	    	  List<Role> allRoles=this.roleService.findAll() ;   //���ҵ�ȫ����ɫ 
	    	  mv.addObject("user", user)  ;   //�����û�ģ�� 
	    	  mv.addObject("myRoles", myRoles) ;  //���ӽ�ɫģ�� 
	    	  mv.addObject("allRoles", allRoles) ;  //���ӽ�ɫģ�� 
	      }catch(Exception e){
	    	  mv.setViewName("err");   //�����쳣����ת������ҳ 
	      }
	      return mv ;
   }
   
   
   //URL����:/ZZUPrj/User/doUpdate
   @RequestMapping("/doUpdate")
   public String doUpdate(User user){
	    String page="redirect:/User/list"  ;
	    try{
	    	List<Role> oldRoles=user.getRoles()   ;    //ȡ��ȫ��������ɫ 
	    	List<Role> newRoles = this.clearNullRoles(oldRoles);  //��ɫ��ϴ 
	    	user.setRoles(newRoles) ;   //���������û��Ľ�ɫ 
	    	Integer count=0;
	    	count=this.userService.doUpdate(user)  ;   //�����û�
	    	if(count==0){
	    		  throw new Exception();
	    	}
	    }catch(Exception e){
	    	page="err"  ;     //�����쳣��ת������ҳ 
	    }
	    return page  ;
   }
   
   
   //URL����:http://localhost/ZZUPrj/User/doRemove/6
   @RequestMapping("/doRemove/{userId}")
   public String doRemove(@PathVariable("userId") Integer userId){
	      String page="redirect:/User/list"   ;
	      try{
	    	  Integer count=0 ;
	    	  count=this.userService.doRemove(userId) ;
	    	  if(count==0){
	    		  throw new Exception()  ;
	    	  }
	      }catch(Exception e){
	    	  page="err"  ;     //�����쳣����ת������ҳ 
	      }
	      return page ;
   }
   
   
   
   
	
	
}
