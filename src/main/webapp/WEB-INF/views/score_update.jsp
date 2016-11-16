<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改成绩</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- JS验证表单-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/score_validate.js">
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
				         action="${pageContext.request.contextPath}/Score/doUpdate"
				         method="POST">
				       <!-- 设置隐藏域,保存参数,经测试发现表单输入框使用disable无法提交 -->
				       <input type="hidden" value="${score.scoreId}" name="scoreId">
				       <input type="hidden" value="${score.course.courseId}" name="course.courseId">
				       <input type="hidden" value="${score.student.studentId}" name="student.studentId">
				       <div class="form-group" id="studentIdDiv">
                           <label for="studentId" class="col-sm-2 control-label">学号</label>
                           <div class="col-sm-5">
				              <input type="text" class="form-control"  
				                     id="studentId" 
				                     value="${score.student.studentId}" disabled/>
                           </div>
                           <div><span class="col-sm-5" id="studentIdSpan"></span></div>
                       </div>
                        <div class="form-group" id="studentNameDiv">
                           <label for="studentName" class="col-sm-2 control-label">学号</label>
                           <div class="col-sm-5">
				              <input type="text" class="form-control"  
				                     id="studentName" 
				                     value="${score.student.studentName}" disabled/>
                           </div>
                           <div><span class="col-sm-5" id="studentNameSpan"></span></div>
                       </div>
                       <div class="form-group" id="termDiv">
                           <label for="term" class="col-sm-2 control-label">学期</label>
                           <div class="col-sm-5">
				               <input type="text" class="form-control"  id="term" name="term" value="${score.course.term}" disabled/>
                           </div>
                           <div><span class="col-sm-5" id="termSpan"></span></div>
                       </div>
                       <div class="form-group" id="courseIdDiv">
                           <label for="credit" class="col-sm-2 control-label">课程</label>
                           <div class="col-sm-5">
                              <input type="text" class="form-control"  
                                     id="courseId"  
                                     value="${score.course.courseName}" disabled/>
                           </div>
                           <div><span class="col-sm-5" id="courseIdSpan"></span></div>
                       </div>
                       <div class="form-group" id="scoreDiv">
						    <label for="score" class="col-sm-2 control-label">分数</label>
						    <div class="col-sm-5">
						      <input type=text class="form-control" 
						             name="score" id="score"
						             placeholder="请输入分数" value="${score.score}">
						    </div>
						    <div class="col-sm-5"><span id="scoreSpan"></span></div>
                       </div>
		               <div class="form-group" id="btnDiv">
		                   <div class="col-md-5 col-md-offset-2">
		                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">修改</button>
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