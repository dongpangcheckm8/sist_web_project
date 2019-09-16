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

public class PurchaseDAO {
	private PreparedStatement ps;
	private Connection conn=null;
	private SuperCon connMaker=null;
	private static PurchaseDAO dao;
	private static ChargeDAO Cdao;
	
	public PurchaseDAO(SuperCon spCon) {
		connMaker=spCon;
	}
	
	
	// 연결 해제
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
	
	PurchaseVO vo=new PurchaseVO();

	public List<PurchaseVO> getPurchaseAllData(String matchDate, String homeTeam, String awayTeam, String id){
		
		List<PurchaseVO> list=new ArrayList<PurchaseVO>();
		
		try {
			conn=connMaker.getConnection();
			/*String sql="SELECT matchDate, homeTeam, awayTeam, winDivRate, drawDivRate, loseDivRate, matchResult "
					+ "FROM toto_matchInfo "
					+ "WHERE matchDate=? "
					+ "AND homeTeam=?";*/
			String sql="SELECT matchDate,(SELECT teamName FROM TOTO_TEAMINFO WHERE NEWteamName=HOMETEAM),(SELECT teamName FROM TOTO_TEAMINFO WHERE NEWteamName=AWAYTEAM), winDivRate, drawDivRate, loseDivRate, matchResult" + 
					"	FROM toto_matchInfo,TOTO_TEAMINFO " + 
					"WHERE HOMETEAM=NEWTEAMNAME " + 
					"AND HOMETEAM=? " + 
					"AND AWAYTEAM=? " + 
					"AND matchDate=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, homeTeam);
			ps.setString(2, awayTeam);
			ps.setString(3, matchDate);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setMatchDate(rs.getString(1));
			vo.setHomeTeam(rs.getString(2));
			vo.setAwayTeam(rs.getString(3));
			vo.setWinDivRate(rs.getString(4));
			vo.setDrawDivRate(rs.getString(5));
			vo.setLoseDivRate(rs.getString(6));
			vo.setMatchResult(rs.getString(7));
			rs.close();
			
			sql="SELECT teamLogo, teamRank "
					+ "FROM toto_teamInfo "
					+ "WHERE TeamName=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getHomeTeam());
			rs=ps.executeQuery();
			rs.next();
			vo.setTeamLogo(rs.getString(1));
			vo.setTeamRank(rs.getInt(2));
			rs.close();
			
			sql="SELECT teamLogo, teamRank "
					+ "FROM toto_teamInfo "
					+ "WHERE TeamName=? ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getAwayTeam());
			rs=ps.executeQuery();
			rs.next();
			vo.setTeam2Logo(rs.getString(1));
			vo.setTeam2Rank(rs.getInt(2));
			rs.close();
			
			sql="SELECT gamemoney "
					+ "FROM toto_member "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			rs.next();
			vo.setGamemoney(rs.getString(1));
			vo.setId(id);
			rs.close();
			
			//System.out.println(vo.getId()+" "+vo.getGamemoney());
			
			
			
			//System.out.println(vo.getMatchDate()+" "+vo.getHomeTeam()+" "+vo.getAwayTeam()+" "+vo.getWinDivRate()+" "+
			//vo.getDrawDivRate()+" "+vo.getLoseDivRate()+" "+vo.getMatchResult());
			//System.out.println(vo.getTeamLogo()+" "+vo.getTeam2Logo());
			//System.out.println(vo.getTeamRank()+" "+vo.getTeam2Rank());
			list.add(vo);
			//insertPurchaseData(vo);
			
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			disConnection();
		}
		
		return list;
	}
	
	
	public void insertPurchaseData(PurchaseVO vo) {
		
		try {
			conn=connMaker.getConnection();
			String sql="INSERT INTO toto_purchase (teamLogo,team2Logo,homeTeam,awayTeam,teamRank,team2Rank,matchDate,winDivRate,drawDivRate,loseDivRate,expectResult,matchResult,id,gamemoney,divRate,bettingmoney) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, vo.getTeamLogo());
			ps.setString(2, vo.getTeam2Logo());
			ps.setString(3, vo.getHomeTeam());
			ps.setString(4, vo.getAwayTeam());
			ps.setInt(5, vo.getTeamRank());
			ps.setInt(6, vo.getTeam2Rank());
			ps.setString(7, vo.getMatchDate());
			ps.setString(8, vo.getWinDivRate());
			ps.setString(9, vo.getDrawDivRate());
			ps.setString(10, vo.getLoseDivRate());
			ps.setString(11, vo.getExpectResult());
			ps.setString(12, vo.getMatchResult());
			ps.setString(13, vo.getId());
			ps.setString(14, vo.getGamemoney());
			ps.setString(15, vo.getDivRate());
			ps.setString(16, vo.getBettingmoney());
			ps.executeUpdate();
			//System.out.println("데이터 추가 완료");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			disConnection();
		}
		
	}
	
