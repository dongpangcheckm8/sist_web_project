<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.change.*"%>
<%
	String mode=request.getParameter("mode");
	if(mode==null)
		mode="0";
	String jsp=JspChange.change(Integer.parseInt(mode));
	String log_jsp="";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css?ver=5">
</head>
<body>
	<center>
		<table border="0" align="center" width="900">
			<tr>
				<td align="center">
					<jsp:include page="../project_main/menu.jsp"></jsp:include>
				</td>
			</tr>
		</table>
		<table id="table_member2" border="1" align="center" width="1100" height="800" cellspacing="0">
			<tr>
				<td align="center" width="30%" height="100%" valign="top" style="border-top-left-radius: 1em; border-bottom-left-radius: 1em;">
					<jsp:include page="menu.jsp"></jsp:include>
				</td>
				<td valign=top width="70%" height="100%" style="border-top-right-radius: 1em; border-bottom-right-radius: 1em;">
					<jsp:include page="<%=jsp %>"></jsp:include>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>