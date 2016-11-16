<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="left_menu">
		 <ul id="nav_dot">
	        <li>
	          <h4 class="M9"><span></span>个人管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Student/doUpdatePasswordPre/${sessionScope.student.studentId}">修改密码</a>
	            <a href="${pageContext.request.contextPath}/Student/findStudentInfo/${sessionScope.student.studentId}">查看信息</a>
	          </div>
	        </li>
	        <li>
	          <h4 class="M6"><span></span>成绩管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Student/findScorePre/${sessionScope.student.studentId}">成绩查询</a>
	            <a href="${pageContext.request.contextPath}/Student/getAnysDetails/${sessionScope.student.studentId}">成绩分析</a>
	          </div>
	        </li>
	 		<li>
	          <h4 class="M8"><span></span>敬请期待</h4>
	          <div class="list-item none">
	          </div>
	        </li>
	   </ul>
</div>