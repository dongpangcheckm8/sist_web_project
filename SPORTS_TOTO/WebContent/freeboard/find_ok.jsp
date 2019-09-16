<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sist.freeboard.*,java.util.*,java.text.*" %>
<%
	request.setCharacterEncoding("EUC-KR");
	String fs=request.getParameter("fs");
	String ss=request.getParameter("ss");
	session.setAttribute("fs", fs);
	session.setAttribute("ss", ss);
	
	response.sendRedirect("find.jsp");
%>