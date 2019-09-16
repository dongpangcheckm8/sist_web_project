<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.notice.*"%>
<%
	//한글변환
	request.setCharacterEncoding("EUC-KR");
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	NoticeDAO dao=new NoticeDAO(new DBConOracle());
	NoticeVO vo=new NoticeVO();
	
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(dao.replaceHtml(content));
	vo.setPwd(pwd);
	
	//DataBase 전송
	dao.noticeInsert(vo);
	
	//화면이동
	response.sendRedirect("list.jsp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>

</body>
</html>