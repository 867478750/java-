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
			response.setContentType("application/x-download");// ����Ϊ����
			String filedownload = "/xls/salemodel.xls";// ���ص��ļ������·�� 
			String filedisplay = "salemodel.xls";// �����ļ�ʱ��ʾ���ļ��������� 
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