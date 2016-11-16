<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<div class="left_menu">
	 <ul id="nav_dot">
	 		<li>
	          <h4 class="M8"><span></span>学信维护</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Student/list">学生列表</a>
			    <a href="${pageContext.request.contextPath}/Student/downloadTemplate">下载模板</a>
	            <!-- 普通管理员不具备更新权限 -->
	            <shiro:hasRole name="SuperAdmin">
	                    <a href="${pageContext.request.contextPath}/Student/doCreatePre">增加学生</a>
			            <a href="${pageContext.request.contextPath}/Student/doCreateBatchPre">批量增加</a>
	            </shiro:hasRole>
	          </div>
	        </li>
	        <li>
	          <h4 class="M2"><span></span>成绩管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Score/list">成绩列表</a>
		        <a href="${pageContext.request.contextPath}/Score/downloadTemplate">下载模板</a>
	            <!-- 普通管理员不具备更新权限 -->
	            <shiro:hasRole name="SuperAdmin">
		            <a href="${pageContext.request.contextPath}/Score/doCreatePre">增加成绩</a>
		            <a href="${pageContext.request.contextPath}/Score/doCreateBatchPre">批量增加</a>
	            </shiro:hasRole>
	           </div>
	        </li>
			<li>
	          <h4 class="M9"><span></span>课程管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Course/list">课程列表</a>
		        <a href="${pageContext.request.contextPath}/Course/downloadTemplate">下载模板</a>
	            <!-- 普通管理员不具备更新权限 -->
	            <shiro:hasRole name="SuperAdmin">
		            <a href="${pageContext.request.contextPath}/Course/doCreatePre">增加课程</a>
		            <a href="${pageContext.request.contextPath}/Course/doBatchCreatePre">批量增加</a>
		        </shiro:hasRole>
	          </div>
	        </li>	        
			<li>
	          <h4 class="M9"><span></span>教师管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Teacher/list">教师列表</a>
	            <!-- 普通管理员不具备更新权限 -->
	            <shiro:hasRole name="SuperAdmin">
	               <a href="${pageContext.request.contextPath}/Teacher/doCreatePre">增加教师</a>
	            </shiro:hasRole>
	          </div>
	        </li>	        
	        <li>
	          <h4 class="M3"><span></span>教学管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Teach/list">教学列表</a>
	            <!-- 普通管理员不具备更新权限 -->
	            <shiro:hasRole name="SuperAdmin">
	                <a href="${pageContext.request.contextPath}/Teach/doCreatePre">增加教学</a>
	            </shiro:hasRole>
	          </div>
	        </li>
	        <li>
	          <h4 class="M3"><span></span>班级管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Clazz/list">班级列表</a>
	            <!-- 普通管理员不具备更新权限 -->
	            <shiro:hasRole name="SuperAdmin">
	               <a href="${pageContext.request.contextPath}/Clazz/doCreatePre">增加班级</a>
	            </shiro:hasRole>
	          </div>
	        </li>
	        <li>
	          <h4 class="M3"><span></span>专业管理</h4>
	          <div class="list-item none">
	            <a href="${pageContext.request.contextPath}/Major/list">专业列表</a>
	            <!-- 普通管理员不具备更新权限 -->
	            <shiro:hasRole name="SuperAdmin">
	               <a href="${pageContext.request.contextPath}/Major/doCreatePre">增加专业</a>
	            </shiro:hasRole>
	          </div>
	        </li>
	        <!-- 使用shiro标签进行权限控制 -->
		    <shiro:hasRole name="SuperAdmin">
				<li>
		          <h4 class="M10"><span></span>系统管理</h4>
		          <div class="list-item none">
		            <a href="${pageContext.request.contextPath}/User/list">用户列表</a>
		            <a href="${pageContext.request.contextPath}/Role/list">角色列表</a>
		            <a href="${pageContext.request.contextPath}/Permission">权限列表</a>
		            <a href="${pageContext.request.contextPath}/User/doCreatePre">增加用户</a>
		            <a href="${pageContext.request.contextPath}/Role/doCreatePre">增加角色</a>
		          </div>
		        </li>
		  </shiro:hasRole>
    </ul>
  </div>