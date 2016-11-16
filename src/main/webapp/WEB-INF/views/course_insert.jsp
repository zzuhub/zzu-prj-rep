<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加课程</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- JS验证表单-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/course_validate.js">
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="content">
		    <jsp:include page="left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				   <br><br>
				   <form class="form-horizontal" role="form" 
				         action="${pageContext.request.contextPath}/Course/doCreate"
				         method="POST">
				      <div class="form-group" id="courseNameDiv">
						    <label for="courseName" class="col-sm-2 control-label">课程名称</label>
						    <div class="col-sm-5">
						      <input type=text class="form-control" 
						             name="courseName" id="courseName"
						             placeholder="请输入课程名称">
						    </div>
						    <div class="col-sm-5"><span id="courseNameSpan"></span></div>
                       </div>
				      <div class="form-group" id="noteDiv">
						    <label for="note" class="col-sm-2 control-label">简介</label>
						    <div class="col-sm-5">
						      <textarea class="form-control" rows="3" id="note" name="note"></textarea>
						    </div>
						    <div class="col-sm-5"><span id="noteSpan"></span></div>
                       </div>
                       <div class="form-group" id="termDiv">
                           <label for="term" class="col-sm-2 control-label">学期</label>
                           <div class="col-xs-3">
	                           <select class="form-control input-sm col-sm-5" id="term" name="term">
	                               <option value="0">=====请选择学期======</option>
	                               <c:forEach items="${termMap}" var="term">
	                                    <option value="${term.key}">${term.value}</option>
	                               </c:forEach>
	                           </select>
                           </div>
                           <div><span class="col-sm-5" id="termSpan"></span></div>
                       </div>
                       <div class="form-group" id="creditDiv">
                           <label for="credit" class="col-sm-2 control-label">学分</label>
                           <div class="col-xs-3">
                              <select class="form-control input-sm col-sm-5" id="credit" name="credit">
	                               <option value="0">=====请选择学分======</option>
	                               <c:forEach items="${credits}" var="credit">
	                                    <option value="${credit}">${credit}</option>
	                               </c:forEach>
                              </select>
                           </div>
                           <div><span class="col-sm-5" id="creditSpan"></span></div>
                       </div>
		               <div class="form-group" id="btnDiv">
		                   <div class="col-md-5 col-md-offset-2">
		                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">增加</button>
		                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
		                   </div>
		              </div>
				   </form>
				</div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>