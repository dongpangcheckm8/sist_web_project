<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.change.*"%>
    
<%
	String mode=request.getParameter("mode");
	if(mode==null)
		mode="0";
	String jsp=JspChange.change(Integer.parseInt(mode));
	String log_jsp="";
	
	String id=(String)session.getAttribute("id");
	if(id==null)
		log_jsp="login.jsp";
	else
		log_jsp="logout.jsp"; 
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		#table td{
			background-color: #FFFFFF;
			opacity: 0.92;
		}
		body{
			background-image: url("../image/stadium.png");
		}
	</style>
	
</head>
<body>
	<center>
		<table border="0" align="center" width="1100" height="50">
			<tr>
				<td align="center">
					<jsp:include page="menu.jsp"></jsp:include>
				</td>
			</tr>
		</table>
		<table id="table" border="0" align="center" width="1120" height="900" cellspacing="10">
			<tr>
				<td align="center" colspan="2" rowspan="3" width="770" height="20%" style="border-radius:1em;">
					<div style="overflow: auto; height: 440px;"><jsp:include page="../main_preview/buy_preview.jsp"></jsp:include></div>
				</td>
				<td align="center" width="350" height="160" style="border-radius:1em;">
					<jsp:include page="<%=log_jsp %>"></jsp:include>
				</td>
			</tr>
			<tr>
				<td valign="top" width="350" height="30%" style="border-radius:1em;">
					<jsp:include page="../main_preview/notice_preview.jsp"></jsp:include>
				</td>
			</tr>
			<tr>
				<td valign="top" rowspan="2" width="350" height="50%" style="border-radius:1em;">
					<jsp:include page="../main_preview/freeboard_preview.jsp"></jsp:include>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="2" width="770" height="350" style="border-radius:1em;">
					<jsp:include page="../main_preview/team_rank_preview.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>