<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.dao.*,java.util.*"%>
<%
	request.setCharacterEncoding("EUC-KR");
	String id=(String)session.getAttribute("id");
	String name=request.getParameter("name");
	String gamemoney=request.getParameter("gamemoney");
	String charge=request.getParameter("charge");
	PurchaseDAO dao=new PurchaseDAO(new DBConOracle());
	
	if(dao.isNan(charge)==false){
		%>
		<script type="text/javascript">
		alert("���ڸ� �Է��ϼ���");
		history.back();
		</script>
		<%
	}else{
		if(Integer.parseInt(charge)<=0){
			%>
			<script type="text/javascript">
			alert("0�� ���Ϸδ� ������ �Ұ����մϴ�");
			history.back();
			</script>
	<%
		}else{
			%>
			<script type="text/javascript">
			alert("���� ��û�� �Ϸ� �Ǿ����ϴ�.");
   			</script>
			<%
			ChargeVO vo=new ChargeVO();
			vo.setId(id);
			vo.setName(name);
			vo.setGamemoney(gamemoney);
			vo.setCharge(charge);
			
			ChargeDAO Cdao=new ChargeDAO(new DBConOracle());
			Cdao.chargeInsert(vo);
			
			response.sendRedirect("mypage.jsp");
		}
		
	}
	
	
%>