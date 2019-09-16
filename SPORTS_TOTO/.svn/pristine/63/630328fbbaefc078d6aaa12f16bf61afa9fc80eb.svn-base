<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.sist.dao.MemberDAO"%>
<%@page import="com.sist.dao.MemberVO"%>
<%
	String id = request.getParameter("id");
	MemberDAO dao=new MemberDAO(new DBConOracle());
	
	boolean result=dao.check(id);
	
	if(result){ %>
		<center>
			<br/><br/>
			<h4>이미 사용중인 아이디 입니다.</h4>
		</center>
	<%}else{ %>
		<center>
			<br/><br/>
			<h4>입력하신 <%=id %>는 사용하실 수 있는 ID입니다.</h4>
		</center>
	<%
	}
%>
