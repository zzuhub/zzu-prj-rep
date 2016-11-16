<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- 导入验证的JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/student_op_update.js">
</script>
</head>
<body>
    <!--包含头部信息-->
	<jsp:include page="student_op_header.jsp"></jsp:include>
	<div id="content">
	        <!-- 包含菜单信息 -->
		    <jsp:include page="student_op_left.jsp"></jsp:include>
			<div class="m-right">
			  <div class="main">
			    <br/><br/>
			    <form action="${pageContext.request.contextPath}/Student/doUpdatePassword" method="post" id="updateForm" class="form-horizontal">
		            <fieldset>
		                <div class="form-group" id="studentIdDiv">
		                    <label class="control-label col-md-3" for="studentId">学号</label>
		                    <div class="col-md-5">
		                        <input type="text" 
		                              id="studentId" class="form-control" 
		                              value="${student.studentId}" disabled>
		                         <!-- 隐藏学号 -->
		                         <input type="hidden" value="${student.studentId}" name="studentId" >
		                    </div>
		                    <span class="col-md-4" id="studentIdSpan"></span>
		                </div>
		                <div class="form-group" id="studentNameDiv">
		                    <label class="control-label col-md-3" for="oldPassword">姓名</label>
		                    <div class="col-md-5">
		                        <input type="text" name="studentName" 
		                               id="studentName" class="form-control" 
		                               value="${student.studentName}" disabled>
		                    </div>
		                    <span class="col-md-4" id="studentNameSpan"></span>
		                </div>
		                <div class="form-group" id="oldPasswordDiv">
		                    <label class="control-label col-md-3" for="oldPassword">原密码</label>
		                    <div class="col-md-5">
		                        <input type="password" name="oldPassword" id="oldPassword" class="form-control" placeholder="请输入原密码">
		                    </div>
		                    <span class="col-md-4" id="oldPasswordSpan"></span>
		                </div>
		                <div class="form-group" id="passwordDiv">
		                    <label class="control-label col-md-3" for="password">新密码</label>
		                    <div class="col-md-5">
		                        <input type="password" name="password" id="password" class="form-control" placeholder="请输入新密码">
		                    </div>
		                    <span class="col-md-4" id="passwordSpan"></span>
		                </div>
		                <div class="form-group" id="btnDiv">
		                    <div class="col-md-5 col-md-offset-3">
		                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">修改</button>
		                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
		                    </div>
		                </div>
		            </fieldset>
		         </form>
			   </div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>