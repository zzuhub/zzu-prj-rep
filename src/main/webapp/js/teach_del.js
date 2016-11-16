  $(function(){
    	$("a[name='delHref']").click(function(){
    	      var teachId=$(this).parent().parent().find("td[name='tid']").text() ;
    	      return window.confirm("您确定要删除教学记录["+teachId+"]吗?") ;
    	}) ;
    })  ;