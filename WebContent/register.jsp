<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人用户注册</title>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	function validate() {
		// 定义验证规则
		var userNameReg = /^[A-Za-z]+$/;
		var userPwdReg = /^[a-zA-Z]\w{5,17}$/;
		var userEmailReg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
		var userMobileReg = /^1\d{10}$/;

		// 取值
		var userName = document.getElementById("userName").value;
		var userPwd = document.getElementById("userPwd").value;
		var userEmail = document.getElementById("userEmail").value;
		var userMobile = document.getElementById("userMobile").value;

		// 获取勾选状态
		var userAgree1 = document.getElementById("userAgree").checked;
		var userAgree2 = $("input[type='checkbox']").is(':checked');

		// 使用test验证
		// 只能是英文
		var userNameResult = userNameReg.test(userName);
		// 最少6位
		var userPwdResult = userPwdReg.test(userPwd);
		// 邮箱常规验证 比如必须有@符号
		var userEmailResult = userEmailReg.test(userEmail);
		// 手机常规验证 比如只能是11位
		var userMobileResult = userMobileReg.test(userMobile);

		// 如果满足条件通过验证
		if (userNameResult == true && userPwdResult == true
				&& userEmailResult == true && userMobileResult == true
				&& userAgree1 == true) {
			document.getElementById("userRegister").submit();
		} else {
			alert("注册信息不正确");
		}
	}

	function flush() {
		window.location.reload();
	}
</script>
<style type="text/css">
form {
	font-size: larger;
}

#t1 {
	position: absolute;
	top: 100px;
	left: 36%;
}

#b1 {
	height: 30px;
	width: 60px;
	font-size: 14px
}

#d1 {
	position: absolute;
	left: 10%;
	top: 2%;
}
</style>
</head>
<body bgcolor="#BAFEC0">
	<p align="center">
		<font size="+3">用户注册</font>
	</p>
	<form action="RegisterServlet" method="post" id="userRegister">
		<table id="t1">
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="userName" name="userName" />
				</td>
				<td><c:choose>
						<c:when test="${userName==null}">
							<span id="td1">只能是英文</span>
						</c:when>
						<c:otherwise>
							<span>${userName}</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>密&nbsp;码：</td>
				<td><input type="password" id="userPwd" name="userPwd" />
				</td>
				<td id="td2">最少六位</td>
			</tr>
			<tr>
				<td>邮&nbsp;箱：</td>
				<td><input type="text" id="userEmail" name="userEmail" />
				</td>
				<td id="td4">请输入正确的邮箱地址</td>
			</tr>
			<tr>
				<td>手机号码：</td>
				<td><input type="text" id="userMobile" name="userMobile" />
				</td>
				<td id="td5">请输入正确的手机号</td>
			</tr>
			<tr>
				<td>住&nbsp;址：</td>
				<td><input type="text" id="address" name="address" />
				</td>
				<td id="td6"></td>
			</tr>
			<tr>
				<td colspan="3">注册协议</td>
			</tr>
			<tr>
				<td colspan="3"><textarea cols="56" rows="10">
<jsp:include page="document/register.txt"></jsp:include>
</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right">同意<input type="checkbox"
					id="userAgree" name="userAgree">&nbsp;&nbsp;</td>
			</tr>
			<tr align="center">
				<td><input type="button" value="提交" id="b1"
					onclick="validate()"></td>
				<td></td>
				<td><input type="reset" value="重置" id="b2" onclick="flush()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>