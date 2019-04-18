package com.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.bean.ResultData;
import com.manage.bean.Salemodel;
import com.manage.db.ImportSaledataService;

public class ImportSaledataDB_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String name = (String) session.getAttribute("name");
		if (name == null || name.equals("") || name.equals("null"))
			resp.sendRedirect("login.jsp");
		else {
			String nettype = (String) req.getParameter("nettype");
			List<Salemodel> list = (List<Salemodel>) session
					.getAttribute("ulist");
			if (list == null || nettype == null || nettype.equals(""))
				resp.sendRedirect("ImportSaledata.jsp");
			else {
				ImportSaledataService Saledata = new ImportSaledataService();
				for (Salemodel cid : list) {
				}
				ResultData rd = Saledata.addlist((List<Salemodel>) list);
				int count = rd.getNum1();
				int c = list.size();
				session.removeAttribute("ulist");
				resp.setContentType("text/html;charset=GBK");
				PrintWriter writer = resp.getWriter();
				writer.println("<html>");
				writer.println("<head><title>Excel 导入结果</title></head>");
				writer.println("<body>" + "导入了" + count + "条记录<br>");
				writer.println("<a href=\"index.jsp\">首页</a><br>");
				writer.println("<a href=\"ImportSaledata.jsp\">销售数据导入</a><br>");
				writer.println("失败了" + (c - count) + "条<br>");
				writer.println("<p>是否全部完成:" + count == c + "</p></body>");
				writer.println("</html>");
				writer.close();
			}
		}
	}

}
