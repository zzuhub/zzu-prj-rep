<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>学生入口</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入Bootstrap支持 -->
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/student_login.js">
    </script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- 引入验证码插件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/idcode.css" type="text/css">
    <script src="${pageContext.request.contextPath}/js/idcode.js" type="text/javascript"></script>
    <style>
        .container{
            margin-top: 30px;
        }
    </style>
</head>
<body>
      <div class="container">
         <c:if test="${not empty errMsg}">
             <div class="alert alert-danger" role="alert" id="errDiv">${errMsg}</div>
         </c:if>
         <form action="${pageContext.request.contextPath}/Student/loginCheck" method="post" id="loginForm" class="form-horizontal">
            <fieldset>
                <legend >
                    <label >
                        <span class="glyphicon glyphicon-user"></span>
                        &nbsp;学生登录
                    </label>
                </legend>
                <div class="form-group" id="studentIdDiv">
                    <label class="control-label col-md-3" for="studentId">学号</label>
                    <div class="col-md-5">
                        <input type="text" name="studentId" id="studentId" class="form-control" placeholder="请输入学号">
                    </div>
                    <span class="col-md-4" id="studentIdSpan"></span>
                </div>
                <div class="form-group" id="passwordDiv">
                    <label class="control-label col-md-3" for="password">&nbsp;密码</label>
                    <div class="col-md-5">
                        <input type="password" name="password" id="password" class="form-control"
                               placeholder="请输入密码">
                    </div>
                    <span class="col-md-4" id="passwordSpan" ></span>
                </div>
                <div class="form-group" id="idcodeDiv">
                     <label class="control-label col-md-3" for="idcode">验证码</label>
                     <span id="idcode" class="col-md-5"></span>
                     <span id="idcodeSpan" class="col-md-4"></span>
                </div>
                <div class="form-group" id="btnDiv">
                    <div class="col-md-5 col-md-offset-3">
                        <button type="submit" id="subBtn" class="btn btn-sm btn-primary">登录</button>
                        <button type="reset" id="rstBtn" class="btn btn-sm btn-warning">重置</button>
                    </div>
                </div>
            </fieldset>
         </form>
    </div>
</body>
</html>