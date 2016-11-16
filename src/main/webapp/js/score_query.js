$(function(){
	 $(":button").click(function(){
		  var term = $(this).val() ;   //取得学期
		  var studentId = $("#studentId").val()  ;   //取得学号
		  var url="/ZZUPrj/Score/findScoreByTermAndStudentId"  ;             //请求URL
		  var args={"time":new Date(),"term":term,"studentId":studentId} ;   //请求参数
		  $.getJSON(url,args,function(json){   //发送Ajax请求
			   if(json){
				   if(json.err){
					   alert("系统异常,请重试!");
				   }else{
					   $("#tb").empty() ;   //清空原有成绩
					   for(var x=0;x<json.length;x++){
						   $("#tb").append("<tr class=\"info\">"+
								               "<th>"+(json[x].scoreId)+"</th>"+
								               "<th>"+(json[x].course.courseName)+"</th>"+
								               "<th>"+(json[x].score)+"</th>"+
								               "<th>"+(json[x].course.credit)+"</th>"+
				                            "</tr>");
					   }
				   }
			   }
		  });
	 });
});