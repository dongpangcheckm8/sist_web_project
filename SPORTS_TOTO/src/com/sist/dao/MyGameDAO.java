package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sist.conn.DBConOracle;
import com.sist.conn.SuperCon;

public class MyGameDAO {

	private PreparedStatement ps;
	private Connection conn=null;
	private SuperCon connMaker=null;
	private static MyGameDAO dao;
	
	public MyGameDAO(SuperCon spCon) {
		connMaker=spCon;
	}
	
	public void disConnection() {
		try {
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null	) {
				conn.close();
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public List<MyGameVO> getMyGameAllData(String id){
		
		MyGameVO vo=new MyGameVO();
		List<MyGameVO> list=new ArrayList<MyGameVO>();
	
		try {
			conn=connMaker.getConnection();
			String sql="SELECT id,regdate,matchDate,homeTeam,awayTeam,expectResult,divRate,bettingmoney,matchResult "
					+ "FROM toto_purchase "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setRegdate(rs.getString(2));
				vo.setMatchDate(rs.getString(3));
				vo.setHomeTeam(rs.getString(4));
				vo.setAwayTeam(rs.getString(5));
				vo.setExpectResult(rs.getString(6));
				vo.setDivRate(rs.getString(7));
				vo.setBettingmoney(rs.getString(8));
				vo.setMatchResult(rs.getString(9));
				
				list.add(vo);
				insertMyGameData(vo);
				System.out.println(vo.getId()+" "+vo.getExpectResult());
			}
			rs.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return list;
	}
	
	public void insertMyGameData(MyGameVO vo) {
		
		try {
			conn=connMaker.getConnection();
			/*String sql="INSERT INTO toto_myGame "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";*/
			String sql="INSERT INTO toto_myGame "
					+ "SELECT ?,?,?,?,?,?,?,?,? "
					+ "FROM dual "
					+ "WHERE NOT EXISTS "
					+ "(SELECT * FROM toto_myGame WHERE regDate=?)";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getRegdate());
			ps.setString(3, vo.getMatchDate());
			ps.setString(4, vo.getHomeTeam());
			ps.setString(5, vo.getAwayTeam());
			ps.setString(6, vo.getExpectResult());
			ps.setString(7, vo.getDivRate());
			ps.setString(8, vo.getBettingmoney());
			ps.setString(9, vo.getMatchResult());
			ps.setString(10, vo.getRegdate());
			ps.executeUpdate();
			System.out.println("데이터 추가 완료");
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		MyGameDAO dao=new MyGameDAO(new DBConOracle());
		
		dao.getMyGameAllData("jeongdh1991");
	}
	
}
