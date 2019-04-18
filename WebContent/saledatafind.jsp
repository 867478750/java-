<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.manage.bean.*"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>销售数据查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js">
	
</script>
<style type="text/css">
td {
	background-color: #FFFFFF;
}

.txt {
	padding-top: 1px;
	padding-right: 1px;
	padding-left: 1px;
	mso-ignore: padding;
	color: black;
	font-size: 11.0pt;
	font-weight: 400;
	font-style .: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-number-format: "\@";
	text-align: general;
	vertical-align: middle;
	mso-background-source: auto;
	mso-pattern: auto;
	white-space: nowrap;
}
</style>
<SCRIPT LANGUAGE="JavaScript">
	function ExportExcel() {
		try {
			var oXL = new ActiveXObject("Excel.Application");
		}//创建excel应用程序对象
		catch (e) {
			alert("无法启动Excel!\n\n如果您确信您的电脑中已经安装了Excel，"
					+ "那么请调整IE的安全级别。\n\n具体操作：\n\n"
					+ "工具 → Internet选项 → 安全 → 自定义级别 → 对没有标记为安全的ActiveX进行初始化和脚本运行 → 启用");
			return false;
			return "";
		}
		oXL.visible = true;

		var oWB = oXL.Workbooks.Add(); //创建工作簿
		var oSheet = oWB.ActiveSheet; //获取当前活动的工作簿
		var table = document.all.data; //获取当前页面中的表格 
		var hang = table.rows.length; //获取表格有多少行
		var lie = table.rows(0).cells.length; //获取首行有多少列-多少标题
		for (i = 0; i < hang; i++) //添加标题到表格中
		{
			for (j = 0; j < lie; j++) {
				oSheet.Cells(i + 1, j + 1).Value = table.rows(i).cells(j).innerText;//设置标题的内容
			}
		}
		oXL.Visible = true; //设置Excel的属性
		oXL.UserControl = true;
	}
</SCRIPT>
</head>
<body bgcolor="#CCCCCC">
	<div>
		<a href="<%=basePath%>/index.jsp">返回首页</a>
	</div>
	<table border="0" cellpadding="0" cellspacing="0" width="80%"
		bordercolorlight="#000080" bordercolordark="#FFFFFF" height="19">
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="80%"
		bordercolorlight="#E6E4C4" bordercolordark="#E8E4C8">
		<tr valign="top">
			<td width="100%" bgcolor="#e6e4c4" class="main1"></td>
		</tr>
		<tr>
			<h1>销售数据查询</h1>
		</tr>
	</table>
	<div align="left">
		<form method="POST" action="SaleDataFind_Servlet">
			<p>
				<br> 请输入日期范围
				<%
					String time1 = (String) request.getAttribute("time1");
					String time2 = (String) request.getAttribute("time2");
					if (time1 == null)
						time1 = "";
					if (time2 == null)
						time2 = "";
				%>

				&nbsp; &nbsp; &nbsp;
			</p>
			<input class="Wdate" type="text" name="time1"
				onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd'})"
				value="<%=time1%>"> --<input class="Wdate" type="text"
				name="time2"
				onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd'})"
				value="<%=time2%>"> <br> <br> <input type="submit"
				value="查询" name="Submit" style="font-size: 14px"><input
				type="button" name="btnExcel" onclick="javascript:ExportExcel();"
				value="导出到excel" class="notPrint"><br>
			<%
				List list = (List) request.getAttribute("list");
				if (list == null) {
				}
			%>
			<%
				if (list != null) {
			%>
			<table id="data" border="1" cellpadding="1" width="100%">
				<tr align='center'>
					<td align='center'>地市</td>
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
				}
			%>
		</form>
	</div>
</body>
</html>
