<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String name=(String)session.getAttribute("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css">
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
		<form method="post" action="insert_ok.jsp">
			<table id="table_content2" width="1100" border="1" cellspacing="0">
				<tr>
					<td align="center" colspan="2" style="border-top-left-radius: 1em; border-top-right-radius:1em;">
						<h2>공지사항 작성</h2>
					</td>
				</tr>
				<tr>
					<th width="10%">이름</th>
					<td width="90%">
						<%=name %>
						<input type="hidden" name="name" value="<%=name %>"></input>
					</td>
				</tr>
				<tr>
					<th width="10%">제목</th>
					<td width="90%">
						<input type="text" name="subject" size="50" id="name">
					</td>
				</tr>
				<tr>
					<th width="10%">내용</th>
					<td width="90%" >
						<textarea rows="30" cols="140" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<th width="10%">비밀번호</th>
					<td width="90%">
						<input type="password" size="5" name="pwd">
					</td>
				</tr>
			</table>
			<table id="table_content3" width="1100" >
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="작성 완료">
						<input type="button" value="취소" onclick="javascripqt:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>