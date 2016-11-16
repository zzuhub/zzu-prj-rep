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
	private EncryptUtils encryptUtils  ;    //加密工具
	
	@Autowired
	private IRoleService roleService   ;    //角色服务层
	
	
	@Autowired
	private IUserService userService   ;    //用户服务层
	

	@RequestMapping("/login")
	public String login(User user,HttpServletRequest httpReq){
		 String page="index"  ;     //没有出现登陆异常,跳转到后台首页
		 String userName=user.getUserName()  ;   //获取用户名
		 String password=user.getPassword()  ;   //获取密码
		 Subject currentUser=SecurityUtils.getSubject()  ;   //CurrentUser
		 UsernamePasswordToken token=null ;
		 token=new UsernamePasswordToken(userName, password)  ;   //创建令牌
		 HttpSession session = httpReq.getSession();   //获取Session
		 try{
			currentUser.login(token);    //登陆验证
		    password=userName+"@"+password ;    //加盐处理
		    password=this.encryptUtils.encBase64(password)  ;   //对密码进行加密
		    user.setPassword(password);    //重新设置密码,因为要进入数据库中修改最后一次登录时间,肯定是要把明文转换文密文才能修改成功
			this.userService.doUpdateLastLogin(user)  ;   //重新设置设置登陆时间
			user=this.userService.findByUserName(userName)  ;   //重新获取User(因为包含ID方便执行CRUD)
			session.setAttribute("user", user); //向Session中保存属性
		 }catch(UnknownAccountException e1){
			 session.setAttribute("errMsg", "用户名错误,请重新登陆!");   //设置错误信息
			 page="redirect:/login.jsp"  ;
		 }catch(IncorrectCredentialsException e2){
			 session.setAttribute("errMsg", "密码错误,请重新登陆!");   //设置错误信息
			 page="redirect:/login.jsp"  ;
		 }catch(Exception e3){
			 session.setAttribute("errMsg", "系统出现异常,请重新登陆!");   //设置错误信息
			 page="redirect:/login.jsp"  ;
		 }
		 return page ;
	}
	
	
   @RequestMapping("/logout")
   public String logout(){
	      String page="redirect:/login.jsp" ;  
	      try{
	    	      Subject subject=SecurityUtils.getSubject()  ;
	    	      subject.logout();                                //退出时会自动清理Session
	      }catch(Exception e){
		         page="err"  ;
	      }
	      return page ;
   }
   
   @RequestMapping("/list")
   public ModelAndView findAll(){
	      ModelAndView mv=new ModelAndView("user_list")  ;
	      try{
	    	   List<User> users=this.userService.findAll()  ;      //查询到全部用户信息
	    	   mv.addObject("users", users) ;   //添加用户模型
	      }catch(Exception e){
	    	   mv.setViewName("err") ;   //出现异常就跳转到错误页
	      }
	      return mv ;
   }
   
   //URL解析:http://localhost/ZZUPrj/Role/findDetailsById/17
   @RequestMapping("/findDetailsById/{userId}")
   public ModelAndView findDetailsById(@PathVariable("userId") Integer userId){
	      ModelAndView mv=new ModelAndView("user_details")  ;   //默认视图为user_details.jsp
	      try{
	    	   User user=this.userService.findById(userId)   ;  //根据ID查询用户
	    	   String newPass=this.encryptUtils.encBase64(user.getUserName()+"@"+user.getPassword()) ;
	    	   user.setPassword(newPass);
	    	   mv.addObject("user", user)  ;   //添加User模型
	      }catch(Exception e){
	    	   mv.setViewName("err") ;                    //出现异常就跳转到错误页面
	      }
	      return mv ;
   }
   
   //URL解析:http://localhost/ZZUPrj/User/doCreatePre
   @RequestMapping("/doCreatePre")
   public ModelAndView doCreatePre(){
	      ModelAndView mv=new ModelAndView("user_insert") ;   //默认跳转到增加用户的页面
	      try{
	    	  List<Role> role=this.roleService.findAll()  ;     //找到全部角色
	    	  mv.addObject("roles", role) ;   //增加角色模型
	      }catch(Exception e){
	    	  mv.setViewName("err") ;   //出现异常就跳转到错误页面
	      }
	      return mv ;
   }
   
   //URL解析:/ZZUPrj/User/ValidateUserNameExist
   @RequestMapping("/validateUserNameExist")
   @ResponseBody
   public String ValidateUserNameExist(@RequestParam("userName") String userName){
	      String jsonStr=null ;   //JSON字符串
	      try{
	    	  User user=this.userService.findByUserName(userName) ;   //根据用户名查找用户
	    	  jsonStr=new ObjectMapper().writeValueAsString(user) ;  //封装JSON串
	      }catch(Exception e){
	    	   jsonStr="err"  ;   //返回错误信息 
	      }
	      return jsonStr  ;
   }
   
   //URL解析:http://localhost/ZZUPrj/User/doCreate
   @RequestMapping("/doCreate")
   public String doCreate(User user){
	      String page="redirect:/User/list" ;
	      try{
	    	   System.out.println("数据清洗后");
	    	   List<Role> oldRoles=user.getRoles() ;    //获取角色列表 
	    	   List<Role> newRoles=this.clearNullRoles(oldRoles) ;   //数据清洗 
	    	   user.setRoles(newRoles);    //重新装载角色 
	    	   Integer count=0 ;
	    	   count=this.userService.doCreate(user) ;  //增加用户
	    	   if(count==0){
	    		   throw new Exception();
	    	   }
	      }catch(Exception e){
	    	  System.out.println(e);
	    	  page="/err"  ;    //出现异常就跳转到错误页面 
	      }
	      return page;
   }
   
   
   private List<Role> clearNullRoles(List<Role> oldRoles){     //清洗角色列表 
	       List<Role> newRoles=new ArrayList<Role>()  ;       
	       for(Role role : oldRoles){
	    	    if(role.getRoleId()!=null){   //角色选定 
	    	    	newRoles.add(role) ;     //将角色添加
	    	    }
	       }
	       return newRoles  ;
   }
   
    
   
   //URL解析:http://localhost/ZZUPrj/User/doEdit/6
   @RequestMapping("/doEdit/{userId}")
   public ModelAndView doEdit(@PathVariable("userId") Integer userId){
	      ModelAndView mv=new ModelAndView("user_update");  //计划跳转到显示用户的列表 
	      try{
	    	  User user = this.userService.findById(userId) ;   //根据ID查找用户完整信息
	    	  List<Role> myRoles=user.getRoles()  ;          //用户拥有的角色
	    	  List<Role> allRoles=this.roleService.findAll() ;   //查找到全部角色 
	    	  mv.addObject("user", user)  ;   //增加用户模型 
	    	  mv.addObject("myRoles", myRoles) ;  //增加角色模型 
	    	  mv.addObject("allRoles", allRoles) ;  //增加角色模型 
	      }catch(Exception e){
	    	  mv.setViewName("err");   //出现异常就跳转到错误页 
	      }
	      return mv ;
   }
   
   
   //URL解析:/ZZUPrj/User/doUpdate
   @RequestMapping("/doUpdate")
   public String doUpdate(User user){
	    String page="redirect:/User/list"  ;
	    try{
	    	List<Role> oldRoles=user.getRoles()   ;    //取得全部待定角色 
	    	List<Role> newRoles = this.clearNullRoles(oldRoles);  //角色清洗 
	    	user.setRoles(newRoles) ;   //重新设置用户的角色 
	    	Integer count=0;
	    	count=this.userService.doUpdate(user)  ;   //更新用户
	    	if(count==0){
	    		  throw new Exception();
	    	}
	    }catch(Exception e){
	    	page="err"  ;     //出现异常跳转到错误页 
	    }
	    return page  ;
   }
   
   
   //URL解析:http://localhost/ZZUPrj/User/doRemove/6
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
	    	  page="err"  ;     //出现异常就跳转到错误页 
	      }
	      return page ;
   }
   
   
   
   
	
	
}
