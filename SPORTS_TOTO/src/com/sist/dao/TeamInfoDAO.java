package com.sist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.conn.DBConOracle;
import com.sist.conn.SuperCon;

public class TeamInfoDAO {

	private PreparedStatement ps;
	private Connection conn=null;
	private SuperCon connMaker=null;
	private static TeamInfoDAO dao;
	
	public TeamInfoDAO(SuperCon spCon) {
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
	
	// 리그별 코드를 추출하기
	public List<String> getLeagueCodeData() {
			
		List<String> list=new ArrayList<String>();
		
		try {
			Document doc=Jsoup.connect("http://www.betman.co.kr/teamRanking.so?method=inquireRanking&item=SC&league=SC001&seq=1&fromCode=left").get();
			Elements league_code=doc.select("div.dsch select option");
			for(int j=2;j<9;j++) {
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
	
	public void getTeamInfoData() {
		
		try {
			
			List<String> list=getLeagueCodeData();
			int i=0;
			
			for(int k=0;k<list.size();k++) {
				
				try {
					
					String url="http://www.betman.co.kr/teamRanking.so?season=2017&btSportsDataAuth=0&id=&method=inquireRanking&seq=1&league="+list.get(k)+"&item=SC";
					
					Document doc=Jsoup.connect(url).get();
					
					Element league_name=doc.select("h2.h2txtBig").first();
					String leagueName=league_name.text().substring(0,league_name.text().indexOf(" "));
					
					Elements team_tag=doc.select("td.team a");
					for(int m=0;m<team_tag.size();m++) {
						
						List<String> teamCodeList=new ArrayList<String>();
						
						Element a=team_tag.get(m);
						String teamCode=a.attr("href");
						teamCode=teamCode.substring(teamCode.indexOf("'")+1,teamCode.lastIndexOf("'"));
						teamCodeList.add(teamCode);
						
						for(int j=0;j<teamCodeList.size();j++) {
							String team_url="http://www.betman.co.kr/sportsTeamIntro.so?method=inquireTeamIntro&item=SC&league="+list.get(k)+"&id=1&seq=&season=2017&isToto=&teamId="+teamCode;
							//System.out.println(team_url);
							Document teamDoc=Jsoup.connect(team_url).get();
							
							Element team_Logo=teamDoc.select("div.teamTit dl dt img").first();
							String teamLogo="http://www.betman.co.kr/"+team_Logo.attr("src");
							//System.out.println(teamLogo);
							
							Element team_name=teamDoc.select("div.teamTit dl dd span.teamsbj").first();
							String teamName=team_name.text();
							//System.out.println(teamName);
							
							Element team_rank=teamDoc.select("div.teamTit dl dd.rank").first();
							int teamRank=Integer.parseInt(team_rank.text().substring(team_rank.text().indexOf(" ")+1,team_rank.text().lastIndexOf("위")));
							//System.out.println(teamRank);
							
							Element number_of_matches=teamDoc.select("tbody tr td").get(11);
							int numberOfMatches=Integer.parseInt(number_of_matches.text());
							//System.out.println(numberOfMatches);
							
							Element team_wins=teamDoc.select("tbody tr td").get(12);
							int wins=Integer.parseInt(team_wins.text());
							
							Element team_draws=teamDoc.select("tbody tr td").get(13);
							int draws=Integer.parseInt(team_draws.text());
							
							Element team_loses=teamDoc.select("tbody tr td").get(14);
							int loses=Integer.parseInt(team_loses.text());
							
							int points=(wins*3)+(draws*1);
							
							Element total_goals_get=teamDoc.select("tbody tr td").get(17);
							int totalGoalsGet=Integer.parseInt(total_goals_get.text());
							
							Element avg_goals_get=teamDoc.select("tbody tr td").get(18);
							double avgGoalsGet=Double.parseDouble(avg_goals_get.text());
							
							Element total_goals_lost=teamDoc.select("tbody tr td").get(19);
							int totalGoalsLost=Integer.parseInt(total_goals_lost.text());
							
							Element avg_goals_lost=teamDoc.select("tbody tr td").get(20);
							double avgGoalsLost=Double.parseDouble(avg_goals_lost.text());
							
							int goalsMargin=totalGoalsGet-totalGoalsLost;
							
							TeamInfoVO vo=new TeamInfoVO();
							
							vo.setLeagueName(leagueName);
							vo.setTeamLogo(teamLogo);
							vo.setTeamRank(teamRank);
							vo.setTeamName(teamName);
							vo.setNumberOfMatches(numberOfMatches);
							vo.setPoints(points);
							vo.setWins(wins);
							vo.setDraws(draws);
							vo.setLoses(loses);
							vo.setAvgGoalsGet(avgGoalsGet);
							vo.setTotalGoalsGet(totalGoalsGet);
							vo.setAvgGoalsLost(avgGoalsLost);
							vo.setTotalGoalsLost(totalGoalsLost);
							vo.setGoalsMargin(goalsMargin);
							
							insertTeamInfoData(vo);
							System.out.println(vo.getLeagueName()+" "+vo.getTeamLogo()+" "+vo.getTeamRank()+" "+vo.getTeamName()+" "+" "+vo.getNumberOfMatches()+" "+vo.getPoints()+" "+vo.getWins()+" "+vo.getDraws()+" "+vo.getLoses()+" "+vo.getAvgGoalsGet()+" "+vo.getTotalGoalsGet()+" "+vo.getAvgGoalsLost()+" "+vo.getTotalGoalsLost()+" "+vo.getGoalsMargin());
							System.out.println(i+1+"번 완료");
							i++;
							
							//System.out.println(wins+" "+draws+" "+loses+" "+points+" "+totalGoalsGet+" "+avgGoalsGet+" "+totalGoalsLost+" "+avgGoalsLost);
							
							//System.out.println(teamLogo+" "+teamName+" "+teamRank+" "+numberOfMatches);
						}
					}
					
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
				
			}
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	// 팀 정보 DB에 값 추가하기
	public void insertTeamInfoData(TeamInfoVO vo) {
		try {
			conn=connMaker.getConnection();
			/*String sql="INSERT INTO toto_teamInfo "
					+ "SELECT ?,?,?,?,?,?,?,?,?,?,?,?,?,? "
					+ "FROM DUAL "
					+ "WHERE NOT EXISTS "
					+ "(SELECT * FROM toto_teamInfo WHERE teamName=?)";*/
			String sql="INSERT INTO toto_teamInfo VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getLeagueName());
			ps.setString(2, vo.getTeamLogo());
			ps.setInt(3, vo.getTeamRank());
			ps.setString(4, vo.getTeamName());
			ps.setString(5, vo.getNewTeamName());
			ps.setInt(6, vo.getNumberOfMatches());
			ps.setInt(7, vo.getPoints());
			ps.setInt(8, vo.getWins());
			ps.setInt(9, vo.getDraws());
			ps.setInt(10, vo.getLoses());
			ps.setInt(11, vo.getTotalGoalsGet());
			ps.setDouble(12, vo.getAvgGoalsGet());
			ps.setInt(13, vo.getTotalGoalsLost());
			ps.setDouble(14, vo.getAvgGoalsLost());
			ps.setInt(15, vo.getGoalsMargin());
			//ps.setString(15, vo.getResultInRow());
			//ps.setString(16, vo.getRecentMatches());
			
			ps.executeUpdate();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			disConnection();
		}
	}
	
	public List<TeamInfoVO> teamInfoAllData(String league){
		
		List<TeamInfoVO> list=new ArrayList<TeamInfoVO>();
		
		try {
			conn=connMaker.getConnection();
			String sql="SELECT teamRank, teamLogo, newTeamName, numberOfMatches, points, wins, draws, loses, totalGoalsGet, totalGoalsLost, goalsMargin "
					+ "FROM toto_teamInfo "
					+ "WHERE leagueName=?"
					+ "ORDER BY teamRank ASC";
			ps=conn.prepareStatement(sql);
			ps.setString(1, league);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				TeamInfoVO vo=new TeamInfoVO();
				vo.setTeamRank(rs.getInt(1));
				vo.setTeamLogo(rs.getString(2));
				vo.setNewTeamName(rs.getString(3));
				vo.setNumberOfMatches(rs.getInt(4));
				vo.setPoints(rs.getInt(5));
				vo.setWins(rs.getInt(6));
				vo.setDraws(rs.getInt(7));
				vo.setLoses(rs.getInt(8));
				vo.setTotalGoalsGet(rs.getInt(9));
				vo.setTotalGoalsLost(rs.getInt(10));
				vo.setGoalsMargin(rs.getInt(11));
				
				list.add(vo);
			}
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			disConnection();
		}
		
		return list;
		
	}

	public List<TeamInfoVO> getWantedTeam(String teamname){
		List<TeamInfoVO> list=new ArrayList<>();
		
		try {
			conn=connMaker.getConnection();
			
			String sql="SELECT * FROM toto_teamInfo WHERE newteamname=?";
			ps=conn.prepareStatement(sql);
	        ps.setString(1, teamname);
	        ResultSet rs=ps.executeQuery();
	        
	        while(rs.next()) {
	        	TeamInfoVO vo=new TeamInfoVO();
	        	vo.setLeagueName(rs.getString(1));
	        	vo.setTeamLogo(rs.getString(2));
	        	vo.setTeamRank(rs.getInt(3));
	        	vo.setTeamName(rs.getString(4));
	        	vo.setNumberOfMatches(rs.getInt(6));
	        	vo.setPoints(rs.getInt(7));
	        	vo.setWins(rs.getInt(8));
	        	vo.setDraws(rs.getInt(9));
	        	vo.setLoses(rs.getInt(10));
	        	vo.setTotalGoalsGet(rs.getInt(11));
	        	vo.setAvgGoalsGet(rs.getDouble(12));
	        	vo.setTotalGoalsLost(rs.getInt(13));
	        	vo.setAvgGoalsLost(rs.getDouble(14));
	        	vo.setGoalsMargin(rs.getInt(15));
	        	
	        	list.add(vo);
	        }
	        rs.close();
		}catch (Exception e) {
			System.out.println("getWantedTeam : "+e.getMessage());
		}finally {
			disConnection();
		}

        return list;
	}
	
	
	public static void main(String[] args) {
		TeamInfoDAO dao=new TeamInfoDAO(new DBConOracle());
		//dao.getTeamInfoData();
		
	}
	
}












