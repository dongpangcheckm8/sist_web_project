package com.sist;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * Connection return Interface
 * @author sist1
 *
 */
public interface ConnectiomMaker {
	/**
	 * 커넥션 Maker
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection makeConnection() throws 
	   ClassNotFoundException, SQLException; 

}
