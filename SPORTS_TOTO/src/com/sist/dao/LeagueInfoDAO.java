package com.sist.dao;

import java.sql.*;
import java.sql.Connection;

import com.sist.conn.DBConOracle;
import com.sist.conn.SuperCon;
import com.sist.dao.*;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LeagueInfoDAO {

	private PreparedStatement ps;
	private Connection conn=null;
	private SuperCon connMaker=null;
	private static LeagueInfoDAO dao;
	
	
	public LeagueInfoDAO(SuperCon spCon) {
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
	
	// 리그별 코드 추출
	public List<String> getLeagueCodeData() {
		
		List<String> list=new ArrayList<String>();
		
		try {
			Document doc=Jsoup.connect("http://www.betman.co.kr/teamRanking.so?method=inquireRanking&item=SC&league=SC001&seq=1&fromCode=left").get();
			Elements league_code=doc.select("div.dsch select option");
			for(int j=0;j<9;j++) {
				Element LC=league_code.get(j);
				String leagueCode=LC.attr("value");
				//System.out.println(leagueCode);
				list.add(leagueCode);
			}
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return list;
		
	}
	
	// 리그별 정보 추출
	public void getLeagueInfoData() {
		
		LeagueInfoVO vo=new LeagueInfoVO();
		int k=1;
		
		try {
			
			Document doc=Jsoup.connect("http://www.betman.co.kr/teamRanking.so?method=inquireRanking&item=SC&league=SC001&seq=1&fromCode=left").get();
			Elements league_name=doc.select("div.dsch select option");
			for(int j=0;j<9;j++) {
				Element LN=league_name.get(j);
				String leagueName=LN.text();
				if(leagueName.equals("K클래식")||leagueName.equals("K챌린지")||leagueName.equals("J리그"))
					continue;
				//System.out.println(leagueName);
				vo.setLeagueNo(k);
				vo.setLeagueName(leagueName);
				vo.setLeagueLogo(" ");
				insertLeagueInfoData(vo);
				System.out.println(k+" 완료");
				k++;
				
			}
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	// 리그 정보 DB에 값 추가하기
	public void insertLeagueInfoData(LeagueInfoVO vo) {
		
		
		try {
			
			conn=connMaker.getConnection();
			String sql="INSERT INTO toto_leagueInfo "
					+ "VALUES (?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getLeagueNo());
			ps.setString(2, vo.getLeagueName());
			ps.setString(3, vo.getLeagueLogo());
			ps.executeUpdate();
			System.out.println("완료");
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			disConnection();
		}
	}
	
	public static void main(String[] args) {
		LeagueInfoDAO dao=new LeagueInfoDAO(new DBConOracle());
		dao.getLeagueInfoData();
	}
	
}



















