package com.manage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDB {
	/** Oracle数据库连接URL */
	private final static String DB_URL = "jdbc:oracle:thin:@";
	public static String DB_NAME = "manage";
	public static String DB_HOST = "127.0.0.1:1521";

	/** Oracle数据库连接驱动 */
	private final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 数据库用户名 */
	public static String DB_USERNAME = "system";

	/** 数据库密码 */
	public static String DB_PASSWORD = "manage";

	public static Connection createConn() {
		/** 声明Connection连接对象 */
		Connection conn = null;
		try {
			/** 使用Class.forName()方法自动创建这个驱动程序的实例且自动调用DriverManager来注册它 */
			Class.forName(DB_DRIVER);
			/** 通过DriverManager的getConnection()方法获取数据库连接 */
			conn = DriverManager.getConnection(
					DB_URL + DB_HOST + ":" + DB_NAME, DB_USERNAME, DB_PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (conn != null) {
			System.out.println("连接成功");
		} else {
			System.out.println("连接失败");
		}
		return conn;

	}

	public static PreparedStatement prepare(Connection conn, String sql) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public static void close(Connection conn) {

		try {
			if (conn != null) {
				/** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */
				if (!conn.isClosed()) {
					conn.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void close(Statement stmt) {
		try {
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = createConn();
		try {
			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getDB_NAME() {
		return DB_NAME;
	}

	public static void setDB_NAME(String dB_NAME) {
		DB_NAME = dB_NAME;
	}

	public static String getDB_USERNAME() {
		return DB_USERNAME;
	}

	public static void setDB_USERNAME(String dB_USERNAME) {
		DB_USERNAME = dB_USERNAME;
	}

	public static String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public static void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
	}

	public static String getDB_HOST() {
		return DB_HOST;
	}

	public static void setDB_HOST(String dB_HOST) {
		DB_HOST = dB_HOST;
	}
}
