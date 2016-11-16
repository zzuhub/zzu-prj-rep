<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- 删除提示信息的JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/role_del.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="content">
		    <jsp:include page="left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				   <table class="table table-hover table-bordered">
				        <thead>
				           <tr class="success">
				             <th>角色编号</th>
				             <th>角色名称</th>
				             <th>详细</th>
				             <th colspan="2">操作</th>
				           </tr>
				        </thead>
				        <tbody>
                          <c:forEach items="${roles}" var="role">
	                         <tr class="active">
					            <td>${role.roleId}</td>
					            <td name="rn">${role.roleName}</td>
					            <td><a href="${pageContext.request.contextPath}/Role/findDetailsById/${role.roleId}">
					                     <span class="glyphicon glyphicon-search">查看</span>
					                </a>
					            </td>
					            <td><a href="${pageContext.request.contextPath}/Role/doEdit/${role.roleId}">
					                  <span class="glyphicon glyphicon-edit">编辑</span>    
					                </a>
					            </td>
					            <td><a href="${pageContext.request.contextPath}/Role/doRemove/${role.roleId}" id="delHref">
					                  <span class="glyphicon glyphicon-remove">删除</span>  
					                 </a>
					            </td>
					         </tr>
                          </c:forEach>
				        </tbody>
                   </table>
				</div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>