/**
 * DevelopTime:2016/10/31
 * ZZU·WangChengJian
 * MyBatis分页插件
 * 使用注意:
 * 1.引入pagination.js,和分页JSP代码
 * 2.控制层把PageInfo传到前台页面
 */
$(function(){
    	 $(":button[id!='cpBtn'][id!='lsBtn'][id!='searchBtn']").click(function(){
             var currentPage=$(this).val()   ;      
             var keyWord=$("#keyWordInput").val()  ;      //这里可以选择是否接收,因为你不一定使用搜索框,看不懂就不需要改写一样可以分页
             $("#currentPage").val(currentPage);
             $("#keyWord").val(keyWord);                  //同上
             $("#spForm").submit() ;
    	 });
    	 $("#cpMenu a").click(function(){
              var currentPage=$(this).text()  ;  
              var keyWord=$("#keyWordInput").val()  ;
              $("#currentPage").val(currentPage);
              $("#keyWord").val(keyWord);
              $("#spForm").submit() ;
    	 });
    	 $("#lsMenu a").click(function(){
    		  var lineSize=$(this).text()   ;
    		  var keyWord=$("#keyWordInput").val()  ;
              $("#lineSize").val(lineSize);
              $("#keyWord").val(keyWord);
              $("#spForm").submit() ;
    	 });
    	 
    	 $("#searchBtn").click(function(){
    		 var keyWord=$("#keyWordInput").val()  ;
              $("#keyWord").val(keyWord);
              $("#spForm").submit() ;
      		 return false ;
    	   });  
    	 
    });