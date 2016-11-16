$(function(){
	
	$("#oldPassword").blur(function(){
		 validateOldPassword()  ;       //验证老密码
	}) ;
	
	$("#password").blur(function(){    //验证新密码
		   validatePassword() ;
	});
	
	$("#subBtn").click(function(){
		 var oldPasswordFlag=validateOldPassword()  ;       //验证老密码
		 var passwordFlag=validatePassword() ;             //验证新密码
		 return oldPasswordFlag && passwordFlag ;
	}) ;
	
	function validateOldPassword(){
		var flag=true ;    //默认是true
		var oldPassword=$("#oldPassword").val()  ;   //取得输入的老密码
		oldPassword=$.trim(oldPassword)   ;          //去除空格
		if(oldPassword){   //输入的不是空值 
			var url="/ZZUPrj/Student/findById "  ;   //URL地址
			var studentId=$("#studentId").val()   ;   //取得隐藏域中的学号
			var args={"time":new Date(),"studentId":studentId} ;  //请求参数
			$.getJSON(url,args,function(data){
				    if(data.password==oldPassword){   //密码验证正确
				    	 $("#oldPasswordDiv").attr("class","form-group has-success") ;
    	        		 $("#oldPasswordSpan").empty()
    	        		                       .append("<span class='text-success'>原密码输入正确</span>") ;
    	        		 $("#subBtn").removeAttr("disabled") ;   //允许提交 
    	        		 flag=true ;
				    }else{
				    	if(data.err){
				    		   $("#oldPasswordDiv").attr("class","form-group  has-error") ;
	      		               $("#oldPasswordSpan").empty()
	      		                 .append("<span class='text-danger'>系统异常,请重试!</span>") ;
	      		               $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
	      		               flag=false ;
				    	}else{
				    		 $("#oldPasswordDiv").attr("class","form-group  has-error") ;
	      		               $("#oldPasswordSpan").empty()
	      		                 .append("<span class='text-danger'>原密码输入错误!</span>") ;
	      		               $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
	      		               flag=false ;
				    	}
				    }
			})   ;
		}else{   //输入的是空值
			 $("#oldPasswordDiv").attr("class","form-group  has-error") ;
             $("#oldPasswordSpan").empty()
                 .append("<span class='text-danger'>原密码不能为空!</span>") ;
             $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
    		 flag=false ;
		}
		return flag ;
	}
	
	function validatePassword(){
		  var flag=false ;  //默认
		  var oldPasswordFlag=validateOldPassword()  ;   //先验证老密码输入没
		  if(oldPasswordFlag){   //老密码已经验证通过
			  var password=$("#password").val()  ;   //新密码
			  password=$.trim(password)   ;          //去除空格
			  if(password){   //新密码不是空值
				  var oldPassword=$("#oldPassword").val()   ;   //旧密码
				  if(password==oldPassword){   //新旧密码不能一致
					     $("#passwordDiv").attr("class","form-group  has-error") ;
			             $("#passwordSpan").empty()
			                 .append("<span class='text-danger'>新旧密码不能相同!</span>") ;
			             $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
			    		 flag=false ; 
				  }else{
					  $("#passwordDiv").attr("class","form-group has-success") ;
 	        		  $("#passwordSpan").empty()
 	        		                       .append("<span class='text-success'>新密码输入正确</span>") ;
 	        		  $("#subBtn").removeAttr("disabled") ;   //允许提交 
 	        		  flag=true ;
				  }
			  }else{  //新密码是空值
				     $("#passwordDiv").attr("class","form-group  has-error") ;
		             $("#passwordSpan").empty()
		                 .append("<span class='text-danger'>新密码不能为空!</span>") ;
		             $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
		    		 flag=false ; 
			  }
		  }else{   //老密码没有验证通过
			     $("#passwordDiv").attr("class","form-group  has-error") ;
	             $("#passwordSpan").empty()
	                 .append("<span class='text-danger'>请先输入原密码!</span>") ;
	             $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
	    		 flag=false ;
		  }
		  return flag;
	}
	
	
	
})  ;