<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sist.dao.*, java.util.*, java.text.*, org.jsoup.*, org.jsoup.nodes.Document, org.jsoup.nodes.Element" %>
<%
	int i, j=0;
	MatchInfoDAO dao=new MatchInfoDAO(new DBConOracle());
	
	
	Document testDoc=Jsoup.connect("http://www.wisetoto.com/gameinfo/?game_type=pt").get();
	Element recentNum=testDoc.select("div.bbs_select li.left select option").get(9);
	int recentNumber=Integer.parseInt(recentNum.attr("value"));
	int firstNumber=70;
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String today=sdf.format(date).toString();
	
	int month=Integer.parseInt(today.substring(5,7));
	String resultMonth=(String)session.getAttribute("month");
	//String resultWeek=(String)session.getAttribute("week");
	
	String strPage=request.getParameter("page");
	
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	
	List<MatchInfoVO> list=dao.matchInfoAllData(resultMonth,curpage);
	int totalpage=dao.matchInfoTotalPage(resultMonth);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css?ver=1">
</head>
<body>
	<center>
		<table border="0" align="center" width="900">
			<tr>
				<td align="center">
					<jsp:include page="../project_main/menu.jsp"></jsp:include>
				</td>
			</tr>
		</table>
		<table width="1100" align="center">
			<tr align="left">
				
			</tr>
		</table>
		<table id="table_content2" border="1" bordercolor="#3ADF00" align="center" width="1100" height="650" cellspacing="0">
			<tr height="13">
				<th width="7%" rowspan="2" style="border-top-left-radius: 1em;">번호</th>
				<th width="15%" rowspan="2">일시</th>
				<th width="10%" rowspan="2">리그</th>
				<th width="25%" colspan="5" rowspan="2">홈팀 vs 원정팀</th>
				<th width="24%" colspan="3">배당률</th>
				<th width="9%" rowspan="2">결과</th>
				<th width="10%" rowspan="2" style="border-top-right-radius: 1em;">구매</th>
			</tr>
			<tr height="13">
				<th width="8%" align="center">홈 승</th>
				<th width="8%" align="center">무승부</th>
				<th width="8%" align="center">홈 패</th>
			</tr>
			<%
				if(list.size()==0){
					%>
					<td width="100%" height="90%" align="center" colspan="13">해당 월의 경기 정보는 존재하지 않습니다.</td>
					<%
				}
				int count=0;
				for(MatchInfoVO vo:list){
			%>
			<tr>
				<td width="7%" align="center"><%=vo.getGameNumber() %></td>
				<td width="15%" align="center"><%=vo.getMatchDate() %></td>
				<td width="10%" align="center"><%=vo.getLeagueName() %></td>
				<td width="8%" align="right" style="border-right-style: none;"><a
					href="../teaminfo/TeamInfo.jsp?teamname=<%=vo.getHomeTeam()%>"><%=vo.getHomeTeam() %></a></td>
				<td width="3%" align="right" style="border-left-style: none;border-right-style: none;"><%=vo.getHomeTeamScore() %></td>
				<td width="2%" align="center" style="border-left-style: none;border-right-style: none;">:</td>
				<td width="3%" align="left" style="border-left-style: none;border-right-style: none;"><%=vo.getAwayTeamScore() %></td>
				<td width="8%" align="left" style="border-left-style: none;"><a
					href="../teaminfo/TeamInfo.jsp?teamname=<%=vo.getAwayTeam()%>"><%=vo.getAwayTeam() %></a></td>
				<td width="8%" align="center"><%=vo.getWinDivRate() %></td>
				<td width="8%" align="center"><%=vo.getDrawDivRate() %></td>
				<td width="8%" align="center"><%=vo.getLoseDivRate() %></td>
				<td width="9%" align="center"><%=vo.getMatchResult() %></td>
				<%
					if(vo.getMatchResult().equals("경기전")){
						%>
						<td width="10%" align="center"><a href="buy.jsp">구매</a></td>
						<%
					}
					else{
						%>
						<td width="10%" align="center">-</td>
						<%
					}
				count++;
				%>
			</tr>		
			<%} %>
			<%
			if(count<15){
				%>
				<tr>
					<td rowspan="<%=15-count%>" style="border-style: none;"></td>
				</tr>
				<%
			}
			%>
		</table>
		<table id="table_content3" width="1100" height="50">
			<tr>
				<td align="left" width="20%">
					<form method="post" action="info_game_ok.jsp">
						&nbsp;&nbsp;<select name="month" style="border: 1px solid #3ADF00">
							
								<option value="11">11월</option>
								<option value="10">10월</option>
								<option value="09">9월</option>
							
							<%-- <input type="hidden" name="month" value="<%=i%>"> --%>
						</select>
						<input type="submit" value="조회">
					</form>
				</td>
				<td align="center">
					<a href="info_game.jsp?page=<%=curpage>1 ? curpage-1:curpage%>">이전</a>
					<%for(i=1;i<=totalpage;i++){
						%>
						<%if(curpage<=5 && i<10){
							%>
							<a href="info_game.jsp?page=<%=i%>">
							<%=i %>
							</a>
							<%
						}else if(curpage-5<i && curpage+5>i){
							%>
							<a href="info_game.jsp?page=<%=i%>">
							<%=i %>
							</a>
							<%
						}%>
						<%
					}%>
					<a href="info_game.jsp?page=<%=curpage<totalpage ? curpage+1:curpage%>">다음</a>
					&nbsp;&nbsp;
				</td>
				<td align="center" width="20%">
					<%=curpage %> page / <%=totalpage %> pages
				</td>
			</tr>
		</table>
	</center>
</body>
</html>