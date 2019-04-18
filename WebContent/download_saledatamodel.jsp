<%@ page language="java" import="java.net.*"
	contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>download salemodel</title>
</head>
<body>
	<div align="center">
		<%
			response.setContentType("application/x-download");// 设置为下载
			String filedownload = "/xls/salemodel.xls";// 下载的文件的相对路径 
			String filedisplay = "salemodel.xls";// 下载文件时显示的文件保存名称 
			String filenamedisplay = URLEncoder.encode(filedisplay, "GB2312");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filedisplay);
			try {
				RequestDispatcher dis = application
						.getRequestDispatcher(filedownload);
				if (dis != null) {
					dis.forward(request, response);
				}
				response.flushBuffer();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		%>
	</div>
</body>
</html>