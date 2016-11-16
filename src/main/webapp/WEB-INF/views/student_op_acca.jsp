<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据分析</title>
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
				   <br><br>
				   <div class="row">
					   <div class="col-md-4">
					     <div class="alert alert-success" role="alert">
					                    专业总人数:${allCount}
					     </div>
					   </div>
					   <!-- 使用Bootstrap的十二栅格系统进行布局 -->
					   <div class="col-md-8" id="nullSpan"></div>
				   </div>
				   <div class="row">
					   <div class="col-md-4">
					      <div class="alert alert-info" role="alert">
					                     专业排名:${rank}
					      </div>
					   </div>
					   <!-- 使用Bootstrap的十二栅格系统进行布局 -->
					   <div class="col-md-8" id="nullSpan"></div>
				   </div>
				   <!-- 引入JFreeChart图表工具生成的图 -->
				   <img alt="学分修习统计图" 
				     src="${pageContext.request.contextPath}/DisplayChart?filename=${fileName}">
				</div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>