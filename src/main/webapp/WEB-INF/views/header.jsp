<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<div class="top"></div>
<div id="header">
	<div class="logo">学生成绩管理平台</div>
	<div class="navigation">
		<ul>
		 	<li>欢迎您！</li>
		 	<li>${user.userName}</li>
			<li><a href="${pageContext.request.contextPath}/User/logout">安全退出</a></li>
		</ul>
	</div>
</div>