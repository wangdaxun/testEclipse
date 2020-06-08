package com.qst.itoffer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JdbcUtil {
	private static String name = "WDX";
	private static String password = "oracle";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:helowin";
	private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
	private static Connection conn = null;
	static {
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, name, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static Connection getConnection() {
		return conn;
	}
	
	public static void Close(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			if(conn != null) {
				conn.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn = JdbcUtil.getConnection();
		if(conn == null) {
			System.out.println("连接失败");
		}else {
			System.out.println("连接成功");
		}
	}
}
