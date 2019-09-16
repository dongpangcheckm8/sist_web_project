<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	<center>
		<form method="post" action="logout_ok.jsp">
			<table width="200">
				<tr>
					<td align="left">
						<%=session.getAttribute("name")%>님
					</td>
				</tr>
				<tr>
					<td align="left">로그인 되었습니다!!</td>
				</tr>
				
				<tr>
					<td align="right">
						<input type="submit" value="로그아웃" style="background-color: white; border-radius: 0.5em;">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>