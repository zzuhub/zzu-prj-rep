 $(function(){
        	 $("#clazzName").blur(function(){
        		 var clazzName=$(this).val()  ;  //取得班级名称 
        		 clazzName=$.trim(clazzName)  ;  //去除前后空格
        		 validateClazzName(clazzName) ;
        	 });
        	 
        	
        	 
        	 $("#subBtn").click(function(){
        		 
        		 var clazzName=$("#clazzName").val()  ;  //取得班级名称     
        		 clazzName=$.trim(clazzName)  ;  //去除前后空格
        		 
        		 
     		      return validateClazzName(clazzName)  ;  
        		 
        	 });
        	 
        	 function validateClazzName(clazzName){
        		  if(clazzName){           //课程名称存在
        			  var url="/ZZUPrj/Clazz/validateClazzNameExist"  ;    //URL准备发送Ajax请求 
   				     var args={"time":new Date(),"clazzName":clazzName} ;   //封装请求参数  
   				     $.getJSON(url, args, function(data){
   					      if(data==null){    //专业名称可用 
   					    	$("#clazzNameDiv").attr("class","form-group has-success") ;
						         $("#clazzNameSpan").empty()
						             .append("<span class='text-success'>班级名称可以使用</span>") ; 
						         $("#subBtn").removeAttr("disabled") ;
						         return true  ;
   					      }else if(data=="err"){  //系统出现异常 
   					    	  $("#majorNameDiv").attr("class","form-group  has-error") ;
   				    		     $("#majorNameSpan").empty()
   				    		             .append("<span class='text-danger'>系统异常,请重试!</span>") ;  
   				    		     $("#subBtn").attr("disabled","disabled"); 
   				    		     return false ;
   					      }else{   //班级名称不可用 
   					    	  if($("#oldClazzName").val()){   //只有修改操作是才存在旧名称
   					    		 if($("#oldClazzName").val()==data.clazzName){
   					    			$("#clazzNameDiv").attr("class","form-group has-success") ;
   					    			$("#clazzNameSpan").empty()
						             .append("<span class='text-success'>班级名称可以使用</span>") ; 
						            $("#subBtn").removeAttr("disabled") ;
						            return true  ;
   					    		 }else{
   					    			 $("#clazzNameDiv").attr("class","form-group  has-error") ;
   	  				    		     $("#clazzNameSpan").empty()
   	  				    		             .append("<span class='text-danger'>班级名称已经存在!</span>") ; 
   	  				    		     $("#subBtn").attr("disabled","disabled"); 
   	  				    		     return false ;
   					    		 }
   					    	  }else{
   					    		 $("#clazzNameDiv").attr("class","form-group  has-error") ;
  				    		     $("#clazzNameSpan").empty()
  				    		             .append("<span class='text-danger'>班级名称已经存在!</span>") ; 
  				    		     $("#subBtn").attr("disabled","disabled"); 
  				    		     return false ;
   					    	  }
   					      }
   				   });
        			  
        		  }else{
        			   $("#clazzNameDiv").attr("class","form-group  has-error") ;
        		         $("#clazzNameSpan").empty()
        		             .append("<span class='text-danger'>班级名称不能为空!</span>") ;  
        			   $("#subBtn").attr("disabled","disable") ;   //禁止提交 
        			   return false ;
        		  }
        	 }  
        	 
        	 
        	 
        })  ;