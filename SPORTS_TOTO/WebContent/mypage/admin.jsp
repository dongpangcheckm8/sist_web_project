<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.dao.*,java.util.*"%>
<%
	String id=(String)session.getAttribute("id");
	
	ChargeDAO dao=new ChargeDAO(new DBConOracle());
	List<ChargeVO> list=dao.chargeAllData();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css?ver=2">
	<script type="text/javascript">
		function charb() {
			alert("���� ������ �Ϸ� �Ǿ����ϴ�.");
	    }
   </script>
</head>
<body>
	<center>
		<table id="table_member" border="0" align="center" width="700" cellspacing="0">
			<h3>���� ����</h3>
			<tr height="60" align="center">
				<th width="7%">��ȣ</th>
				<th width="15%">ID</th>
				<th width="10%">�̸�</th>
				<th width="30%">���� ���ӸӴ�</th>
				<th width="30%">���� ��û �ݾ�</th>
				<th width="8%">����</th>
			</tr>
			<%
            for(ChargeVO vo:list){
       		%>
			<form method="post" action="admin_ok.jsp">
			<tr height="60" align="center">
				<td><%=vo.getNo() %></td>
				<td><%=vo.getId() %></td>
				<td><%=vo.getName() %></td>
				<td><%=vo.getGamemoney() %></td>
				<td><%=vo.getCharge() %></td>
				<td>
					<input type="hidden" name="no" value="<%=vo.getNo()%>">
					<input type="hidden" name="id" value="<%=vo.getId()%>">
					<input type="hidden" name="name" value="<%=vo.getName()%>">
					<input type="hidden" name="gamemoney" value="<%=vo.getGamemoney()%>">
					<input type="hidden" name="charge" value="<%=vo.getCharge()%>">
					<input onclick="charb()" type="submit" value="Ȯ��">
				</td>
			</tr>
			</form>
			<%
            }
         	%>
		</table>
	</center>
</body>
</html>