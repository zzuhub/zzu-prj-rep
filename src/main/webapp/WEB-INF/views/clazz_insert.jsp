<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加班级</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- JS验证表单-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clazz_validate.js">
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
				         action="${pageContext.request.contextPath}/Clazz/doCreate"
				         method="POST">
				      <div class="form-group" id="clazzNameDiv">
						    <label for="clazzName" class="col-sm-2 control-label">班级名称</label>
						    <div class="col-sm-5">
						      <input type=text class="form-control" 
						             name="clazzName" id="clazzName"
						             placeholder="请输入班级名称">
						    </div>
						    <div class="col-sm-5"><span id="clazzNameSpan"></span></div>
                       </div>
                       <div class="form-group" id="majorDiv">
                           <label for="term" class="col-sm-2 control-label">专业</label>
                           <div class="col-xs-3">
	                           <select class="form-control input-sm col-sm-5" id="major" name="major.majorId">
	                               <option value="0" selected="selected">=====请选择专业======</option>
	                               <c:forEach items="${majors}" var="major">
	                                    <option value="${major.majorId}">${major.majorName}</option>
	                               </c:forEach>
	                           </select>
                           </div>
                           <div><span class="col-sm-5" id="majorSpan"></span></div>
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