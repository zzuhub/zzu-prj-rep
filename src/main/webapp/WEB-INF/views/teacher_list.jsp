<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程列表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- 自定义分页的JS插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pagination.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/teacher_del.js">
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="content">
		    <jsp:include page="left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				   <br><br>
				   <!-------------------------------分页插件↓(根据业务需要添加)----------------------------------------------------------------->
				   <form class="form-horizontal" role="form">
					  <div class="form-group">
					    <label for="keyWordInput" class="col-sm-2 control-label">关键字</label>
					    <div class="col-sm-6">
					      <input type="text" class="form-control" id="keyWordInput" placeholder="请输入关键词" value="${keyWord}">
					    </div>
					    <span class="col-sm-2">
					      <button type="button" class="btn btn-success" id="searchBtn">搜索</button>
					    </span>
					  </div>
				   </form>
				   <!-------------------------------分页插件↑------------------------------------------------------------------->
				   <table class="table table-hover table-bordered">
				        <thead>
				           <tr class="success">
				             <th>编号</th>
				             <th>姓名</th>
				             <th>详细</th>
				             <th colspan="2">操作</th>
				           </tr>
				        </thead>
				        <tbody>
                          <c:forEach items="${teachers}" var="teacher">
	                         <tr class="active">
					            <td>${teacher.teacherId}</td>
					            <td name="tn">${teacher.teacherName}</td>
					            <td>
					               <a href="${pageContext.request.contextPath}/Teacher/findDetailsById/${teacher.teacherId}">
					                     <span class="glyphicon glyphicon-search">查看</span>
					               </a>
					            </td>
					            <td>
					               <a href="${pageContext.request.contextPath}/Teacher/doEdit/${teacher.teacherId}">
					                  <span class="glyphicon glyphicon-edit">编辑</span>    
					               </a>
					            </td>
					            <td>
					               <a href="${pageContext.request.contextPath}/Teacher/doRemove/${teacher.teacherId}" name="delHref">
					                  <span class="glyphicon glyphicon-remove">删除</span> 
					               </a>
					            </td>
					         </tr>
                          </c:forEach>
                             <tr class="active">
                                 <td colspan="8">
                                   <!-------------------------------分页插件↓----------------------------------------------------------------->
                                   <form action="${pageContext.request.contextPath}/Teacher/list" method="POST" name="spForm" id="spForm">
	                                   <input type="hidden" id="currentPage" name="currentPage" value="${pageInfo.pageNum}">
                                       <input type="hidden" id="lineSize" name="lineSize" value="${pageInfo.pageSize}">
                                       <input type="hidden" id="keyWord" name="keyWord" value="${keyWord}">
	                                   <button type="button" class="btn btn-primary" value="${pageInfo.firstPage}"
	                                           ${pageInfo.pageNum==1?"disabled='disabled'":""}>
	                                                                                             首页
	                                   </button>
	                                   <button type="button" class="btn btn-success" 
	                                           value="${pageInfo.prePage}"
	                                           ${pageInfo.prePage==0?"disabled='disabled'":""}>
	                                                                                           上一页
	                                   </button>
									   &nbsp;&nbsp;&nbsp;跳转到第&nbsp;<div class="btn-group">
										  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="cpBtn">
										     ${pageInfo.pageNum}&nbsp;&nbsp;<span class="caret"></span>
										  </button>
										  <ul class="dropdown-menu" role="menu" id="cpMenu">
										     <c:forEach begin="1" end="${pageInfo.pages}" var="page">
										            <li><a href="#">${page}</a></li>
										     </c:forEach>
										  </ul>
										</div>&nbsp;页&nbsp;&nbsp;&nbsp;					
									   &nbsp;&nbsp;&nbsp;每页显示&nbsp;<div class="btn-group">
										  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="lsBtn">
										     ${pageInfo.pageSize}&nbsp;&nbsp;<span class="caret"></span>
										  </button>
										  <ul class="dropdown-menu" role="menu" id="lsMenu">
										     <c:forEach items="${lineSizes}" var="lineSize">
										            <li><a href="#">${lineSize}</a></li>
										     </c:forEach>
										  </ul>
										</div>&nbsp;条&nbsp;&nbsp;&nbsp;					
									   <button type="button" class="btn btn-info" 
	                                           value="${pageInfo.nextPage}"
	                                           ${pageInfo.nextPage==0?"disabled='disabled'":""}>
	                                                                                            下一页
	                                   </button>
	                                   <button type="button" class="btn btn-danger"value="${pageInfo.lastPage}"
	                                           ${pageInfo.pageNum==pageInfo.lastPage?"disabled='disabled'":""}>
	                                                                                               尾页
	                                   </button>
	                                      &nbsp;共 &nbsp;${pageInfo.size}/${pageInfo.total} &nbsp;条
                                   </form>
                                   <!-------------------------------分页插件↑------------------------------------------------------------------->
                                 </td>
                             </tr>
				        </tbody>
                   </table>
				</div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>