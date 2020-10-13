package com.sist.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.conn.DBConOracle;
import com.sist.conn.SuperCon;

public class MatchInfoDAO {

	private PreparedStatement ps;
	private Connection conn = null;
	private SuperCon connMaker = null;
	private static MatchInfoDAO dao;

	public MatchInfoDAO(SuperCon spCon) {
		connMaker = spCon;
	}

	// ���� ����
	public void disConnection() {
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// ��� ���� ������
	public void getMatchInfoData() {

		MatchInfoVO vo = new MatchInfoVO();
		int k = 1;

		try {

			Document testDoc = Jsoup.connect("http://www.wisetoto.com/gameinfo/?game_type=pt").get();
			Element recentNum = testDoc.select("div.bbs_select li.left select option").get(9);
			int recentNumber = Integer.parseInt(recentNum.attr("value"));
			int firstNumber = 70;

			for (int i = 70; i <= recentNumber; i++) {

				Document doc = Jsoup.connect(
						"http://www.wisetoto.com/gameinfo/?game_type=pt&game_category=pt1&game_year=2017&game_round="
								+ i + "&sports=sc&sort=&gi_page_type=manual&rate_type=pt")
						.get();
				Elements match_date = doc.select("li.a2");

				for (int j = 0; j < match_date.size(); j++) {

					Element is_under_or_over = doc.select("div.gameinfo ul li").get(11 + 14 * j);
					String isUnderOrOver = is_under_or_over.text();

					Element game_number = doc.select("li.a1").get(j);
					Element match_Date = match_date.get(j);
					Element league_name = doc.select("li.a4").get(j);
					Element home_team = doc.select("div.gameinfo ul li").get(5 + 14 * j);
					Element home_team_score = doc.select("div.gameinfo ul li").get(5 + 14 * j);
					Element away_team_score = doc.select("div.gameinfo ul li").get(7 + 14 * j);
					Element away_team = doc.select("div.gameinfo ul li").get(7 + 14 * j);
					Elements code = doc.select("li.a9");
					Element win_code = code.get(3 * j);
					Element draw_code = code.get(3 * j + 1);
					Element lose_code = code.get(3 * j + 2);

					if (isUnderOrOver.equals("�����")) {
						Element handicaps = doc.select("div.gameinfo ul li").get(4 + 14 * j);
						String handicap = handicaps.text();
						if (!handicap.equals("")) {
							continue;
						}

						int gameNumber = Integer.parseInt(game_number.text());

						String matchDate = match_Date.text();

						String leagueName = league_name.text();

						String homeTeam = home_team.text();

						// int
						// homeTeamScore=Integer.parseInt(home_team_score.text().substring(home_team_score.text().length()-1,home_team_score.text().length()));
						// int awayTeamScore=Integer.parseInt(away_team_score.text().substring(0,1));
						String awayTeam = away_team.text();

						String winDivRate = "-";
						// win_code.text();
						/*
						 * if(winDivRate.length()>4) { winDivRate=winDivRate.substring(2,
						 * winDivRate.length()-1); }
						 */

						if (draw_code.text().indexOf("-") == 0) {

						}
						String drawDivRate = "-";
						// draw_code.text();
						/*
						 * if(drawDivRate.length()>4) { drawDivRate=drawDivRate.substring(2,
						 * drawDivRate.length()-1); }
						 */

						String loseDivRate = "-";
						// lose_code.text();
						/*
						 * if(loseDivRate.length()>4) { loseDivRate=loseDivRate.substring(2,
						 * loseDivRate.length()-1); }
						 */

						/*vo.setGameNumber(gameNumber);
						vo.setMatchDate(matchDate);
						vo.setLeagueName(leagueName);
						vo.setHomeTeam(homeTeam);
						vo.setHomeTeamScore("-");
						vo.setAwayTeamScore("-");
						vo.setAwayTeam(awayTeam);
						vo.setWinDivRate(winDivRate);
						vo.setDrawDivRate(drawDivRate);
						vo.setLoseDivRate(loseDivRate);
						vo.setMatchResult(isUnderOrOver);
						insertMatchInfoData(vo);*/

						/*System.out.println(
								gameNumber + " " + matchDate + " " + leagueName + " " + homeTeam + " " + awayTeam + " "
										+ winDivRate + " " + drawDivRate + " " + loseDivRate + " " + isUnderOrOver);*/
						continue;
					}

					if (isUnderOrOver.equals("���") || isUnderOrOver.equals("����") || isUnderOrOver.equals("�ڵ��")
							|| isUnderOrOver.equals("�ڵ�") || isUnderOrOver.equals("�ڵ���")
							|| isUnderOrOver.equals("���")) {
						continue;
					}

					int gameNumber = Integer.parseInt(game_number.text());

					String matchDate = match_Date.text();

					String leagueName = league_name.text();

					String homeTeam = home_team.text().substring(0, home_team.text().indexOf(" "));

					String homeTeamScore = home_team_score.text().substring(home_team_score.text().length() - 1,
							home_team_score.text().length());

					String awayTeamScore = away_team_score.text().substring(0, 1);

					String awayTeam = away_team.text().substring(1, away_team.text().length());

					String winDivRate = win_code.text();
					if (winDivRate.length() > 5) {
						winDivRate = winDivRate.substring(2, winDivRate.length() - 1);
					}

					if (draw_code.text().indexOf("-") == 0) {
						String drawDivRate = null;
					}
					String drawDivRate = draw_code.text();
					if (drawDivRate.length() > 5) {
						drawDivRate = drawDivRate.substring(2, drawDivRate.length() - 1);
					}
					String loseDivRate = lose_code.text();
					if (loseDivRate.length() > 5) {
						loseDivRate = loseDivRate.substring(2, loseDivRate.length() - 1);
					}

					vo.setGameNumber(gameNumber);
					vo.setMatchDate(matchDate);
					vo.setLeagueName(leagueName);
					vo.setHomeTeam(homeTeam);
					vo.setHomeTeamScore(homeTeamScore);
					vo.setAwayTeamScore(awayTeamScore);
					vo.setAwayTeam(awayTeam);
					vo.setWinDivRate(winDivRate);
					vo.setDrawDivRate(drawDivRate);
					vo.setLoseDivRate(loseDivRate);
					vo.setMatchResult(isUnderOrOver);
					insertMatchInfoData(vo);

					System.out.println(gameNumber + " " + matchDate + " " + leagueName + " " + homeTeam + " "
							+ homeTeamScore + " " + awayTeamScore + " " + awayTeam + " " + winDivRate + " "
							+ drawDivRate + " " + loseDivRate + " " + isUnderOrOver);

				}

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

	}

	// ��� ���� DB�� �� �߰��ϱ�
	public void insertMatchInfoData(MatchInfoVO vo) {

		try {
			conn = connMaker.getConnection();
			String sql = "INSERT INTO toto_matchInfo " + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getGameNumber());
			ps.setString(2, vo.getMatchDate());
			ps.setString(3, vo.getLeagueName());
			ps.setString(4, vo.getHomeTeam());
			ps.setString(5, vo.getHomeTeamScore());
			ps.setString(6, vo.getAwayTeamScore());
			ps.setString(7, vo.getAwayTeam());
			ps.setString(8, vo.getWinDivRate());
			ps.setString(9, vo.getDrawDivRate());
			ps.setString(10, vo.getLoseDivRate());
			ps.setString(11, vo.getMatchResult());
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

	}

	// ���ϴ� ���� ���� �޾ƿ���
	public void getUsefulMatchInfoData() {

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT gameNumber, matchDate, leagueName, homeTeam, homeTeamScore, awayTeamScore, awayTeam, winDivRate, drawDivRate, loseDivRate, matchResult FROM toto_matchInfo "
					+ "WHERE leagueName IN ('�е�����', '�󸮰�', 'EPL', '�������', '������1', '������A') " + "ORDER BY matchDate ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MatchInfoVO vo = new MatchInfoVO();
				vo.setGameNumber(rs.getInt(1));
				vo.setMatchDate(rs.getString(2));
				vo.setLeagueName(rs.getString(3));
				vo.setHomeTeam(rs.getString(4));
				vo.setHomeTeamScore(rs.getString(5));
				vo.setAwayTeamScore(rs.getString(6));
				vo.setAwayTeam(rs.getString(7));
				vo.setWinDivRate(rs.getString(8));
				vo.setDrawDivRate(rs.getString(9));
				vo.setLoseDivRate(rs.getString(10));
				vo.setMatchResult(rs.getString(11));

				// System.out.println(vo.getGameNumber()+" "+vo.getMatchDate()+"
				// "+vo.getLeagueName()+" "+vo.getHomeTeam()+" "+vo.getHomeTeamScore()+"
				// "+vo.getAwayTeamScore()+" "+vo.getAwayTeam()+" "+vo.getWinDivRate()+"
				// "+vo.getDrawDivRate()+" "+vo.getLoseDivRate()+" "+vo.getMatchResult());
			}
			rs.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

	}

	public List<MatchInfoVO> matchInfoAllData(String month, int page) {

		List<MatchInfoVO> list = new ArrayList<MatchInfoVO>();

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT gameNumber, matchDate, leagueName, homeTeam, homeTeamScore, awayTeamScore, awayTeam, winDivRate, drawDivRate, loseDivRate, matchResult FROM toto_matchInfo "
					+ "WHERE SUBSTR(matchDate,0,2)=? "
					+ "AND leagueName IN ('�е�����', '�󸮰�', 'EPL', '�������', '������1', '������A') " + "ORDER BY matchDate DESC";
			ps = conn.prepareStatement(sql);
			ps.setString(1, month);
			ResultSet rs = ps.executeQuery();

			int i = 0;
			int j = 0;
			int pagecnt = (page * 15) - 15;

			while (rs.next()) {
				if (i < 15 && j >= pagecnt) {
					MatchInfoVO vo = new MatchInfoVO();
					vo.setGameNumber(rs.getInt(1));
					vo.setMatchDate(rs.getString(2));
					vo.setLeagueName(rs.getString(3));
					vo.setHomeTeam(rs.getString(4));
					vo.setHomeTeamScore(rs.getString(5));
					vo.setAwayTeamScore(rs.getString(6));
					vo.setAwayTeam(rs.getString(7));
					vo.setWinDivRate(rs.getString(8));
					vo.setDrawDivRate(rs.getString(9));
					vo.setLoseDivRate(rs.getString(10));
					vo.setMatchResult(rs.getString(11));
					/*System.out.println(vo.getGameNumber() + " " + vo.getMatchDate() + " " + vo.getLeagueName() + " "
							+ vo.getHomeTeam() + " " + vo.getHomeTeamScore() + " " + vo.getAwayTeamScore() + " "
							+ vo.getAwayTeam() + " " + vo.getWinDivRate() + " " + vo.getDrawDivRate() + " "
							+ vo.getLoseDivRate() + " " + vo.getMatchResult());*/

					list.add(vo);
					i++;
				}
				j++;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

		return list;

	}

	public void DropMatchInfo() {
		try {
			conn = connMaker.getConnection();
			String sql = "DROP TABLE toto_matchInfo CASCADE CONSTRAINTS";
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			//System.out.println("DROP �Ϸ�");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}
	}

	public void CreateMatchInfo() {
		try {
			conn = connMaker.getConnection();
			String sql = "CREATE TABLE toto_matchInfo(" + "  gameNumber    NUMBER CONSTRAINT tmi_gnumber_nn NOT NULL,"
					+ "  matchDate     VARCHAR2(50) CONSTRAINT tmi_md_nn NOT NULL,"
					+ "  leagueName    VARCHAR2(60) CONSTRAINT tmi_league_nn NOT NULL,"
					+ "  homeTeam      VARCHAR2(40) CONSTRAINT tmi_ht_nn NOT NULL," + "  homeTeamScore VARCHAR2(20),"
					+ "  awayTeamScore VARCHAR2(20)," + "  awayTeam      VARCHAR2(40) CONSTRAINT tmi_at_nn NOT NULL,"
					+ "  winDivRate    VARCHAR2(10)," + "  drawDivRate   VARCHAR2(10),"
					+ "  loseDivRate   VARCHAR2(10)," + "  matchResult   VARCHAR2(14)" + ")";
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			//System.out.println("CREATE �Ϸ�");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}
	}

	public void insertextraData() {
		try {
			conn = connMaker.getConnection();
			String sql = "INSERT INTO toto_matchInfo (gameNumber,matchDate,leagueName,homeTeam,homeTeamScore,awayTeamScore,awayTeam,winDivRate,drawDivRate,loseDivRate,matchResult) "
					+ "SELECT '999','11.11(��) 07:45','�󸮰�','�ٸ�����','-','-','���˸���','1.85','3.60','2.80','�����' FROM dual UNION ALL "
					+ "SELECT '998','11.11(��) 03:45','EPL','��ü��C','-','-','��ü��U','1.40','2.30','2.50','�����' FROM dual UNION ALL "
					+ "SELECT '997','11.10(��) 21:45','�е�����','���̹���','-','-','����Ʈ��','1.20','2.50','3.20','�����' FROM dual UNION ALL " 
					+ "SELECT '996','11.10(��) 23:15','������A','������','-','-','��������','2.10','1.90','2.40','�����' FROM dual UNION ALL "
					+ "SELECT '995','11.11(��) 00:00','������1','PSG','-','-','����','1.10','3.90','6.50','�����' FROM dual";

			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}
	}

	public List<MatchInfoVO> main_matchData() {
		List<MatchInfoVO> list = new ArrayList<>();

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT matchDate, homeTeam, homeTeamScore, awayTeamScore, awayTeam, matchResult FROM toto_matchInfo "
					+ "WHERE SUBSTR(matchDate,0,2)=11 "
					+ "AND leagueName IN ('�е�����', '�󸮰�', 'EPL', '�������', '������1', '������A') " + "ORDER BY matchDate DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MatchInfoVO vo = new MatchInfoVO();

				vo.setMatchDate(rs.getString(1));
				vo.setHomeTeam(rs.getString(2));
				vo.setHomeTeamScore(rs.getString(3));
				vo.setAwayTeamScore(rs.getString(4));
				vo.setAwayTeam(rs.getString(5));
				vo.setMatchResult(rs.getString(6));

				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("main_matchData : " + e.getMessage());
		} finally {
			disConnection();
		}

		return list;
	}

	public int matchInfoTotalPage(String month) {
		int total = 0;

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT CEIL(COUNT(*)/15) FROM toto_matchInfo"
					+ "  WHERE leagueName IN ('�󸮰�','EPL','������A','�е�����','�������','������1')"
					+ "AND SUBSTR(matchDate,0,2) = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, month);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

		return total;
	}

	public static void main(String[] args) {

		MatchInfoDAO dao = new MatchInfoDAO(new DBConOracle());
		// dao.DropMatchInfo();
		// dao.CreateMatchInfo();
		// dao.getMatchInfoData();
		// dao.getUsefulMatchInfoData();
		// dao.matchInfoAllData("11");
		// dao.insertextraData();
	}

	public List<MatchInfoVO> ������޼ҵ�(String month) {

		List<MatchInfoVO> list = new ArrayList<MatchInfoVO>();

		try {
			conn = connMaker.getConnection();
			String sql = "SELECT gameNumber, matchDate, leagueName, homeTeam, homeTeamScore, awayTeamScore, awayTeam, winDivRate, drawDivRate, loseDivRate, matchResult FROM toto_matchInfo "
					+ "WHERE SUBSTR(matchDate,0,2)=? " + "AND matchresult='�����' "
					+ "AND leagueName IN ('�е�����', '�󸮰�', 'EPL', '�������', '������1', '������A') " + "ORDER BY matchDate DESC";
			ps = conn.prepareStatement(sql);
			ps.setString(1, month);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MatchInfoVO vo = new MatchInfoVO();
				vo.setGameNumber(rs.getInt(1));
				vo.setMatchDate(rs.getString(2));
				vo.setLeagueName(rs.getString(3));
				vo.setHomeTeam(rs.getString(4));
				vo.setHomeTeamScore(rs.getString(5));
				vo.setAwayTeamScore(rs.getString(6));
				vo.setAwayTeam(rs.getString(7));
				vo.setWinDivRate(rs.getString(8));
				vo.setDrawDivRate(rs.getString(9));
				vo.setLoseDivRate(rs.getString(10));
				vo.setMatchResult(rs.getString(11));
				/*System.out.println(vo.getGameNumber() + " " + vo.getMatchDate() + " " + vo.getLeagueName() + " "
						+ vo.getHomeTeam() + " " + vo.getHomeTeamScore() + " " + vo.getAwayTeamScore() + " "
						+ vo.getAwayTeam() + " " + vo.getWinDivRate() + " " + vo.getDrawDivRate() + " "
						+ vo.getLoseDivRate() + " " + vo.getMatchResult());*/

				list.add(vo);

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}

		return list;

	}
	
	/*public String GetGameResult(String date, String hometeam) {
		String gameresult="";
		
		try {
			conn=connMaker.getConnection();
			String sql="SELECT matchresult FROM toto_matchinfo WHERE matchdate=? AND hometeam=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, hometeam);
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			gameresult=rs.getString(1);
			rs.close();
		}catch (Exception e) {
			System.out.println("GetGameResult : " + e.getMessage());
		}finally {
			disConnection();
		}
		
		
		return gameresult;
	}*/
	
	//���� ����
	public String bettingData(String matchDate,String homeTeam) {
		String matchResult="";
		
		try {
			conn = connMaker.getConnection();
			String sql = "SELECT matchResult FROM toto_matchInfo "
					+ "WHERE matchdate=? AND hometeam=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, matchDate);
			ps.setString(2, homeTeam);
			ResultSet rs = ps.executeQuery();
			rs.next();
			matchResult=rs.getString(1);
			rs.close();
		} catch (Exception e) {
			System.out.println("bettingData : " + e.getMessage());
		} finally {
			disConnection();
		}
		
		return matchResult;
	}

}