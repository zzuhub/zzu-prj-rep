 $(function(){
	   $("a[name='delHref']").click(function(){
		   var studentName= $(this).parent().parent().find("td[name='sm']").text() ;   //获取学生姓名 
		   studentName=$.trim(studentName);   //去除前后空格   
		   return window.confirm("您确定要删除学生["+studentName+"]吗?") ;
	   }) ;
   });