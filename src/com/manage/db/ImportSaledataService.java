package com.manage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.manage.bean.ResultData;
import com.manage.bean.Salemodel;

public class ImportSaledataService {
	public ResultData addlist(List<Salemodel> list) {
		Connection conn = OracleDB.createConn();
		UUID Id = java.util.UUID.randomUUID();
		ResultData rd = new ResultData();
		int count = 0;
		String sql = "insert into saledata"
				+ " (id,city,product,num,salesman,remark)"
				+ "  values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		for (Salemodel cid : list) {
			ps = OracleDB.prepare(conn, sql);
			try {
				ps.setString(1, Id.toString());
				ps.setString(2, cid.getCity());
				ps.setString(3, cid.getProduct());
				ps.setString(4, cid.getNum());
				ps.setString(5, cid.getSalesman());
				ps.setString(6, cid.getRemark());
				ps.executeUpdate();
				rd.setNum1(count++);
			} catch (SQLException e) {
				rd.setNum2(count++);
				e.printStackTrace();
			}
		}
		OracleDB.close(ps);
		OracleDB.close(conn);
		rd.setNum1(count);
		return rd;
	}

	public List<Object> list() {
		Connection conn = OracleDB.createConn();
		String sql = "select city,product,num,salesman,remark from saledata";
		List<Object> Objects = new ArrayList<Object>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs == null)
				return null;
			Salemodel c = null;
			while (rs.next()) {
				c = new Salemodel();
				c.setCity(rs.getString("city"));
				c.setProduct(rs.getString("product"));
				c.setNum(rs.getString("num"));
				c.setSalesman(rs.getString("salesman"));
				c.setRemark(rs.getString("remark"));
				Objects.add((Object) c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		OracleDB.close(conn);
		return Objects;
	}
}
