package com.sist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SGscPanDao implements ConnectiomMaker {

	public Connection makeConnection() 
			throws ClassNotFoundException, SQLException {
		String DB_URL = "jdbc:oracle:thin:@211.238.142.98:1521:orcl";
		String DB_USER = "hy";
		String DB_PASSWORD = "0109";
		// 드라이버를 로딩한다.
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    // 데이터베이스의 연결을 설정한다.
	    Connection conn=
	      DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);	
		
		return conn;
	}

}
