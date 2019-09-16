package com.sist;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 위원회 Dao
 * @author sist1
 * @version : 0.1
 * @since: 2017/11/14
 *
 */
public class GscPanDao {

	/**
	 * 전체삭제
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteAll()throws
    		ClassNotFoundException,SQLException{
		int flag = 0;
		Connection con = getConnection();
		
		//sql 문
		StringBuilder sb=new StringBuilder();
		sb.append("delete from gsc_pan ");
		
		PreparedStatement ps = con.prepareStatement(sb.toString());
		
		flag = ps.executeUpdate();
		
		ps.close();
		con.close();
		
		return flag;
	}
	
	/**
	 * 위원회 멤버 추가
	 * @param pan
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add(GscPanVO pan) throws
    ClassNotFoundException,SQLException{
	    
        Connection conn         = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs            = null;

        StringBuilder sb=new StringBuilder();
        sb.append("INSERT INTO GSC_PAN \n");
        sb.append("  (                 \n");
        sb.append("    CM_ID,          \n");
        sb.append("    NAME,           \n");
        sb.append("    PASSWORD,       \n");
        sb.append("    USE_YN          \n");
        sb.append("  )                 \n");
        sb.append("  VALUES            \n");
        sb.append("  (                 \n");
        sb.append("    ?,              \n");
        sb.append("    ?,              \n");
        sb.append("    ?,              \n");
        sb.append("    ?               \n");
        sb.append("  )                 \n");
        
        
        System.out.println("======================");
        System.out.println("sql:\t"+sb.toString());
        System.out.println("======================");
        pstmt = conn.prepareStatement(sb.toString());
        
        pstmt.setString(1, pan.getCmId());
        pstmt.setString(2, pan.getName());
        pstmt.setString(3, pan.getPassword());
        pstmt.setString(4, pan.getUseYn());
        
        int flag = pstmt.executeUpdate();
        System.out.println("======================");
        System.out.println("pan:\t"+pan.toString());
        System.out.println("flag:\t"+flag);
        System.out.println("======================");  
        
        pstmt.close();
        conn.close();
	}
	
    /**
     * 위원회 멤버 단건 조회
     * @param cmId
     * @return GscPanVO 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
     
	public GscPanVO get(String cmId) throws
	ClassNotFoundException,SQLException{

        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        StringBuilder sb=new StringBuilder();
        sb.append("SELECT CM_ID,        \n");
        sb.append("       NAME,         \n");
        sb.append("       PASSWORD,     \n");
        sb.append("       USE_YN        \n");
        sb.append("  FROM GSC_PAN       \n");
        sb.append(" WHERE CM_ID = ?     \n");
        
        
        pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1, cmId);
        rs = pstmt.executeQuery();
        rs.next();
        
        GscPanVO pan=new GscPanVO();
        pan.setCmId(rs.getString("CM_ID"));
        pan.setName(rs.getString("NAME"));
        pan.setPassword(rs.getString("PASSWORD"));
        pan.setUseYn(rs.getString("USE_YN"));
        
        System.out.println("======================");
        System.out.println("sql:\t"+sb.toString());
        System.out.println("======================");
        
        System.out.println("======================");
        System.out.println("pan:\t"+cmId);
        System.out.println("======================");  
        
        rs.close();
        pstmt.close();
        conn.close();
        
        return pan;
	}	
	
	//connection 
	private Connection getConnection()throws ClassNotFoundException,
	SQLException{
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
