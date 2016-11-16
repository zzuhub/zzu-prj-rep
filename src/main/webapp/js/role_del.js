 $(function(){
        	$("a[id='delHref']").click(function(){
        		var roleName=$(this).parent().parent().find("td[name='rn']").text() ;
        		if(window.confirm("您确定要删除角色["+roleName+"]吗?")){
        			return true  ;
        		}else{
        			return false   ;
        		}
        	});
        })   ;