<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sist.dao.*, java.util.*, java.text.*" %>
<%
	TeamInfoDAO dao=new TeamInfoDAO(new DBConOracle());
	List<TeamInfoVO> list=dao.teamInfoAllData("에레디비");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css">
</head>
<body>
	<table id="table_content" width="670" height="220">
		<tr>
			<th width="7%">순위</th>
			<th width="33%" colspan="2">팀</th>
			<th width="9%">경기수</th>
			<th width="7%">승점</th>
			<th width="7%">승</th>
			<th width="7%">무</th>
			<th width="7%">패</th>
			<th width="7%">득점</th>
			<th width="7%">실점</th>
			<th width="9%">골득실</th>
		</tr>
		<%
		for(TeamInfoVO vo:list){
			if(vo.getTeamRank()>10)
				break;
			%>
		<tr>
			<td width="7%" height="10" align="center"><%=vo.getTeamRank() %></td>
			<td width="3%" align="center"><img src="<%=vo.getTeamLogo()%>" width="13" height="13"></td>
			<td width="30%" align="left"><a
					href="../teaminfo/TeamInfo.jsp?teamname=<%=vo.getNewTeamName()%>"><%=vo.getNewTeamName() %></a></td>
			<td width="9%" align="center"><%=vo.getNumberOfMatches() %></td>
			<td width="7%" align="center"><%=vo.getPoints() %></td>
			<td width="7%" align="center"><%=vo.getWins() %></td>
			<td width="7%" align="center"><%=vo.getDraws() %></td>
			<td width="7%" align="center"><%=vo.getLoses() %></td>
			<td width="7%" align="center"><%=vo.getTotalGoalsGet() %></td>
			<td width="7%" align="center"><%=vo.getTotalGoalsLost() %></td>
			<td width="7%" align="center"><%=vo.getGoalsMargin() %></td>
		</tr>
			<%
		}
		%>
	</table>
</body>
</html>