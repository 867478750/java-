package com.manage.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.db.ImportSaledataService;

public class SaleDataFind_Servlet extends HttpServlet {
	private static final long serialVersionUID = -854550978502353857L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String name = (String) session.getAttribute("name");
		if (name == null || name.equals("") || name.equals("null"))
			resp.sendRedirect("login.jsp");
		else {
			String time1 = (String) req.getParameter("time1");
			String time2 = (String) req.getParameter("time2");
			String userName = (String) session.getAttribute("name");
			req.setAttribute("list", new ImportSaledataService().list());
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("saledatafind.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
