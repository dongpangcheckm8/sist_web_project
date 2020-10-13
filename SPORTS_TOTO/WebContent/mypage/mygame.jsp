<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.dao.*,java.util.*,java.text.*"%>
<%

   String id=(String)session.getAttribute("id");
   
   
   PurchaseDAO dao=new PurchaseDAO(new DBConOracle());
   List<PurchaseVO> list=dao.getMyGameAllData(id);
   
   
   List<PurchaseVO> list3=dao.purchaseAllData();
   MatchInfoDAO dao2=new MatchInfoDAO(new DBConOracle());
   
   String matchDate="";
   String homeTeam="";
   String matchResult="";
   
   for(PurchaseVO vo3:list3){
      matchDate=vo3.getMatchDate();
      homeTeam=vo3.getHomeTeam();
      System.out.println("matchDate="+matchDate);
      System.out.println("homeTeam="+homeTeam);
      matchResult=dao2.bettingData(matchDate, homeTeam);
      System.out.println("matchResult="+matchResult);
      dao.purchaseResultUpdate(matchResult, matchDate, homeTeam);
   }
   
   
   
   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
   <center>
      <table id="table_member" border="0" align="center" width="750" cellspacing="0">
         <h3>���� ���� ����</h3>
      <tr height="60" align="center">
         <th width="14%">���� ��¥</th>
         <th width="14%">��� ��¥</th>
         <th width="13%">Ȩ ��</th>
         <th width="13%">����� ��</th>
         <th width="10%">���� ���</th>
         <th width="9%">����</th>
         <th width="17%">���� �ݾ�</th>
         <th width="10%">���</th>
      </tr>
        <%
            for(PurchaseVO vo:list){
         %>
      <tr align="center">
         <td width="14%" align="center"><%=vo.getRegdate() %></td>
         <td width="14%" align="center"><%=vo.getMatchDate() %></td>
         <td width="13%" align="center"><%=vo.getHomeTeam() %></td>
         <td width="13%" align="center"><%=vo.getAwayTeam() %></td>
         <td width="10%" align="center"><%=vo.getExpectResult() %></td>
         <td width="9%" align="center"><%=vo.getDivRate() %></td>
         <td width="17%" align="center"><%=vo.getBettingmoney() %></td>
         <td width="10%" align="center"><%=vo.getMatchResult() %></td>
      </tr>
        <%
            }
         %>
      </table>
   </center>
</body>
</html>