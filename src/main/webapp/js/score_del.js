$(function(){
    	 $("a[name='delHref']").click(function(){
    		 var scoreId=$(this).parent().parent().find("td[name='sid']").text() ;  //取得成绩代码
    		 return window.confirm("您确定要删除成绩编号为["+scoreId+"]的成绩吗") ;     //删除提示   
    	 });
     });