	public static boolean isNan(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public List<PurchaseVO> getMyGameAllData(String id){
		
		List<PurchaseVO> list=new ArrayList<>();
		
		try {
			
			conn=connMaker.getConnection();
			String sql="SELECT id,regdate,matchDate,homeTeam,awayTeam,expectResult,divRate,bettingmoney,matchResult "
					+ "FROM toto_purchase "
					+ "WHERE id=? "
					+ "ORDER BY matchDate DESC";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				PurchaseVO vo=new PurchaseVO();
				vo.setId(rs.getString(1));
				vo.setRegdate(rs.getDate(2));
				vo.setMatchDate(rs.getString(3));
				vo.setHomeTeam(rs.getString(4));
				vo.setAwayTeam(rs.getString(5));
				vo.setExpectResult(rs.getString(6));
				vo.setDivRate(rs.getString(7));
				vo.setBettingmoney(rs.getString(8));
				vo.setMatchResult(rs.getString(9));
				
				list.add(vo);
				
				//System.out.println(vo.getId()+" "+vo.getExpectResult());
			}
			
			rs.close();
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return list;
		
	}
	
	//멤버 테이블 업데이트
	   public List<PurchaseVO> purchaseUpdate(String gamemoney,String id) {
		   List<PurchaseVO> list=new ArrayList<>();
			
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
	
	public static void main(String[] args) {
		PurchaseDAO dao=new PurchaseDAO(new DBConOracle());
		
		//dao.getMyGameAllData("admin");
		dao.finalround("ldg1021");
		
		
	}
	   public void finalround(String id){
			
			try {
				conn=connMaker.getConnection();
				String sql="SELECT expectresult, matchresult, divrate, bettingmoney FROM toto_purchase WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					PurchaseVO vo=new PurchaseVO();
					vo.setExpectResult(rs.getString(1));
					vo.setMatchResult(rs.getString(2));
					vo.setDivRate(rs.getString(3));
					vo.setBettingmoney(rs.getString(4));
					System.out.println(rs.getString(1) + " " + rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"\n");
					
					if(rs.getString(2).equals("경기전"))
					{
						System.out.println("여기까지");
						continue;
					}else if(rs.getString(1).equals(rs.getString(2))) {
						
						int yourmoney=(int)(Double.parseDouble(rs.getString(3))*Integer.parseInt(rs.getString(4)));
						ChargeDAO Cdao=new ChargeDAO(new DBConOracle());
						Cdao.PlusMoney(yourmoney, id);
					}
					
				}
				rs.close();
			}catch (Exception e) {
				System.out.println("PostData : " + e.getMessage());
			}finally {
				disConnection();
			}
		}
		
		public List<PurchaseVO> purchaseAllData() {
			List<PurchaseVO> list=new ArrayList<>();
			String r="경기전";
			try {
				conn=connMaker.getConnection();
				String sql="SELECT matchdate, hometeam FROM toto_purchase "
						+ "WHERE matchresult=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, r);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) {
					PurchaseVO vo=new PurchaseVO();
					vo.setMatchDate(rs.getString(1));
					vo.setHomeTeam(rs.getString(2));
					list.add(vo);
					System.out.println("mat="+rs.getString(1)+"hom="+rs.getString(2));
				}
				rs.close();
			}catch (Exception e) {
				System.out.println("purchaseAllData : " + e.getMessage());
			}finally {
				disConnection();
			}
			
			
			return list;
		}
		
		/*public List<PurchaseVO> GetAllMoney() {
			List<PurchaseVO>list=new ArrayList<>();
			
			try {
				conn=connMaker.getConnection();
				String sql="SELECT matchdate, hometeam FROM toto_purchase";
			}catch (Exception e) {
				System.out.println("GetAllMoney : "+e.getMessage());
			}finally {
				disConnection();
			}
			
			return list;
		}*/
		
		
		//결과 업데이트
		public List<PurchaseVO> purchaseResultUpdate(String matchresult,String date, String hometeam) {
			   List<PurchaseVO> list=new ArrayList<>();
				
				try {
					conn= connMaker.getConnection();
					String sql="UPDATE toto_purchase SET "
							+ "matchresult=? "
							+ "WHERE matchdate=? AND hometeam=?";
					ps=conn.prepareStatement(sql);
					ChargeVO vo=new ChargeVO();
					ps.setString(1, matchresult);
					ps.setString(2, date);
					ps.setString(3, hometeam);
					ps.executeUpdate();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
				
				return list;
			}
	
}
