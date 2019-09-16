package com.sist.dao;

import java.util.*;

import com.sist.conn.SuperCon;
import com.sist.freeboard.BoardVO;

import java.sql.*;

public class ChargeDAO {
	
	private PreparedStatement ps;
	private Connection conn = null;
	private SuperCon connMaker = null;
	private static ChargeDAO dao;

	public ChargeDAO(SuperCon spCon){
		connMaker = spCon;
	}

   public void disConnection() {
      try {
         if (ps != null)
            ps.close();
         if (conn != null)
            conn.close();
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
   }
   
   //충전 테이블 데이터 가져오기
   public List<ChargeVO> chargeAllData(){
		List<ChargeVO> list=new ArrayList<>();
		
		try {
			conn= connMaker.getConnection();
			String sql="SELECT no,id,name,gamemoney,charge "
					+ "FROM toto_charge ";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ChargeVO vo=new ChargeVO();
				vo.setNo(rs.getString(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setGamemoney(rs.getString(4));
				vo.setCharge(rs.getString(5));
				list.add(vo);
			}
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return list;
	}
   
   //충전 테이블 삽입
   public void chargeInsert(ChargeVO vo) {
		try {
			conn= connMaker.getConnection();
			String sql = "INSERT INTO toto_charge (no,id,name,gamemoney,charge) "
					+ "VALUES ((SELECT NVL(MAX(no)+1,1) FROM toto_charge),?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getGamemoney());
			ps.setString(4, vo.getCharge());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("chargeInsert : " + e.getMessage());
		} finally {
			disConnection();
		}
	}
   
   //멤버 테이블 업데이트
   public List<ChargeVO> chargeUpdate(String gamemoney,String charge,String id) {
	   List<ChargeVO> list=new ArrayList<>();
		
		try {
			conn= connMaker.getConnection();
			String sql="UPDATE toto_member SET "
						+ "gamemoney=? "
						+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ChargeVO vo=new ChargeVO();
			ps.setString(1, gamemoney);
			ps.setString(2, id);
			ps.executeUpdate();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return list;
	}
   
   //차지 테이블 업데이트
   public List<ChargeVO> chargeRecent(String gamemoney,String id) {
	   List<ChargeVO> list=new ArrayList<>();
	
		try {
			conn= connMaker.getConnection();
			String sql="UPDATE toto_charge SET "
					+ "gamemoney=? "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ChargeVO vo=new ChargeVO();
			ps.setString(1, gamemoney);
			ps.setString(2, id);
			ps.executeUpdate();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
	
		return list;
	}
   
   //충전 테이블 삭제
   public void ChargeDelete(String id,String no) {
		try {
			conn= connMaker.getConnection();
			String sql="DELETE FROM toto_charge "
					+"WHERE id=? AND no=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, no);
			ps.executeUpdate();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
	}
   
   public void PlusMoney(int yourmoney,String id) {
		int MyGameMoney = 0;
	   try {
			conn=connMaker.getConnection();
			String sql="SELECT gamemoney FROM toto_member WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				MyGameMoney=rs.getInt(1);
			}
			rs.close();
			
			MyGameMoney = MyGameMoney+yourmoney;
			sql="UPDATE toto_member SET gamemoney=? WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, MyGameMoney);
			ps.setString(2, id);
			ps.executeQuery();
			System.out.println("ㅊㅋㅊㅋ");
		}catch (Exception e) {
			System.out.println("PlusMoney : " + e.getMessage());
		}finally {
			disConnection();
		}
	}
   
}
