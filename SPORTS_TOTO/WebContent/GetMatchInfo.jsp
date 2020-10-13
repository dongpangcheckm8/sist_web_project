<%@page import="java.util.concurrent.Delayed"%>
<%@page import="com.sist.conn.DBConOracle"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, com.sist.dao.*"%>
<%
	MatchInfoDAO dao = new MatchInfoDAO(new DBConOracle());
	MemberDAO Mdao = new MemberDAO(new DBConOracle());
	PurchaseDAO Pdao = new PurchaseDAO(new DBConOracle());

	List<MemberVO> list = Mdao.getAllID();
	List<PurchaseVO> Plist = Pdao.purchaseAllData();

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
	String today = sdf.format(date).toString();

	out.print(today);

	if (today.equals("02:24:10 ����")) {
		dao.DropMatchInfo();
		dao.CreateMatchInfo();
		dao.getMatchInfoData();
		dao.insertextraData();

		String matchDate = "";
		String homeTeam = "";
		String matchResult = "";

		for (PurchaseVO vo3 : Plist) {
			matchDate = vo3.getMatchDate();
			homeTeam = vo3.getHomeTeam();
			System.out.println("matchDate=" + matchDate);
			System.out.println("homeTeam=" + homeTeam);
			matchResult = dao.bettingData(matchDate, homeTeam);
			System.out.println("matchResult=" + matchResult);
			Pdao.purchaseResultUpdate(matchResult, matchDate, homeTeam);
		}

		for (MemberVO vo : list) {
			Pdao.finalround(vo.getId());
		}

		response.setIntHeader("Refresh", 1);
	} else {
		response.setIntHeader("Refresh", 1);
	}
%>