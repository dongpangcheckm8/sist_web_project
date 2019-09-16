<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.notice.*"%>
<%
	String no=request.getParameter("no");
	
	NoticeDAO dao=new NoticeDAO(new DBConOracle());
	dao.noticeDelete(Integer.parseInt(no));
	
	response.sendRedirect("list.jsp");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	삭제
</body>
</html>