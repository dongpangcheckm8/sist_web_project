package com.sist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 전략패턴 인터페이스
 * @author sist1
 *
 */
public interface StatementStrategy {
	
	/**
	 * PreparedStatement 생성
	 * @param Connection c
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement makePreparedStatement(Connection c)
			  throws SQLException;
}
