$(function(){
	
	var changeCourseIdSelect=false   ;   //默认不改变课程下拉列表
	
	$("#studentId").blur(function(){
		  validateStudentId()  ;    //验证学号
	}) ;
	
	$("#term").change(function(){
		  changeCourseIdSelect=true  ;    //改变课程下拉列表 
		  validateTerm()  ;
	});
	
	$("#score").blur(function(){
		 validateScore() ;
	});
	
	$("#courseId").change(function(){
		 validateCourseId()  ;
	});
	
	$("#subBtn").click(function(){
		 var studentIdFlag=validateStudentId() ;   //验证学号
		 var termFlag=validateTerm()  ;            //验证学期 
		 var courseIdFlag=validateCourseId() ;    //验证课程
		 var scoreFlag=validateScore()   ;        //验证分数
		 return studentIdFlag && termFlag && courseIdFlag && scoreFlag ;    //全部验证通过才能进行最终的提交  
	});
	
	
	
	
	function validateTerm(){
		 var flag=false ;   //默认
		 var term=$("#term").val();  //取得学期
		 if(term==0){    //没选择学期 
			 $("#termDiv").attr("class","form-group  has-error") ;
		     $("#termSpan").empty()
		             .append("<span class='text-danger'>学期不能为空!</span>") ;  
		     $("#subBtn").attr("disabled","disabled"); 
		     $("#courseId").empty()
             .append("<option value=\"0\">=====请选择课程======</option>");  //清空课程下拉列表 
		     flag=false ;
		 }else{   //选择了学期
			 $("#termDiv").attr("class","form-group has-success") ;
	         $("#termSpan").empty()
	             .append("<span class='text-success'>学期选择正确</span>") ; 
	         $("#subBtn").removeAttr("disabled") ;
			 if(changeCourseIdSelect){            //改变课程下拉列表
		         genCourseList(term) ;           //生成课程列表 
		         changeCourseIdSelect=false ;   //不允许改变
			 }
			 flag= true  ;
		 }
		 return flag ;
	}
	
	function validateStudentId(){    //验证学号
		 var flag=true ;    //默认情况下,不允许提交
		 var studentId=$("#studentId").val()   ;    //学号
		 studentId=$.trim(studentId)    ;
		 if(studentId){   //学号确实输入了
			 var url="/ZZUPrj/Student/validateStudentIdExists" ;          //请求路径 
			 var args = {"time":new Date(),"studentId":studentId}   ;   //准备请求参数
			 $.get(url,args,function(data){   //发送Ajax请求 
				   if(data.match("^\{(.+:.+,*){1,}\}$")){   //存在JSON格式串
					   if(data.err=="err"){
						     $("#studentIdDiv").attr("class","form-group  has-error") ;
						     $("#studentIdSpan").empty()
						             .append("<span class='text-danger'>系统异常,请重试!</span>") ;  
						     $("#subBtn").attr("disabled","disabled"); 
						     flag=false ;
					   }else{
						     $("#studentIdDiv").attr("class","form-group has-success") ;
					         $("#studentIdSpan").empty()
					             .append("<span class='text-success'>学号可以使用</span>") ; 
					         $("#subBtn").removeAttr("disabled") ;
					         return true  ;
					   }
				   }else{
					     $("#studentIdDiv").attr("class","form-group  has-error") ;
					     $("#studentIdSpan").empty()
					             .append("<span class='text-danger'>学号不存在!</span>") ;  
					     $("#subBtn").attr("disabled","disabled"); 
					     flag=false ;
				   }
			 }) ;
		 }else{  //没有输入学号 
			 $("#studentIdDiv").attr("class","form-group  has-error") ;
		     $("#studentIdSpan").empty()
		             .append("<span class='text-danger'>学号不能为空!</span>") ;  
		     $("#subBtn").attr("disabled","disabled"); 
		     flag=false ;
		 }
		 return flag   ;
	}
	
	
	function genCourseList(term){   //生成课程列表
		  $("#courseId").empty()
		                .append("<option value=\"0\">=====请选择课程======</option>");  //重新装配
		  var url="/ZZUPrj/Score/findCoursesByTerm"   ;   //URL地址
		  var args={"time":new Date(),"term":term}    ;   //学期
		  $.getJSON(url,args,function(data){
			      if(data){   
			    	  for(var x=0;x<data.length;x++){
			    		  $("#courseId")
				    	   .append("<option value='"+(data[x].courseId)+"'>"+(data[x].courseName)+"</option>");  
			    	  }  
			      }
		  })  ;
	}
	
	
	function validateScore(){
		 var flag=false ;    //默认
		 var score=$("#score").val() ;   //取得输入的分数 
		 if(score){
			 if(/^[0-9]*$/.test(score)){    //输入的是数字
				 $("#scoreDiv").attr("class","form-group has-success") ;
		         $("#scoreSpan").empty()
		             .append("<span class='text-success'>分数输入正确</span>") ; 
		         $("#subBtn").removeAttr("disabled") ;
		         flag= true  ;
			 }else{
				 $("#scoreDiv").attr("class","form-group  has-error") ;
			     $("#scoreSpan").empty()
			             .append("<span class='text-danger'>分数格式错误!</span>") ;  
			     $("#subBtn").attr("disabled","disabled"); 
			     flag=false ;
			 }
		 }else{
			 $("#scoreDiv").attr("class","form-group  has-error") ;
		     $("#scoreSpan").empty()
		             .append("<span class='text-danger'>分数不能为空!</span>") ;  
		     $("#subBtn").attr("disabled","disabled"); 
		     flag=false ;
		 }
		 return flag ;
	}
	
	function validateCourseId(){
		 var flag=false ;   //默认
		 var courseId=$("#courseId").val()  ;   //取得课程代码
		 if(courseId==0){
			 $("#courseIdDiv").attr("class","form-group  has-error") ;
		     $("#courseIdSpan").empty()
		             .append("<span class='text-danger'>课程不能为空!</span>") ;  
		     $("#subBtn").attr("disabled","disabled"); 
		     flag=false ;
		 }else{
			 $("#courseIdDiv").attr("class","form-group has-success") ;
	         $("#courseIdSpan").empty()
	             .append("<span class='text-success'>课程选择正确</span>") ; 
	         $("#subBtn").removeAttr("disabled") ;
	         flag= true  ;
		 }
		 return flag   ;
	}
	
	
}) ;