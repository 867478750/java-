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
	 if(!(userName == "����" || userPwd == "123")){
	 document.getElementById("login").submit();
	 }else{
	 alert("��½��Ϣ����ȷ");
	 }
	 } */

	function validate() {
		// ������֤����
		var userNameReg = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
		var userPwdReg = /^[a-zA-Z]\w{5,17}$/;
		var userName = document.getElementById("userName").value;
		var userPwd = document.getElementById("userPwd").value;
		// ʹ��test��֤
		var userNameResult = userNameReg.test(userName);
		var userPwdResult = userPwdReg.test(userPwd);
		// �����������ͨ����֤
		if (userNameResult == true && userPwdResult == true) {
			document.getElementById("login").submit();
		} else {
			alert("��½��Ϣ����ȷ");
		}
	}
</script>



<body>
	<div id="container">
		<div id="header">
			<div align="center">
				<marquee>
					<a>����ϵͳ��Servlet��һ�ڣ����ڿ�����</a>
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
				<%=name%>�ѵ�¼
				<%
					}
				%>
			</div>
			<div id="content">			
				<form id="login" method="post" action="LoginServlet" align="center">
					�û�����<input id="userName" type="text" name="name" value="" /> �� �<input
						id="userPwd" type="password" name="passwd" value=""
						style="width: 155px;" /><br>
					<p>
						<!-- <input type="submit" value="��½" name="Submit" onclick="validate()" />  -->
						<input type="button" value="��¼" name="Submit" onclick="validate()" />
						<input type="reset" value="����" name="" />
						<input type="button" value="ע��" onclick="window.open('register.jsp')"   />
<!-- 						<td><a href="../host/host.jsp" name="dhl" onclick="dh(0)">��ҳ</a></td><td><img src="../logo/line.bmp"  width="6"/></td>
<input type=button onclick="window.open('����')"> -->						
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>