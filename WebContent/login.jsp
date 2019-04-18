<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link href="css/layout.css" rel="stylesheet" type="text/css" />
</head>

<script language="javascript" type="text/javascript">
	/* 	function validate(){
	 var userName = document.getElementById("userName").value;
	 var userPwd = document.getElementById("userPwd").value;
	 if(!(userName == "张三" || userPwd == "123")){
	 document.getElementById("login").submit();
	 }else{
	 alert("登陆信息不正确");
	 }
	 } */

	function validate() {
		// 定义验证规则
		var userNameReg = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
		var userPwdReg = /^[a-zA-Z]\w{5,17}$/;
		var userName = document.getElementById("userName").value;
		var userPwd = document.getElementById("userPwd").value;
		// 使用test验证
		var userNameResult = userNameReg.test(userName);
		var userPwdResult = userPwdReg.test(userPwd);
		// 如果满足条件通过验证
		if (userNameResult == true && userPwdResult == true) {
			document.getElementById("login").submit();
		} else {
			alert("登陆信息不正确");
		}
	}
</script>



<body>
	<div id="container">
		<div id="header">
			<div align="center">
				<marquee>
					<a>管理系统的Servlet第一期，正在开发中</a>
				</marquee>
			</div>
		</div>
		<div id="mainContent">
			<%
				String name = (String) session.getAttribute("name");
			%>

			<div id="sidebar">
				<%
					if (name == null) {
						name = "";
					} else {
				%>
				<%=name%>已登录
				<%
					}
				%>
			</div>
			<div id="content">			
				<form id="login" method="post" action="LoginServlet" align="center">
					用户名：<input id="userName" type="text" name="name" value="" /> 口 令：<input
						id="userPwd" type="password" name="passwd" value=""
						style="width: 155px;" /><br>
					<p>
						<!-- <input type="submit" value="登陆" name="Submit" onclick="validate()" />  -->
						<input type="button" value="登录" name="Submit" onclick="validate()" />
						<input type="reset" value="重置" name="" />
						<input type="button" value="注册" onclick="window.open('register.jsp')"   />
<!-- 						<td><a href="../host/host.jsp" name="dhl" onclick="dh(0)">首页</a></td><td><img src="../logo/line.bmp"  width="6"/></td>
<input type=button onclick="window.open('连接')"> -->						
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>