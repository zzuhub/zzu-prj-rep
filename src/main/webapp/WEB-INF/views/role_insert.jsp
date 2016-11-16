<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加角色</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validateRoleName.js">
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
				         action="${pageContext.request.contextPath}/Role/doCreate"
				         method="POST">
				      <div class="form-group" id="roleNameDiv">
						    <label for="roleName" class="col-sm-2 control-label">角色名称</label>
						    <div class="col-sm-5">
						      <input type=text class="form-control" 
						             name="roleName" id="roleName" 
						             placeholder="请输入角色名称">
						    </div>
						    <div class="col-sm-5"><span id="roleNameSpan"></span></div>
                       </div>
                       <div class="form-group">
                           <label for="selectPermission" class="col-sm-2 control-label">权限</label>
                           <div class="col-sm-5">
                              <c:set var="count" value="0"/>
                              <c:forEach items="${permissions}" var="permission">
                                  <label class="checkbox-inline">
								  <input type="checkbox" id="selectPermission" 
								         name="permissions[${count}].permissionId" value="${permission.permissionId}">${permission.permissionNote}
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
		                         <!--防止表单重复提交 -->
                                <input type="hidden" name="token" value="${token}" id="token"/>
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