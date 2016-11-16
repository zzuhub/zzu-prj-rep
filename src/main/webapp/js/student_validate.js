$(function(){
	var f1=false ;
	var f2=false ;
	var f3=false ;
	var f4=false ;
	var f5=false ;
	var f6=false ;
	var f7=false ;
	var f8=false ;
	$("#studentId").blur(function(){      //Ajax验证学号是否正确输入
		f1=validateStudentId() ;
	});
	
	$("#studentName").blur(function(){    //学生姓名不能为空 
		f2=validateStudentName()  ;
	}) ;
	
	$("#password").blur(function(){     //学生密码不能为空
		f3=validatePassword()  ;
	}) ;
	
	$("#tel").blur(function(){    //电话号码不能为空且格式正确 
		f4=validateTel()  ;
	}) ;
	
	$("#address").blur(function(){   //地址不能为空
		f5=validateAddress() ;
	}) ;
	
	$("#clazzId").change(function(){   //班级代码必须选择
		f6=validateClazzId()  ;     
	}) ;
	
	$("#note").blur(function(){   //简介不能为空
		f7=validateNote()  ;
	}) ;
	
	$("#subBtn").click(function(){
		f8=validateBirth();
		return f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8;
	}) ;
	
	
	function validateBirth(){  //出生日期验证 
		 var birth=$("#birth").val()  ;     //出生日期
		 if(birth){   //出生日期存在
			 if(/^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/.test(birth)){
				    $("#birthSpan")
	 			    .empty()
	 			    .append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>出生日期输入正确!</font>");
	    		     $("#subBtn").removeAttr("disabled") ;  //提交按钮可用
	    		     return true ;
			 }else{
				 $("#birthSpan")
	 			    .empty()
	 			    .append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>出生日期格式错误!</font>");
	    		     $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
	    		     return false ;
			 }
		 }else{  //出生日期不存在
			 $("#birthSpan")
			    .empty()
			    .append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>请先选择出生日期!</font>");
 		     $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
 		     return false ;
		 }
	}
	
	
	
	
	
	//二级联动菜单
	$("#major").change(function(){
		var majorId=$(this).val()  ;   //取得专业代码
		var url="/ZZUPrj/Student/getClazzsByMajorId" ;   //URL
		var args={"time":new Date(),"majorId":majorId}   ;   //参数
		$.getJSON(url,args,function(data){
			   if(data){
				   $("#clazzId").empty().append("<option value=\"0\">=====请选择班级======</option>") ;
				   for(var x=0;x<data.length;x++){   //循环遍历
					   $("#clazzId")
					   .append("<option value=\""+data[x].clazzId+"\">"+data[x].clazzName+"</option>") ;
				   }
			   }
		}) ;
	});
	
	
	
	
	function validateStudentId(){
		var studentId=$("#studentId").val() ;   //学生编号
		studentId=$.trim(studentId)   ;        //去除空格
		if(studentId){
			var url="/ZZUPrj/Student/validateStudentIdExists"   ;   //URL地址
			var args={"time":new Date(),"studentId":studentId} ;   //请求参数
			$.getJSON(url, args,function(data){
				 if(data){
	            	 if(data.err){
	            		 $("#studentIdSpan")
	 	 			    .empty()
	 	 			    .append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>程序出现异常,请重试!</font>");
    	    		     $("#subBtn").attr("disabled","disabled");    //提交按钮不可以使用
    	    		     return false ;
	            	 }else{
	            		 $("#studentIdSpan")
	 	 			    .empty()
	 	 			    .append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>学号已被使用!</font>");
	            		 $("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
	            		 return false ;
	            	 }
	             }else{
	            	 $("#studentIdSpan")
	 			    .empty()
	 			    .append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>学号可以使用!</font>");
	    		     $("#subBtn").removeAttr("disabled") ;  //提交按钮可用
	    		     return true ;
	             }
			}) ;
		}else{
			$("#studentIdSpan")
			    .empty()
			    .append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>学号不能为空!</font>");
		}
	}
	
	function validateStudentName(){
		var studentName=$("#studentName").val()  ;    //学生姓名
		studentName=$.trim(studentName) ;   //去除空格 
		if(studentName){
			$("#studentNameSpan")
			    .empty()
			    .append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>姓名输入正确!</font>");
		     $("#subBtn").removeAttr("disabled") ;  //提交按钮可用
		     return true ;
		}else{
			 $("#studentNameSpan")
			    .empty()
			    .append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>姓名不能为空!</font>");
     		 $("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
     		 return false ;
		}   
	}
	
	function validatePassword(){
		var password=$("#password").val()  ;    //密码
		password=$.trim(password) ;   //去除空格 
		if(password){
			$("#passwordSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>密码输入正确!</font>");
			$("#subBtn").removeAttr("disabled") ;  //提交按钮可用
			return true ;
		}else{
			$("#passwordSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>密码不能为空!</font>");
			$("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
			return false ;
		}   
	}
	
	function validateAddress(){
		var address=$("#address").val()  ;    //地址
		address=$.trim(address) ;   //去除空格 
		if(address){
			$("#addressSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>地址输入正确!</font>");
			$("#subBtn").removeAttr("disabled") ;  //提交按钮可用
			return true ;
		}else{
			$("#addressSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>地址不能为空!</font>");
			$("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
			return false ;
		}   
	}
	
	function validateNote(){
		var note=$("#note").val()  ;    //简介
		note=$.trim(note) ;   //去除空格 
		if(note){
			$("#noteSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>简介输入正确!</font>");
			$("#subBtn").removeAttr("disabled") ;  //提交按钮可用
			return true ;
		}else{
			$("#noteSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>简介不能为空!</font>");
			$("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
			return false ;
		}   
	}
	
	function validateTel(){
		var tel=$("#tel").val()  ;    //电话
		tel=$.trim(tel) ;   //去除空格 
		if(tel){
			if( /^1[3|4|5|8]\d{9}$/.test(tel)){
				$("#telSpan")
				.empty()
				.append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>电话输入正确!</font>");
				$("#subBtn").removeAttr("disabled") ;  //提交按钮可用
				return true ;
			}else{
				$("#telSpan")
				.empty()
				.append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>电话格式错误!</font>");
				$("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
				return false ;
			}
			
		}else{
			$("#telSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>电话不能为空!</font>");
			$("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
			return false ;
		}   
	}
	
	function validateClazzId(){
		var majorId=$("#major").val()   ;   //专业代码
		var clazzId=$("#clazzId").val()  ;   //班级代码
		if(majorId==0){
			$("#clazzIdSpan")
			.empty()
			.append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>请先选择专业!</font>");
			$("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
			return false ;
		}else{
			if(clazzId==0){
				$("#clazzIdSpan")
				.empty()
				.append("<img src='/ZZUPrj/photo/wrong.gif'><font color='red'>请选择班级!</font>");
				$("#subBtn").attr("disabled","disabled");     //提交按钮不可以使用
				return false ;
			}else{
				$("#clazzIdSpan")
				.empty()
				.append("<img src='/ZZUPrj/photo/right.gif'><font color='green'>班级选择正确!</font>");
				$("#subBtn").removeAttr("disabled") ;  //提交按钮可用
				return true ;
			}
		}
	}
	
	
	
	
});