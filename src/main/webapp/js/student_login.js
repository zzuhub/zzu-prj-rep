/**
 * 验证学生登陆
 */
$(function(){

	$("#studentId,#password").blur(function(){    //取消错误消息显示框
		if($("#errDiv").text()){   //有错误消息
			$("#errDiv").remove()  ;
		}
	});
	
    $("#studentId").blur(function(){   //验证学号
    	  validateStudentId()  ;
    }) ;
    
    $("#password").blur(function(){   //密码校验
          validatePassword()  ;    	
    }) ;
    
    $("#idcode").blur(function(){
    	validateIdcode($(this).val(),$.idcode.getCode())  ;  //校验验证码
    });
    
    $("#subBtn").click(function(){
    	   var studentIdFlag=validateStudentId()   ;   //验证学号
           var passwordFlag=validatePassword()   ;   //验证密码
    	   var idCodeFlag=validateIdcode($("#ehong-code-input").val(),$.idcode.getCode())  ;   //验证码验证
    	   return studentIdFlag && passwordFlag && idCodeFlag ;
    });
    
    $.idcode.setCode();   //设置验证码

    $("#ehong-code-input").bind("blur",function(){        //将验证码输入框绑定验证
        validateIdcode($(this).val(),$.idcode.getCode())  ;
    });
    
	
    
    function validateStudentId(){
    	var flag=true ;
    	var studentId=$("#studentId").val()  ;    //取得学号
    	studentId=$.trim(studentId)   ;           //去除空格 
    	if(studentId){   //学号不为空
    		var url="/ZZUPrj/Student/validateStudentIdExists"   ;   //URL地址
    		var args={"time":new Date(),"studentId":studentId}  ;  //请求参数
    		$.getJSON(url,args,function(data){   //发送Ajax请求
    			   if(data){  //学号可能存在,也可能存在系统异常
    				   if(data.err){   //系统异常
    					   $("#studentIdDiv").attr("class","form-group  has-error") ;
      		               $("#studentIdSpan").empty()
      		                 .append("<span class='text-danger'>系统异常,请重试!</span>") ;
      		               $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
      		               flag=false ;
    				   }else{    //学号存在
    					   $("#studentIdDiv").attr("class","form-group has-success") ;
    	        		   $("#studentIdSpan").empty()
    	        		                       .append("<span class='text-success'>学号输入正确</span>") ;
    	        		    $("#subBtn").removeAttr("disabled") ;   //允许提交 
    	        		    flag=true ;
    				   }
    				    
    			   }else{     //学号不存在,返回的是null
    				     $("#studentIdDiv").attr("class","form-group  has-error") ;
    		             $("#studentIdSpan").empty()
    		                 .append("<span class='text-danger'>学号不存在!</span>") ;
    		             $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
    		             flag=false ;
    			   }
    			   return flag ;
    		}) ;
    	}else{   //学号为空
    		 $("#studentIdDiv").attr("class","form-group  has-error") ;
             $("#studentIdSpan").empty()
                 .append("<span class='text-danger'>学号不能为空!</span>") ;
             $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
             flag=false ;
    	}
    	return flag;
    }
    
    
    function validatePassword(){
    	var flag=false ;
    	var password=$("#password").val() ;   //取得密码
    	password=$.trim(password);            //去除空格 
    	if(password){     //密码不为空
    		 $("#passwordDiv").attr("class","form-group has-success") ;
  		     $("#passwordSpan").empty()
  		                       .append("<span class='text-success'>密码输入正确</span>") ;
  		     $("#subBtn").removeAttr("disabled") ;   //允许提交 
    		 flag=true ;
    	}else{            //密码为空
    		 $("#passwordDiv").attr("class","form-group  has-error") ;
             $("#passwordSpan").empty()
                 .append("<span class='text-danger'>密码不能为空!</span>") ;
             $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
    		 flag=false ;
    	}
    	return flag   ;
    }
    
    
    /**
     *Author ZZU·王成键
     * @param inputIdcode 输入的验证码
     * @param idcode      动态生成的验证码
     */
    function validateIdcode(inputIdcode,idcode){
            var flag=true ;
            if(inputIdcode==""){    //输入的验证码为空
                $("#idcodeDiv").attr("class","form-group  has-error") ;
                $("#idcodeSpan").empty()
                    .append("<span class='text-danger'>验证码内容不能为空!</span>") ;
                flag=false ;
            }else{
                 if(inputIdcode.toUpperCase()==idcode.toUpperCase()){    //输入的验证码和动态生成的验证码一致
                     $("#idcodeDiv").attr("class","form-group has-success") ;
                     $("#idcodeSpan").empty().append("<span class='text-success'>验证码内容输入正确</span>") ;
                     $("#subBtn").removeAttr("disabled") ;   //允许提交 
                     flag=true ;
                 }else{                                                   // //输入的验证码和动态生成的验证码不一致
                     $("#idcodeDiv").attr("class","form-group  has-error") ;
                     $("#idcodeSpan").empty()
                         .append("<span class='text-danger'>验证码内容输入错误!</span>") ;
                     $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
                     flag=false ;
                 }
            }
            return flag  ;
    }
    
	
})  ;







