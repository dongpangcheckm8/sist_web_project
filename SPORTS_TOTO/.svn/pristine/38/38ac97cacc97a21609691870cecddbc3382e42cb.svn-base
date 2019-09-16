<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.dao.*"%>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	
	//MemberDAO dao=MemberDAO.newInstance();
	MemberDAO dao=new MemberDAO(new DBConOracle());
	int res=dao.isLogin(id,pwd);
	
	if(res==0){
		%>
		<script type="text/javascript">
			alert("ID가 존재하지 않습니다.");
			history.back();
		</script>
		<%
	}else if(res==1){
		%>
		<script type="text/javascript">
			alert("비밀번호가 틀립니다.");
			history.back();
		</script>
		<%
	}else if(res==2){
		String name=dao.getLogData(id);
		String permission_level=dao.getPermission(id);
		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("permission_level", permission_level);
		session.setAttribute("pwd", pwd);
		response.sendRedirect("main.jsp");
	}
%>