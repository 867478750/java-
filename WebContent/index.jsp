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

<body>

	<div id="container">
		<div id="header">
			<div align="left">
					<p>������</p>
			</div>
		</div>
		<div id="mainContent">
			<%
				String name = (String) session.getAttribute("name");
				System.out.println("name==="+name);
			%>
			<div id="sidebar">
				<div>
					<a href="<%=basePath%>login.jsp">�˳�</a>
				</div>
				<%
					if (name == null) {
						name = "";
					} else {
				%>
				�û���
				<%=name%>�ѵ�¼
				<%
					}
				%>
				
				
				
		<%

		 %>		

		
				<script type="text/javascript" charset="utf-8">

				</script>
			</div>
			<div id="content">
				<jsp:include page="/list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>