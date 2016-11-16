    $(function(){
    	$("a[name='delHref']").click(function(){
    		 var teacherName=$(this).parent().parent().find("td[name='tn']").text() ;
             return window.confirm("您确定要删除教师["+teacherName+"]吗?") ;  
    	}) ;
    });