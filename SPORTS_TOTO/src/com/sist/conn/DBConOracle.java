package com.sist.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConOracle implements SuperCon{

	@Override
	public Connection getConnection() {
		Connection conn=null;
		String URL="jdbc:oracle:thin:@211.238.142.111:1521:ORCL";
		String USER="scott";	
		String PWD="tiger";
		
		try{
			//Driver Load
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PWD);
		}catch(ClassNotFoundException cnf){
			System.out.println("=connect_ClassNotFoundException="+cnf.getMessage());
		}catch (SQLException sqle) {
			System.out.println("=connect_SQLException="+sqle.getMessage());
		}catch(Exception e){
			System.out.println("==========================");
			e.printStackTrace();
			System.out.println("==========================");
		}
		
		return conn;
	}

}
