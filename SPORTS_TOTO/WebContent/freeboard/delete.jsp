<%@page import="com.sist.conn.DBConOracle"%>
<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.freeboard.*"%>
<%
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	
	if(pwd.equals(session.getAttribute("pwd"))){
		BoardDAO dao=new BoardDAO(new DBConOracle());
		dao.boardDelete(Integer.parseInt(no));
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw=response.getWriter();
        out.println("<script>alert('������ �Ϸ�Ǿ����ϴ�..');location.href=\"list.jsp\";</script>");
        out.flush();
	}else{
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw=response.getWriter();
        out.println("<script>alert('��й�ȣ�� Ʋ�Ƚ��ϴ�.');history.go(-1);</script>");
        out.flush();
	}
	
	response.sendRedirect("list.jsp");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	����
</body>
</html>