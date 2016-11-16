 $(function(){
        	 $("#courseName").blur(function(){
        		 var courseName=$(this).val()  ;  //取得课程名称 
        		 courseName=$.trim(courseName)  ;  //去除前后空格
        		 validateCourseName(courseName) ;
        	 });
        	 
        	 $("#note").blur(function(){
        		    var note=$(this).val()  ;   //取得课程简介 
        		    note=$.trim(note)   ;        //去除前后空格 
        		    validateNote(note)  ;     
        	 }) ;
        	 
        	 $("#subBtn").click(function(){
        		 
        		 var courseName=$("#courseName").val()  ;  //取得课程名称     
        		 courseName=$.trim(courseName)  ;  //去除前后空格
        		 
        		 var note=$("#note").val()  ;   //取得课程简介 
     		      note=$.trim(note)   ;        //去除前后空格 
     		      
     		      return validateCourseName(courseName) && validateNote(note)   ;  
        		 
        	 });
        	 
        	 function validateCourseName(courseName){
        		  if(courseName){           //课程名称存在
        			  $("#courseNameDiv").attr("class","form-group has-success") ;
        		      $("#courseNameSpan").empty()
        		                       .append("<span class='text-success'>课程名称输入正确</span>") ;
        		      $("#subBtn").removeAttr("disabled") ;   //允许提交
        			  return true ;
        		  }else{
        			   $("#courseNameDiv").attr("class","form-group  has-error") ;
        		         $("#courseNameSpan").empty()
        		             .append("<span class='text-danger'>课程名称不能为空!</span>") ;  
        			   $("#subBtn").attr("disabled","disable") ;   //禁止提交 
        			   return false ;
        		  }
        	 }   
        	 
        	 
        	 function validateNote(note){
        		  if(note){    //简介存在 
        			  $("#noteDiv").attr("class","form-group has-success") ;
        		      $("#noteSpan").empty()
        		                       .append("<span class='text-success'>课程简介输入正确</span>") ;    
        		      $("#subBtn").removeAttr("disabled") ;   //允许提交
        			  return true ;
        		  }else{
       			      $("#noteDiv").attr("class","form-group  has-error") ;
       		          $("#noteSpan").empty()
       		             .append("<span class='text-danger'>课程简介不能为空!</span>") ;   
        			  $("#subBtn").attr("disabled","disable") ;   //禁止提交 
        			   return false ;
        		  }
        	 }  
        	 
        	 
        	 
        })  ;