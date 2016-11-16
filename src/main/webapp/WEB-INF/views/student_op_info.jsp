<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<!--包含头部信息-->
	<jsp:include page="student_op_header.jsp"></jsp:include>
	<div id="content">
		    <!-- 包含菜单信息 -->
		    <jsp:include page="student_op_left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				   <table class="table table-hover table-bordered">
                       	<tr class="danger">
                       	  <td>学号</td>
                       	  <td colspan="2">${student.studentId}</td>
                       	</tr>			       
                       	<tr>
	                       <td class="success">姓名</td>
	                       <td class="success">${student.studentName}</td>
                       	  <td rowspan="4" width="12%"class="info">
                       	    <img alt="照片" 
                       	         src="${pageContext.request.contextPath}/photo/${student.photo}" 
                       	         class="img-rounded" />
                       	  </td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>班级</td>
                       	  <td>${student.clazz.clazzName}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>性别</td>
                       	  <td>${student.sex}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>年龄</td>
                       	  <td>${student.age}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>地址</td>
                       	  <td colspan="2">${student.address}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>出生日期</td>
                       	  <td colspan="2">
                       	   <fmt:formatDate value="${student.birth}" pattern="yyyy-MM-dd"/>
                       	  </td>
                       	</tr>
                       	<tr class="success">
                       	  <td>电话</td>
                       	  <td colspan="2">${student.tel}</td>
                       	</tr>			       
                       	<tr class="success">
                       	  <td>简介</td>
                       	  <td colspan="2">${student.note}</td>
                       	</tr>			       
                   </table>
				</div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>