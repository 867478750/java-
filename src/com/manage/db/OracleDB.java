package com.manage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDB {
	/** Oracle���ݿ�����URL */
	private final static String DB_URL = "jdbc:oracle:thin:@";
	public static String DB_NAME = "manage";
	public static String DB_HOST = "127.0.0.1:1521";

	/** Oracle���ݿ��������� */
	private final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** ���ݿ��û��� */
	public static String DB_USERNAME = "system";

	/** ���ݿ����� */
	public static String DB_PASSWORD = "manage";

	public static Connection createConn() {
		/** ����Connection���Ӷ��� */
		Connection conn = null;
		try {
			/** ʹ��Class.forName()�����Զ�����������������ʵ�����Զ�����DriverManager��ע���� */
			Class.forName(DB_DRIVER);
			/** ͨ��DriverManager��getConnection()������ȡ���ݿ����� */
			conn = DriverManager.getConnection(
					DB_URL + DB_HOST + ":" + DB_NAME, DB_USERNAME, DB_PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (conn != null) {
			System.out.println("���ӳɹ�");
		} else {
			System.out.println("����ʧ��");
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
				/** �жϵ�ǰ�������Ӷ������û�б��رվ͵��ùرշ��� */
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
