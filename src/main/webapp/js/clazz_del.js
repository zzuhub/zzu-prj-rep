 $(function(){
    	 $("a[name='delHref']").click(function(){
    		 var clazzName=$(this).parent().parent().find("td[name='cn']").text() ; 
    		 return window.confirm("您确定要删除["+clazzName+"]吗?");
    	 }) ;
     });