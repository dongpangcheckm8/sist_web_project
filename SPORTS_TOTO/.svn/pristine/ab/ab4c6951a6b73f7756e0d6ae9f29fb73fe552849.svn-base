<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*, java.text.*"%>
<%
	MatchInfoDAO dao = new MatchInfoDAO(new DBConOracle());
	List<MatchInfoVO> list = dao.main_matchData();
	MatchInfoVO vo = new MatchInfoVO();
	String teamname = "";
	String id=(String)session.getAttribute("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		#pv th{
			background-color: #CEFBC9;
			opacity: 0.92;
		}
		#pv a:link {
			color: black; text-decoration: none;
		}
		#pv a:visited {
			color: black; text-decoration: none;
		}
		#pv a:hover {
			color: #22741C; text-decoration: none;
		}
	</style>
</head>
<body>
	<center>
		<table id="pv" border="" bordercolor="#3ADF00" align="center" height="100" width="650px" cellspacing="0">
			<tr align="center">
				<th width="20%" rowspan="2">일시</th>
				<th width="50%" colspan="5" rowspan="2">홈vs원정</th>
				<th width="20%" rowspan="2">결과</th>
				<th width="10%" rowspan="2">구매</th>
			</tr>
			<%
				for (MatchInfoVO vo1 : list) {
			%>
			<tr></tr>
			<tr align="center">
				<td><%=vo1.getMatchDate()%></td>
				<td style="border-right-style: none;"><a
					href="../teaminfo/TeamInfo.jsp?teamname=<%=vo1.getHomeTeam()%>"><%=vo1.getHomeTeam()%></a></td>
				<td style="border-left-style: none;border-right-style: none;"><%=vo1.getHomeTeamScore()%></td>
				<td style="border-left-style: none;border-right-style: none;">:</td>
				<td style="border-left-style: none;border-right-style: none;"><%=vo1.getAwayTeamScore()%></td>
				<td style="border-left-style: none;"><a
					href="../teaminfo/TeamInfo.jsp?teamname=<%=vo1.getAwayTeam()%>"><%=vo1.getAwayTeam()%></a></td>
				<td><%=vo1.getMatchResult()%></td>
				<%
					if (vo1.getMatchResult().equals("경기전")) {
				%>
				<td width="10%" align="center"><a href="../purchase/purchase.jsp?date=<%=vo1.getMatchDate()%>&hometeam=<%=vo1.getHomeTeam()%>&awayteam=<%=vo1.getAwayTeam() %>&id=<%=id%>">구매</a></td>
				<%
					} else {
				%>
				<td width="10%" align="center">-</td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</center>
</body>
</html>