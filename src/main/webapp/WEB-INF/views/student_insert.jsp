<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加学生</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
<!--  Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- 因为要使用日期插件,所以直接放入laydate文件夹,因为不熟悉其结构,所以不变动结构 -->
<script src="${pageContext.request.contextPath}/laydate/laydate.js" type="text/javascript">
</script>
<!-- JS验证表单-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/student_validate.js">
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="content">
	        <jsp:include page="left.jsp"></jsp:include>
			<div class="m-right">
				<div class="main container">
				   <form method="post" 
				         action="${pageContext.request.contextPath}/Student/doCreate"
				         enctype="multipart/form-data">
					   <table class="table table-bordered" width="50%">
					      <thead>
					           <tr class="success">
					           <th colspan="3">学生添加</th>
					         </tr>
					      </thead>
					      <tbody>
					          <tr class="info">
					            <td>学号</td>
					            <td>
					              <div class="col-xs-4">
						              <input type="text" id="studentId" name="studentId" 
						               placeholder="请输入学号" class="form-control"/>
					              </div>
					            </td>
					            <td>
					             <span id="studentIdSpan">
					                 <font color="red">*必填</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="danger">
					            <td>姓名</td>
					            <td>
					              <div class="col-xs-4">
						              <input type="text" id="studentName" name="studentName" 
						               placeholder="请输入姓名" class="form-control"/>
					              </div>
					            </td>
					            <td>
					             <span id="studentNameSpan">
					                 <font color="red">*必填</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="success">
					            <td>性别</td>
					            <td>
					              <div class="radio col-xs-4">
						              <label class="radio-inline">
									    <input type="radio" name="sex" id="sex" value="男" checked="checked">男
									 </label> 
						              <label class="radio-inline">
									    <input type="radio" name="sex" id="sex" value="女">女
									</label> 
					              </div>
					            </td>
					            <td>
					             <span id="sexSpan">
					                 <font color="green">*选填</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="info">
					            <td>出生日期</td>
					            <td>
					              <div class="col-xs-4">
									  <input onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" 
									         class="laydate-icon"
									         name="birth" id="birth" />
								  </div>
					            </td>
					            <td>
					             <span id="birthSpan">
					                 <font color="red">*必填</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="warning">
					            <td>上传照片</td>
					            <td>
					              <div class="col-xs-4">
					                 <input type="file" name="photoFile" 
					                        id="photoFile" class="form-control"/>
								  </div>
					            </td>
					            <td>
					             <span id="birthSpan">
					                 <font color="green">*选填</font>
					             </span>
					            </td>
					          </tr>
					          
					          <tr class="danger">
					            <td>密码</td>
					            <td>
					               <div class="col-xs-4">
									   <input type="password" id="password" name="password" 
							               placeholder="请输入密码" class="form-control"/>
					               </div>
					            </td>
					            <td>
					             <span id="passwordSpan">
					                 <font color="red">*必填</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="success">
					            <td>联系电话</td>
					            <td>
					              <div class="col-xs-4">
								    <input type="tel" id="tel" name="tel" 
						                  placeholder="请输入联系电话" class="form-control"/>
						          </div>
					            </td>
					            <td>
					             <span id="telSpan">
					                 <font color="red">*必填</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="warning">
					            <td>专业</td>
					            <td>
								  <div class="col-xs-4">
			                           <select class="form-control input-sm col-sm-5" id="major" name="major">
			                               <option value="0">=====请选择专业======</option>
			                               <c:forEach items="${majors}" var="major">
			                                    <option value="${major.majorId}">${major.majorName}</option>
			                               </c:forEach>
			                           </select>
		                           </div>
					            </td>
					            <td>
					             <span id="majorSpan">
					                 <font color="red">*必选</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="info">
					            <td>班级</td>
					            <td>
								  <div class="col-xs-3">
			                           <select class="form-control input-sm col-sm-5" id="clazzId" name="clazzId">
			                               <option value="0">=====请选择班级======</option>
			                           </select>
		                           </div>
					            </td>
					            <td>
					             <span id="clazzIdSpan">
					                 <font color="red">*必选</font>
					             </span>
					            </td>
					          </tr>
					          <tr class="warning">
					            <td>地址</td>
					            <td>
					              <div class="col-xs-6">
					                <textarea class="form-control" rows="3" id="address" name="address"></textarea>
					              </div>
					            </td>
					            <td>
					             <span id="addressSpan">
					                 <font color="red">*必填</font>
					             </span>
					            </td>
					          </tr>
					          <tr>
					          <tr class="warning">
					            <td>简介</td>
					            <td>
					              <div class="col-xs-6">
					                <textarea class="form-control" rows="3" id="note" name="note"></textarea>
					              </div>
					            </td>
					            <td>
					             <span id="noteSpan">
					                 <font color="red">*必填</font>
					             </span>
					            </td>
					          </tr>
					          <tr>
					           <td colspan="3">
					             <div class="form-group" id="btnDiv">
		                           <div class="col-md-5 col-md-offset-2">
				                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">增加</button>
				                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
				                   </div>
		                         </div>
					           </td>
					          </tr>
					      </tbody>
					   </table>
				   </form>
				</div>
			</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>