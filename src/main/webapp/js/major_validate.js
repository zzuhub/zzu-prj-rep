 $(function(){
        	 $("#majorName").blur(function(){
        		 var majorName=$(this).val()  ;  //取得专业名称 
        		 majorName=$.trim(majorName)  ;  //去除前后空格
        		 validateMajorName(majorName) ;
        	 });
        	 
        	 $("#note").blur(function(){
        		    var note=$(this).val()  ;   //取得专业简介 
        		    note=$.trim(note)   ;        //去除前后空格 
        		    validateNote(note)  ;     
        	 }) ;
        	 
        	 $("#subBtn").click(function(){
        		 
        		 var majorName=$("#majorName").val()  ;  //取得专业名称     
        		 majorName=$.trim(majorName)  ;  //去除前后空格
        		 
        		 var note=$("#note").val()  ;   //取得专业简介 
     		      note=$.trim(note)   ;        //去除前后空格 
     		      
     		      return validateMajorName(majorName) && validateNote(note)   ;  
        		 
        	 });
        	 
        	 function validateMajorName(majorName){
        		  if(majorName){           //课程名称存在
        			  var url="/ZZUPrj/Major/validateMajorNameExist"  ;    //URL准备发送Ajax请求 
   				     var args={"time":new Date(),"majorName":majorName} ;   //封装请求参数  
   				     $.getJSON(url, args, function(data){
   					      if(data==null){    //专业名称可用 
   					    	$("#majorNameDiv").attr("class","form-group has-success") ;
						         $("#majorNameSpan").empty()
						             .append("<span class='text-success'>专业名称可以使用</span>") ; 
						         $("#subBtn").removeAttr("disabled") ;
						         return true  ;
   					      }else if(data=="err"){  //系统出现异常 
   					    	  $("#majorNameDiv").attr("class","form-group  has-error") ;
   				    		     $("#majorNameSpan").empty()
   				    		             .append("<span class='text-danger'>系统异常,请重试!</span>") ;  
   				    		     $("#subBtn").attr("disabled","disabled"); 
   				    		     return false ;
   					      }else{   //专业名称不可用 
   					    	  if($("#oldMajorName").val()){   //只有修改操作是才存在旧名称
   					    		 if($("#oldMajorName").val()==data.majorName){
   					    			$("#majorNameSpan").empty()
						             .append("<span class='text-success'>专业名称可以使用</span>") ; 
						            $("#subBtn").removeAttr("disabled") ;
						            return true  ;
   					    		 }else{
   					    			 $("#majorNameDiv").attr("class","form-group  has-error") ;
   	  				    		     $("#majorNameSpan").empty()
   	  				    		             .append("<span class='text-danger'>专业名称已经存在!</span>") ; 
   	  				    		     $("#subBtn").attr("disabled","disabled"); 
   	  				    		     return false ;
   					    		 }
   					    	  }else{
   					    		 $("#majorNameDiv").attr("class","form-group  has-error") ;
  				    		     $("#majorNameSpan").empty()
  				    		             .append("<span class='text-danger'>专业名称已经存在!</span>") ; 
  				    		     $("#subBtn").attr("disabled","disabled"); 
  				    		     return false ;
   					    	  }
   					      }
   				   });
        			  
        		  }else{
        			   $("#majorNameDiv").attr("class","form-group  has-error") ;
        		         $("#majorNameSpan").empty()
        		             .append("<span class='text-danger'>专业名称不能为空!</span>") ;  
        			   $("#subBtn").attr("disabled","disable") ;   //禁止提交 
        			   return false ;
        		  }
        	 }   
        	 
        	 
        	 function validateNote(note){
        		  if(note){    //简介存在 
        			  $("#noteDiv").attr("class","form-group has-success") ;
        		      $("#noteSpan").empty()
        		                       .append("<span class='text-success'>专业简介输入正确</span>") ;    
        		      $("#subBtn").removeAttr("disabled") ;   //允许提交
        			  return true ;
        		  }else{
       			      $("#noteDiv").attr("class","form-group  has-error") ;
       		          $("#noteSpan").empty()
       		             .append("<span class='text-danger'>专业简介不能为空!</span>") ;   
        			  $("#subBtn").attr("disabled","disable") ;   //禁止提交 
        			   return false ;
        		  }
        	 }  
        	 
        	 
        	 
        })  ;