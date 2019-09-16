<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.sist.dao.*, java.util.*"%>



<%
	//한글변환
	request.setCharacterEncoding("UTF-8");
	String id=(String)session.getAttribute("id");
	String gamemoney=request.getParameter("gamemoney");
	String homeTeam=request.getParameter("homeTeam");
	String awayTeam=request.getParameter("awayTeam");
	String matchDate=request.getParameter("matchDate");
	String teamLogo=request.getParameter("teamLogo");
	String team2Logo=request.getParameter("team2Logo");
	int teamRank=Integer.parseInt(request.getParameter("teamRank").substring(0,1));
	int team2Rank=Integer.parseInt(request.getParameter("team2Rank").substring(0,1));
	String winDivRate=request.getParameter("홈승");
	String drawDivRate=request.getParameter("무승부");
	String loseDivRate=request.getParameter("홈패");
	String divRate=request.getParameter("결과");
	System.out.println(winDivRate+" "+drawDivRate+" "+loseDivRate+" "+divRate);
	String expectResult="";
	String matchResult=request.getParameter("matchResult");
	String bettingmoney=request.getParameter("bettingmoney");
	
	if(divRate.equals(winDivRate)){
		expectResult="홈승";
	}
	else if(divRate.equals(drawDivRate)){
		expectResult="무승부";
	}
	else{
		expectResult="홈패";
	}
	
	PurchaseVO vo=new PurchaseVO();
	vo.setTeamLogo(teamLogo);
	vo.setTeam2Logo(team2Logo);
	vo.setHomeTeam(homeTeam);
	vo.setAwayTeam(awayTeam);
	vo.setTeamRank(teamRank);
	vo.setTeam2Rank(team2Rank);
	vo.setMatchDate(matchDate);
	vo.setWinDivRate(winDivRate);
	vo.setDrawDivRate(drawDivRate);
	vo.setLoseDivRate(loseDivRate);
	vo.setDivRate(divRate);
	vo.setExpectResult(expectResult);
	vo.setMatchResult(matchResult);
	vo.setId(id);
	vo.setGamemoney(gamemoney);
	vo.setBettingmoney(bettingmoney);
	
	
	PurchaseDAO dao=new PurchaseDAO(new DBConOracle());
	List<PurchaseVO> list=dao.getPurchaseAllData(matchDate, homeTeam, awayTeam, id);
	
	list.add(vo);
	
	dao.purchaseUpdate(gamemoney, id);
	
	if(dao.isNan(bettingmoney)==false||vo.getDivRate()==null){
		%>
		<script type="text/javascript">
		alert("배팅 정보를 다시 확인해주세요!");
		history.back();
		</script>
		<%
	}else{
		
		
		if(Integer.parseInt(bettingmoney)<1000||Integer.parseInt(bettingmoney)>Integer.parseInt(gamemoney)){
			%>
			<script type="text/javascript">
			alert("배팅 금액은 1000원 이상이여야 하며, 보유 중인 게임머니를 초과할 수 없습니다.");
			history.back();
			</script>
	<%
		}else{
			dao.insertPurchaseData(vo);
			out.print(divRate);
			response.sendRedirect("../project_main/main.jsp");
		}
		
	}
	
	int sub=Integer.parseInt(gamemoney)-Integer.parseInt(bettingmoney);
	String submoney=Integer.toString(sub);
	dao.purchaseUpdate(submoney, id);
	
	
	
%>