<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.dao.*,java.util.*"%>
<%
   String id=(String)session.getAttribute("id");
   
   MemberDAO dao=new MemberDAO(new DBConOracle());
   List<MemberVO> list=dao.memberAllData(id);
   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css?ver=2">
</head>
<body>
	<center>
		<form method="post" action="charge_ok.jsp">
			<table id="table_member" border="0" align="center" width="500" height="100" cellspacing="0">
				<br><br><br><br><br>
				<h3>충전소</h3>
				<%
				for(MemberVO vo:list){
				%>
				<tr>
					<th width="40%">보유 게임 머니</th>
					<td>&nbsp;&nbsp;&nbsp;<%=vo.getGamemoney() %></td>
				</tr>
				<tr>
					<th width="40%">충전 금액</th>
					<td>
						<input type="text" name="charge">
					</td>
				</tr>
			</table>
			</br>
			<input type="hidden" name="name" value="<%=vo.getName()%>">
			<input type="hidden" name="gamemoney" value="<%=vo.getGamemoney()%>">
			<input onclick="charb()" type="submit" value="충전">
				<%
				}
				%>
		</form>
	</center>
</body>
</html>