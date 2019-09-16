package com.sist;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 위원회 Dao
 * @author sist1
 * @version : 0.2
 * @since: 2017/11/14
 *         2017/11/14: getConnection 추상메소드로 변경
 *                     S/K사에 요청 사항 : DB를 유연하게 변경 하도록 추상클래스로 수정   
 *
 */
public class GscPanDao {
    Logger log = Logger.getLogger(GscPanDao.class);
    private ConnectiomMaker connectiomMaker;
    
    public GscPanDao(){}
    
    //----------------------------------------------
    // 인터페이스를 통해 접근하므로 구체적인 클래스 정보를 알 필요가 없다.
    //----------------------------------------------    
    public GscPanDao(ConnectiomMaker connectiomMaker){
    	//connectiomMaker = new SGscPanDao();
    	this.connectiomMaker = connectiomMaker;
    }
    
	/**
	 * 전체삭제
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteAll()throws
    		ClassNotFoundException,SQLException{
		int flag = 0;
		Connection con = connectiomMaker.makeConnection();
		
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
	    
        Connection conn         = connectiomMaker.makeConnection();
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
        
        
        log.debug("======================");
        log.debug("sql:\t"+sb.toString());
        log.debug("======================");
        pstmt = conn.prepareStatement(sb.toString());
        
        pstmt.setString(1, pan.getCmId());
        pstmt.setString(2, pan.getName());
        pstmt.setString(3, pan.getPassword());
        pstmt.setString(4, pan.getUseYn());
        
        int flag = pstmt.executeUpdate();
        log.debug("======================");
        log.debug("pan:\t"+pan.toString());
        log.debug("flag:\t"+flag);
        log.debug("======================");  
        
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

        Connection conn = connectiomMaker.makeConnection();
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
        
        log.debug("======================");
        log.debug("sql:\t"+sb.toString());
        log.debug("======================");
        
        log.debug("======================");
        log.debug("pan:\t"+cmId);
        log.debug("======================");  
        
        rs.close();
        pstmt.close();
        conn.close();
        
        return pan;
	}	

	
}
