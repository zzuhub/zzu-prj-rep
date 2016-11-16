   $(function(){
	        	$("#teacherName").blur(function(){
	        		  var teacherName=$(this).val() ;    //取得教师名称 
	        		  teacherName=$.trim(teacherName) ;  //去除空格 
	        		  validateTeacherName(teacherName) ;   //验证teacherName 
	        	}) ;
	        	
	        	$("#subBtn").click(function(){
	        		  var teacherName=$("#teacherName").val() ;    //取得教师名称  
	        		  teacherName=$.trim(teacherName) ;  //去除空格 
	        		  return validateTeacherName(teacherName) ;   //验证teacherName 
	        	});
	        	
        	 function validateTeacherName(teacherName){
        		  if(teacherName){           //课程名称存在
        			  $("#teacherNameDiv").attr("class","form-group has-success") ;
        		      $("#teacherNameSpan").empty()
        		                       .append("<span class='text-success'>教师名称输入正确</span>") ; 
        		      $("#subBtn").removeAttr("disabled") ;   //允许提交
        			  return true ;
        		  }else{
        			   $("#teacherNameDiv").attr("class","form-group  has-error") ;
        		         $("#teacherNameSpan").empty()
        		             .append("<span class='text-danger'>教师名称不能为空!</span>") ;    
        			   $("#subBtn").attr("disabled","disable") ;   //禁止提交 
        			   return false ;
        		  }
        	 } 
        	 
        	 
        })  ;