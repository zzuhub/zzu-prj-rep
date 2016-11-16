    $(function(){
    	$("a[name='delHref']").click(function(){
    		 var courseName=$(this).parent().parent().find("td[name='cn']").text() ;
             return window.confirm("您确定要删除课程["+courseName+"]吗?") ;  
    	}) ;
    });