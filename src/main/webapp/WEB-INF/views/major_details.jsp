<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专业详情</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="content">
		    <jsp:include page="left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				   <table class="table table-hover table-bordered">
                       	<tr class="danger">
                       	  <td>专业编号</td>
                       	  <td>${major.majorId}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>专业名称</td>
                       	  <td>${major.majorName}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>简介</td>
                       	  <td>${major.note}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>创建时间</td>
                       	  <td><fmt:formatDate value="${major.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                       	</tr>			       
                       	<tr rowspan="3" class="info">
                       	  <td>归属班级</td>
                       	  <td>
                       	    <c:forEach items="${major.clazzs}" var="clazz">
                       	        <a href="${pageContext.request.contextPath}/Clazz/findDetailsById/${clazz.clazzId}">
                       	          &nbsp;${clazz.clazzName}
                       	        </a>
                       	    </c:forEach>
                         </td>
                       	</tr>			       
                   </table>
				</div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>