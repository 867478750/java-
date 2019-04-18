<%@ page language="java" import="java.util.*" import="java.net.*"
	pageEncoding="UTF-8"%>
<%@page import="com.manage.bean.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>销售数据导入</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<script type="text/javascript">
	function flush() {
		window.location.reload();
	}
</script>

<body bgcolor="#CCCCCC">
	<div>
		<a href="<%=basePath%>/index.jsp">返回首页</a>
	</div>
	<table border="0" cellpadding="0" cellspacing="0" width="80%"
		bordercolorlight="#000080" bordercolordark="#FFFFFF" height="19">
		<tr valign="middle">
			<td width="80%" background="img/topbg.gif" height="32"></td>
		</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="80%"
		bordercolorlight="#E6E4C4" bordercolordark="#E8E4C8">
		<tr>
			<h1>销售数据导入</h1>
		</tr>
	</table>

	<div align="left">
		<a href="download_saledatamodel.jsp">下载Excel模板</a>
	</div>
	<div align="left">
		<form method="POST" action="ImportSaledata_Servlet"
			enctype="multipart/form-data">
			选择文件:<input type="file" name="filename" /> <br>
			<p>
				<input type="Submit" value="提交"> <input type="reset"
					value="重置" id="b2" onclick="flush()">
			</p>
		</form>
		<%
			List<?> list = (List<?>) request.getAttribute("ulist");
			if (list == null) {
			} else {
		%>
		<br>
		<div align='left'>
			共<%=list.size()%>条记录
		</div>
		<table id="data" border="1" cellpadding="1" width="70%">
			<tr align='center'>
				<td align='center'>城市</td>
				<td align='center'>产品</td>
				<td align='center'>数量</td>
				<td align='center'>推销员</td>
				<td align='center'>备注</td>

			</tr>
			<tr>
				<%
					for (Object o : list) {
							Salemodel t = (Salemodel) o;
				%>
				<td align='center'><%=t.getCity()%></td>
				<td align='center'><%=t.getProduct()%></td>
				<td align='center'><%=t.getNum()%></td>
				<td align='center'><%=t.getSalesman()%></td>
				<td align='center'><%=t.getRemark()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			session.setAttribute("ulist", list);
		%>
		<br>
		<div align='center'>
			共<%=list.size()%>条记录
		</div>
		<form method="post" action="ImportSaledataDB_Servlet">
			<select name="nettype">
				<option selected="selected">请选择类型:</option>
				<option value="other">其他</option>
				<option value="net">销售数据</option>
			</select> <br> <br> <input type="submit" value="导入数据库" />
		</form>
		<%
			}
		%>
	</div>
</body>
</html>
