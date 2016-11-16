<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- 验证增加用户的JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_insert.js">
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
				         action="${pageContext.request.contextPath}/User/doCreate"
				         method="POST">
				      <div class="form-group" id="userNameDiv">
						    <label for="userName" class="col-sm-2 control-label">用户名</label>
						    <div class="col-sm-5">
						      <input type=text class="form-control" 
						             name="userName" id="userName" 
						             placeholder="请输入用户名称">
						    </div>
						    <div class="col-sm-5"><span id="userNameSpan"></span></div>
                       </div>
				      <div class="form-group" id="passwordDiv">
						    <label for="passwordDiv" class="col-sm-2 control-label">密码</label>
						    <div class="col-sm-5">
						      <input type="password" class="form-control" 
						             name="password" id="password" 
						             placeholder="请输入用户密码">
						    </div>
						    <div class="col-sm-5"><span id="passwordSpan"></span></div>
                       </div>
				      <div class="form-group" id="rePasswordDiv">
						    <label for="rePasswordDiv" class="col-sm-2 control-label">确认密码</label>
						    <div class="col-sm-5">
						      <input type="password" class="form-control" 
						             name="rePassword" id="rePassword" 
						             placeholder="请确认用户密码">
						    </div>
						    <div class="col-sm-5"><span id="rePasswordSpan"></span></div>
                       </div>
                       <div class="form-group">
                           <label for="selectRole" class="col-sm-2 control-label">权限</label>
                           <div class="col-sm-5">
                              <c:set var="count" value="0"/>
                              <c:forEach items="${roles}" var="role">
                                  <label class="checkbox-inline">
								  <input type="checkbox" id="selectRole" 
								         name="roles[${count}].roleId" value="${role.roleId}">${role.roleName}
								  </label>
                                  <c:set var="count" value="${count+1}"/>
                                  <c:if test="${count%4==0}">
                                      <br/>
                                  </c:if>
                              </c:forEach>
                           </div>
                           <div class="col-sm-5"></div>
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