package com.manage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.manage.bean.SiteUser;

public class RegisterService {
	public void add(SiteUser user) {
		Connection conn = OracleDB.createConn();
		UUID Id = java.util.UUID.randomUUID();
		String sql = "insert into SiteUser values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = OracleDB.prepare(conn, sql);
		try {
			ps.setString(1, Id.toString());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPwd());
			ps.setString(4, user.getUserEmail());
			ps.setString(5, user.getUserMobile());
			ps.setString(6, user.getUserAddress());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		OracleDB.close(ps);
		OracleDB.close(conn);
	}

	public int login(SiteUser user) {
		Connection conn = OracleDB.createConn();
		String sql = "select count(*) from siteuser t where t.username = ? and t.userpwd = ?";
		PreparedStatement ps = OracleDB.prepare(conn, sql);
		try {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			ResultSet result = ps.executeQuery();
			if (result != null) {
				while (result.next()) {
					int userCount = result.getInt(1);
					return userCount;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		OracleDB.close(ps);
		OracleDB.close(conn);
		return 0;
	}
}
