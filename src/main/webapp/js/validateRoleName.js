 $(function(){
    	 $("#roleName").blur(function(){
    		  var roleName=$(this).val()  ;   //取得角色名称
    		  roleName=$.trim(roleName) ;   //去除空格 
    		  if(roleName){
    			  var url="/ZZUPrj/Role/ValidateRoleNameExist" ;   //定义URL
    			  var args={"time":new Date(),"roleName":roleName} ;   //请求参数 
    			   $.post(url,args,function(data){
    				     if(data!="null"){
    				    	 $("#roleNameDiv").attr("class","form-group  has-error") ;
    		    		     $("#roleNameSpan").empty()
    		    		             .append("<span class='text-danger'>角色名称已经存在!</span>") ;
    		    		     $("#subBtn").attr("disabled","disabled"); 
    				     }else{
    				    	 $("#roleNameDiv").attr("class","form-group has-success") ;
    				         $("#roleNameSpan").empty()
    				             .append("<span class='text-success'>角色名称输入正确</span>") ;
    				         $("#subBtn").removeAttr("disabled") ;
    				     }
    			   });
    		  }else{
    			   $("#roleNameDiv").attr("class","form-group  has-error") ;
    		       $("#roleNameSpan").empty()
    		             .append("<span class='text-danger'>角色名称不能为空!</span>") ;
    		       $("#subBtn").attr("disabled","disabled"); 
    		  }
    		  
    	 }) ;
     });