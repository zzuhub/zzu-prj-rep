$(function(){
    	 $("#teacherId").blur(function(){
    		 var reg=/^[0-9]*$/  ;    //正则
    		 var teacherId=$(this).val()  ;
    		 if(reg.test(teacherId)){   //输入的是数字
    			  var args={"time":new Date(),"teacherId":teacherId}   ;   //参数
    		      var url="/ZZUPrj/Teach/validateTeacherExist"  ;    //URL
    		      $.getJSON(url,args,function(data){
    		    	             if(data){
    		    	            	 if(data.err){
    		    	            		 $("#teacherIdDiv").attr("class","form-group  has-error") ;
        		    	    		     $("#teacherIdSpan").empty()
        		    	    		             .append("<span class='text-danger'>系统异常,请重试!</span>") ;   
        		    	    		     $("#subBtn").attr("disabled","disabled"); 
    		    	            	 }else{
    		    	            		 $("#teacherIdDiv").attr("class","form-group has-success") ;
        	    				         $("#teacherIdSpan").empty()
        	    				             .append("<span class='text-success'>教师可以使用</span>") ;
        	    				         $("#subBtn").removeAttr("disabled") ;
    		    	            	 }
    		    	             }else{
    		    	            	 $("#teacherIdDiv").attr("class","form-group  has-error") ;
    		    	    		     $("#teacherIdSpan").empty()
    		    	    		             .append("<span class='text-danger'>教师编号不存在!</span>") ; 
    		    	    		     $("#subBtn").attr("disabled","disabled"); 
    		    	             }
    		      }) ;
    		 }else{    //输入的不是数字 
    			 $("#teacherIdDiv").attr("class","form-group  has-error") ;
    		     $("#teacherIdSpan").empty()
    		             .append("<span class='text-danger'>教师编号格式错误!</span>") ; 
    		     $("#subBtn").attr("disabled","disabled"); 
    		 }
    	 }) ;
    	 
    	 $("#term").change(function(){
    		   var term=$(this).val()   ;  //取得学期 
    		   $("#courseId").empty();   ;  //删除所有节点      
	    	   $("#courseId").append(" <option value='0'>=====请选择课程======</option>");
    		   if(term==0){
    			     $("#termDiv").attr("class","form-group  has-error") ;
	    		     $("#termSpan").empty()
	    		             .append("<span class='text-danger'>请先选择学期!</span>") ;    
	    		     $("#subBtn").attr("disabled","disabled");  
    		   }else{
    			    $("#termDiv").attr("class","form-group has-success") ;
			        $("#termSpan").empty()
			             .append("<span class='text-success'>学期选择成功</span>") ;
			        $("#subBtn").removeAttr("disabled") ;
    			    args={"time":new Date(),"term":term}  ;   //准备参数 
    			    var url="/ZZUPrj/Teach/getCoursesByTerm"  ;  //请求地址 
    			    $.getJSON(url, args, function(json){
    			    	      if(json.err){
    			    	    	    $("#termDiv").attr("class","form-group  has-error") ;
    				    		     $("#termSpan").empty()
    				    		             .append("<span class='text-danger'>系统异常,请重试!</span>") ;     
    				    		     $("#subBtn").attr("disabled","disabled");  
    			    	      }else{
    			    	    	    for(var x=0;x<json.length;x++){
    			    	    	    	$("#courseId").append(" <option value="+json[x].courseId+">"+json[x].courseName+"</option>");
    			    	    	    }
    			    	      }
    			    })    ;   
    		   }
    	 })   ;
    	 
    	 
    	 
    	 
    	$("#major").change(function(){
    		   $("#clazzId").empty();   ;  //删除所有节点      
	    	   $("#clazzId").append(" <option value='0'>=====请选择班级======</option>");
    		   var majorId=$(this).val() ;  //取得专业代码 
    		   if(majorId==0){   //专业代码不存在 
    			     $("#majorDiv").attr("class","form-group  has-error") ;
	    		     $("#majorSpan").empty()
	    		             .append("<span class='text-danger'>请先选择专业!</span>") ;      
	    		     $("#subBtn").attr("disabled","disabled"); 
    		   }else{   //调用Ajax得到班级列表 
    			    $("#majorDiv").attr("class","form-group has-success") ;
			        $("#majorSpan").empty()
			             .append("<span class='text-success'>专业选择成功</span>") ;  
			        $("#subBtn").removeAttr("disabled") ;
    			   args={"time":new Date(),"majorId":majorId}  ;   //准备参数 
   			       var url="/ZZUPrj/Teach/getClazzsByMajorId"  ;  //请求地址  
	   			    $.getJSON(url, args, function(json){
			    	      if(json.err){
			    	    	    $("#majorDiv").attr("class","form-group  has-error") ;
				    		     $("#majorSpan").empty()
				    		             .append("<span class='text-danger'>系统异常,请重试!</span>") ;     
				    		     $("#subBtn").attr("disabled","disabled");  
			    	      }else{
			    	    	    for(var x=0;x<json.length;x++){
			    	    	    	$("#clazzId").append(" <option value="+json[x].clazzId+">"+json[x].clazzName+"</option>");
			    	    	    }
			    	      }
			    })    ;   
    		   }
    	}) ;
    	 
    	$("#subBtn").click(function(){
    		  var courseId=$("#courseId").val()  ;
    		  var teacherId=$("#teacherId").val()  ;
    		  if(!teacherId){
    			  teacherId = 0  ;   
    		  }
    		  var clazzId=$("#clazzId").val()  ;
    		  var flag1=courseId==0?false:true  ;
    		  var flag2=teacherId==0?false:true  ;
    		  var flag3=clazzId==0?false:true  ;
    		  var flag=flag1 && flag2 && flag3    ;
    		  if(!flag){
    			  alert("请先选择全面!!!")  ;  
    		  }
    		  return flag ;
    	}) ; 
    	 
    	 
     });