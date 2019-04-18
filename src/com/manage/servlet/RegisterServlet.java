package com.manage.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.bean.SiteUser;
import com.manage.db.RegisterService;
import com.manage.util.CodeMd5;

public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RegisterService register = new RegisterService();
		String userName = req.getParameter("userName");
		String userPwd = req.getParameter("userPwd");
		String userEmail = req.getParameter("userEmail");
		String userMobile = req.getParameter("userMobile");
		String userAddress = req.getParameter("userAddress");

		SiteUser user = new SiteUser();
		CodeMd5 md5 = new CodeMd5();
		try {
			String md5Pwd = md5.EncoderByMd5(userPwd);
			user.setUserPwd(md5Pwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserMobile(userMobile);
		user.setUserAddress(userAddress);
		register.add(user);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("registersuccess.jsp");
		dispatcher.forward(req, resp);
	}
}
