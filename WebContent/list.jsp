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
</head>
<body>
	<div id="fksjdr">
		<a href="<%=basePath%>ImportSaledata.jsp">销售数据导入</a>
	</div>

	<div id="fksjcx">
		<a href="<%=basePath%>saledatafind.jsp">销售数据查询</a>
	</div>
	<div id="yhmrld">
<%-- 		<a href="<%=basePath%>phone_2.jsp">离职员工查询</a>
 --%>	</div>

	<div id="fajhsl">
<%-- 		<a href="<%=basePath%>fkactivate.jsp">离职员工统计</a>
 --%>	</div>
	<script type="text/javascript">
					<%String nameValide = (String) session.getAttribute("name");

			if (nameValide != "admin") {

			}%>
			
			var nameValide = "<%=nameValide%>";

		if (nameValide != "admin") {
			document.getElementById("yhmx").style.display = "none";
			document.getElementById("yhmrld").style.display = "none";
			document.getElementById("yhdjl").style.display = "none";
			document.getElementById("jxtj").style.display = "none";
			document.getElementById("fksjdr").style.display = "none";
			document.getElementById("fajhsl").style.display = "none";
			document.getElementById("fksltj").style.display = "none";
			document.getElementById("yyssj").style.display = "none";
			document.getElementById("fkll").style.display = "none";

		}
	</script>
</body>
</html>