<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
<%
	String matchDate=request.getParameter("date");
	String homeTeam=request.getParameter("hometeam");
	String awayTeam=request.getParameter("awayteam");
	String id=request.getParameter("id");
	if(id.equals("null")){
		%>
		<script type="text/javascript">
			alert("로그인을 해주세요!");
			history.back();
		</script>
<%
	}
	//matchDate=matchDate.replaceAll(" ", "");
	PurchaseDAO dao=new PurchaseDAO(new DBConOracle());
	List<PurchaseVO> list=dao.getPurchaseAllData(matchDate, homeTeam, awayTeam, id);
	
	String ck="";
	if((String)session.getAttribute("permission_level")==null){
		ck="2";
	}else{
   		ck=(String)session.getAttribute("permission_level");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css?ver=1">
	<%if(ck.equals("1")){
      %>
      <style type="text/css">
         #betting{
            visibility: hidden;
         }   
      </style>
      <%
   }
   %>
</head>
<body>
	<table border="0" align="center" width="1100" height="50">
		<tr>
			<td align="center">
				<jsp:include page="../project_main/menu.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<table id="table_content2" border="0" width="1100" align="center" cellspacing="0">
		<%
			for(PurchaseVO vo:list){
				%>
		<form action="purchase_ok.jsp" method="post">
		<tr>
			<th rowspan="2"  style="border-top-left-radius: 1em;"><img alt="" src="<%=vo.getTeamLogo()%>" width="300"
				height="300" align="center">
				<input type="hidden" name="teamLogo" value="<%=vo.getTeamLogo()%>">
			</th>
			<th align="center" valign="bottom" width="30" height="195">
				<font size="24pt">vs</font>
			</th>
			<th rowspan="2"  style="border-top-right-radius: 1em;"><img alt="" src="<%=vo.getTeam2Logo()%>" width="300"
				height="300" align="center">
				<input type="hidden" name="team2Logo" value="<%=vo.getTeam2Logo()%>">
			</th>
		</tr>
		<tr>
			<th align="center" valign="top" height="105">
				<font size="4pt">
				<%=vo.getMatchDate() %>
				</font>
				<input type="hidden" name="matchDate" value="<%=matchDate%>">
			</th>
		</tr>
		<tr>
			<td width="300" height="20" align="center">[HOME TEAM]</td>
			<td width="30" height="20" align="center"><%=vo.getMatchResult() %></td>
				<input type="hidden" name="matchResult" value="<%=vo.getMatchResult()%>">
			<td width="300" height="20" align="center">[AWAY TEAM]</td>
		</tr>
		<tr>
			<td width="300" height="100" align="center">
				<%=vo.getHomeTeam() %>
				<input type="hidden" name="homeTeam" value="<%=homeTeam%>">
			</td>
			<td width="30" height="100"></td>
			<td width="300" height="100" align="center">
				<%=vo.getAwayTeam() %>
				<input type="hidden" name="awayTeam" value="<%=awayTeam%>">
			</td>
		</tr>
		<tr>
			<td width="300" height="70" align="center">
				<%=vo.getTeamRank() %>위
				<input type="hidden" name="teamRank" value="<%=vo.getTeamRank()%>">
			</td>
			<td width="30" height="70"></td>
			<td width="300" height="70" align="center">
				<%=vo.getTeam2Rank() %>위
				<input type="hidden" name="team2Rank" value="<%=vo.getTeam2Rank()%>">
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				홈승&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;무승부&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;홈패<br/><br/>
				<%=vo.getWinDivRate() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=vo.getDrawDivRate() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=vo.getLoseDivRate() %><br/><br/>
				<input type="radio" name="결과" value="<%=vo.getWinDivRate()%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="결과" value="<%=vo.getDrawDivRate()%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="결과" value="<%=vo.getLoseDivRate()%>">
				<br>
				<br>
			</td>
			<input type="hidden" name="홈승" value="<%=vo.getWinDivRate()%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="hidden" name="무승부" value="<%=vo.getDrawDivRate()%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="hidden" name="홈패" value="<%=vo.getLoseDivRate()%>">
			<input type="hidden" name="gamemoney" value="<%=vo.getGamemoney()%>">
			<%-- <td width="300" height="150" align="center">홈승<br/><br/><%=vo.getWinDivRate() %><br/><br/><input type="radio" name="홈승" value="<%=vo.getWinDivRate()%>"></td>
			<td width="30" height="150" align="center">무승부<br/><br/><%=vo.getDrawDivRate() %><br/><br/><input type="radio" name="무승부" value="<%=vo.getDrawDivRate()%>"></td>
			<td width="300" height="150" align="center">홈패<br/><br/><%=vo.getLoseDivRate() %><br/><br/><input type="radio" name="홈패" value="<%=vo.getLoseDivRate()%>"></td> --%>
		</tr>
		
		
	</table>
	<table id="table_content3" border="1" width="1100" align="center" cellspacing="0">
			<tr>
				<td align="center" height="100" style="border-bottom-left-radius: 1em;border-bottom-right-radius: 1em;">
					베팅할 게임 머니
					<input type="text" name="bettingmoney">
					<input id="betting" type="submit" value="베팅">
					<input type="button" value="돌아가기" onclick="javascript:history.back()">
				 </td>
			</tr>
	</table>
	<footer align="center" height="100"><%=id %> 님의 남은 게임머니는 <br/>
		<%=vo.getGamemoney() %> 원 입니다.
		<%
			}
		%>
	</footer>
	</form>
</body>
</html>