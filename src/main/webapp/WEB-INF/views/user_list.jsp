<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- 删除提示信息的JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_del.js">
</script>
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
				             <th>用户编号</th>
				             <th>名称</th>
				             <th>密码</th>
				             <th>创建时间</th>
				             <th>修改时间</th>
				             <th>上次登陆</th>
				             <th>详细</th>
				             <th colspan="2">操作</th>
				           </tr>
				        </thead>
				        <tbody>
                          <c:forEach items="${users}" var="user">
	                         <tr class="active">
					            <td>${user.userId}</td>
					            <td name="un">${user.userName}</td>
					            <td>${user.password}</td>
					            <td>
					              <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					            </td>
					            <td>
					               <fmt:formatDate value="${user.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					            </td>
					            <td>
					              <fmt:formatDate value="${user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"/>
					             </td>
					            <td>
					               <a href="${pageContext.request.contextPath}/User/findDetailsById/${user.userId}">
					                     <span class="glyphicon glyphicon-search">查看</span>
					               </a>
					            </td>
			                    <td>
					               <a href="${pageContext.request.contextPath}/User/doEdit/${user.userId}">
					                  <span class="glyphicon glyphicon-edit">编辑</span>    
					               </a>
			                    </td>
					            <td>
					              <a href="${pageContext.request.contextPath}/User/doRemove/${user.userId}" id="delHref">
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