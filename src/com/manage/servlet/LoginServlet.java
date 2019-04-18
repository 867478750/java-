package com.manage.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.bean.SiteUser;
import com.manage.db.OracleDB;
import com.manage.db.RegisterService;
import com.manage.dom.JdomRead;
import com.manage.util.CodeMd5;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean loginyes = false;
		String t_name = (String) req.getParameter("name");
		String t_passwd = (String) req.getParameter("passwd");

		if (t_name == null || t_name.equals("") || t_passwd == null
				|| t_passwd.equals(""))
			resp.sendRedirect("login.jsp");
		else {
			JdomRead xmlr = new JdomRead(this
					.getServletContext().getRealPath("/WEB-INF/config/")
					+ "/manage_config.xml");
			try {
				xmlr.jdomReader();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Êý¾Ý¿âÅäÖÃ
			OracleDB.DB_NAME = xmlr.getDb_name();
			OracleDB.DB_HOST = xmlr.getDb_host() + ":" + xmlr.getDb_port();
			OracleDB.DB_USERNAME = xmlr.getDb_username();
			OracleDB.DB_PASSWORD = xmlr.getDb_passwd();

			SiteUser user = new SiteUser();
			user.setUserName(t_name);
			user.setUserPwd(t_passwd);
			
			
			CodeMd5 md5 = new CodeMd5();
			try {
				String md5Pwd = md5.EncoderByMd5(t_passwd);
				user.setUserPwd(md5Pwd);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			
			// ÐÂµÇÂ½Âß¼­
			RegisterService register = new RegisterService();
			int userCount = register.login(user);
			if (userCount == 1) {
				HttpSession session = req.getSession();
				session.setAttribute("name", t_name);
				loginyes = true;
			}
			/*
			 * ÀÏµÇÂ½Âß¼­ List<User> ulist = xmlr.getList(); HttpSession session =
			 * req.getSession(); for (User u : ulist) { if
			 * (u.getName().equals(t_name)) if (u.getPasswd() ==
			 * t_passwd.hashCode()) { session.setAttribute("name", t_name);
			 * loginyes = true; } }
			 */

			if (loginyes) {
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("index.jsp");
				dispatcher.forward(req, resp);
			} else {
				resp.sendRedirect("login.jsp");
			}
		}
	}
}
