<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩查询</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/score_query.js"></script>
</head>
<body>
    <!--包含头部信息-->
	<jsp:include page="student_op_header.jsp"></jsp:include>
	<div id="content">
	        <!-- 包含菜单信息 -->
		    <jsp:include page="student_op_left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				  <br/><br/>
				   <!-- 隐藏学号,方便查询使用 -->
				   <input type="hidden" id="studentId" name="studentId" value="${studentId}" />
				   <table class="table table-bordered table-hover">
				      <thead>
				         <tr class="success">
				           <th>编号</th>
				           <th>课程</th>
				           <th>分数</th>
				           <th>学分</th>
				         </tr>
				      </thead>
				      <tbody id="tb">
				         <c:forEach var="score" items="${scores}">
				           <tr class="info">
				               <th>${score.scoreId}</th>
				               <th>${score.course.courseName}</th>
				               <th>${score.score}</th>
				               <th>${score.course.credit}</th>
				           </tr>
				         </c:forEach>
				      </tbody>
				   </table>
				   <br><br>
				   <c:forEach var="map" items="${termMap}">
					              <button type="button" class="btn btn-success" value="${map.key}">
					                  ${map.value}
					              </button>&nbsp;  
				   </c:forEach>    
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>