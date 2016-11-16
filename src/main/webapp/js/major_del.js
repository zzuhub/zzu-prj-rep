 $(function(){
    	 $("a[name='delHref']").click(function(){
       	  var majorName=$(this).parent().parent().find("td[name='mn']").text() ;
       	  return window.confirm("您确定要删除["+majorName+"]专业吗?") ;  
       });
     });