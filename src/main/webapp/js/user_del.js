$(function(){
    	  $("a[id='delHref']").click(function(){
    		      var userName=$(this).parent().parent().find("td[name='un']").text() ;    //取得用户名
    		      if(window.confirm("您确定删除用户["+userName+"]吗?")){
    		    	 return true ;  
    		      }
    		         return false ;   //取消超链接的默认行为     
    	  }) ;
     });