<%@page import="com.sist.conn.DBConOracle"%>
<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.freeboard.*"%>
<%
	//�ѱۺ�ȯ
	request.setCharacterEncoding("EUC-KR");
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	BoardDAO dao=new BoardDAO(new DBConOracle());
	
	BoardVO vo=new BoardVO();
	vo.setName(name);
	vo.setSubject(dao.replaceHtml(subject));
	vo.setContent(dao.replaceHtml(content));
	vo.setPwd(pwd);
	
	if(pwd.equals(session.getAttribute("pwd"))){
		//DataBase ����
		dao.boardInsert(vo);
	}else{
		 response.setContentType("text/html; charset=UTF-8");
         PrintWriter pw=response.getWriter();
         out.println("<script>alert('��й�ȣ�� Ʋ�Ƚ��ϴ�.');history.go(-1);</script>");
         out.flush();
	}
	
	
	//ȭ���̵�
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