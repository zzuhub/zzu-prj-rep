   $(function(){
	    //对用户名的JS验证 
	   $("#userName").blur(function(){
		      validateUserName()  ;
	   });
	   
	   //对密码的JS验证 
	   $("#password").blur(function(){
		    validatePassword()  ;
       });
	   
	   $("#rePassword").blur(function(){    //对密码二次输入进行验证 
		   validateRePassword()  ;
       });
	   
	   $("#subBtn").click(function(){
		    return validateUserName()&&validatePassword()&&validateRePassword() ;
	   });
	
   });
   /*
    *验证用户名是否可用 
   */
   function validateUserName(){
		   var userName=$("#userName").val()   ;    //获取用户输入的用户名 
		   userName=$.trim(userName)   ;    //去除前后空格  
		   if(userName){
				   var url="/ZZUPrj/User/validateUserNameExist"  ;    //URL准备发送Ajax请求 
				   var args={"time":new Date(),"userName":userName} ;   //封装请求参数  
				   $.getJSON(url, args, function(data){
					      if(data==null){    //用户名可用 
					    	    $("#userNameDiv").attr("class","form-group has-success") ;
						         $("#userNameSpan").empty()
						             .append("<span class='text-success'>用户名可以使用</span>") ; 
						         $("#subBtn").removeAttr("disabled") ;
						         return true  ;
					      }else if(data=="err"){  //系统出现异常 
					    	     $("#userNameDiv").attr("class","form-group  has-error") ;
				    		     $("#userNameSpan").empty()
				    		             .append("<span class='text-danger'>系统异常,请重试!</span>") ;  
				    		     $("#subBtn").attr("disabled","disabled"); 
				    		     return false ;
					      }else{   //用户名不可用 
					    	  $("#userNameDiv").attr("class","form-group  has-error") ;
				    		     $("#userNameSpan").empty()
				    		             .append("<span class='text-danger'>用户名已经存在!</span>") ; 
				    		     $("#subBtn").attr("disabled","disabled"); 
				    		     return false ;
					      }
				   });
		   }else{
				  $("#userNameDiv").attr("class","form-group  has-error") ;
	    		     $("#userNameSpan").empty()
	    		             .append("<span class='text-danger'>用户名不能为空!</span>") ;  
	    		     $("#subBtn").attr("disabled","disabled"); 
	    		  return false  ;
		   }
   }
   
   
   /*
    *验证密码是否可用   
   */
   function validatePassword(){
	   var password=$("#password").val()  ;   //取得密码     
	     password=$.trim(password)  ;   //去除空格 
	     if(password){   //密码存在 
		    	 $("#passwordDiv").attr("class","form-group has-success") ;
		         $("#passwordSpan").empty()
		             .append("<span class='text-success'>密码输入正确</span>") ;   
		         $("#subBtn").removeAttr("disabled") ;
		         return true  ;
	     }else{   //密码没输入 
	    	 $("#passwordDiv").attr("class","form-group  has-error") ;
		     $("#passwordSpan").empty()
		             .append("<span class='text-danger'>密码不能为空!</span>") ;   
		     $("#subBtn").attr("disabled","disabled");   
		     return false ;
	     }
   }
   
   function validateRePassword(){
	   var flag=validatePassword()  ;
	   if(flag){   //密码正确 
		   var rePassword=$("#rePassword").val()  ;   //取得待确认的密码 
		   rePassword=$.trim(rePassword) ;   //去除左右空格 
		   if(rePassword){
		      var password=$("#password").val()  ;   //取得密码 
			  if(password==rePassword){
				     $("#rePasswordDiv").attr("class","form-group has-success") ;
			         $("#rePasswordSpan").empty()
			             .append("<span class='text-success'>两次密码一致</span>") ; 
			         $("#subBtn").removeAttr("disabled") ;
			         return true ;
			  }else{
				  $("#rePasswordDiv").attr("class","form-group  has-error") ;
	  		       $("#rePasswordSpan").empty()
	  		             .append("<span class='text-danger'>两次密码不一致</span>") ;   
	  		      $("#subBtn").attr("disabled","disabled"); 
	  		      return false ;
			  }
		   }else{
			   $("#rePasswordDiv").attr("class","form-group  has-error") ;
  		       $("#rePasswordSpan").empty()
  		             .append("<span class='text-danger'>确认密码不能为空!</span>") ;   
  		      $("#subBtn").attr("disabled","disabled"); 
  		      return false ;
		   }
	   }else{
		   $("#rePasswordDiv").attr("class","form-group  has-error") ;
		       $("#rePasswordSpan").empty()
		             .append("<span class='text-danger'>请先输入密码!</span>") ;    
		      $("#subBtn").attr("disabled","disabled"); 
		      return false ;
	   }
   
   }