<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.notice.*"%>
<%
	String strNo=request.getParameter("no");
	String strPage=request.getParameter("page");
	
	//DB연동
	NoticeDAO dao=new NoticeDAO(new DBConOracle());
	NoticeVO vo=dao.noticeUpdateData(Integer.parseInt(strNo));
	
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
		<form method="post" action="update_ok.jsp">
			<table id="table_content2" width="1100" border="1" cellspacing="0">
				<tr>
					<td colspan="2" align="center" style="border-top-left-radius: 1em; border-top-right-radius: 1em;">
						<h2>수정하기</h2>
					</td>
				</tr>
				<tr>
					<th width="10%" align="right">이름</th>
					<td width="90%" align="left">
						<%=name %>
						<input type="hidden" name="name" value="<%=name %>"></input>
						<input type="hidden" name="no" value="<%=strNo%>">
						<input type="hidden" name="page" value="<%=strPage%>">
					</td>
				</tr>
				<tr>
					<th width="10%" align="right">제목</th>
					<td width="90%" align="left">
						<input type="text" name="subject" size="50" value="<%=vo.getSubject()%>">
					</td>
				</tr>
				<tr>
					<th width="10%" align="right">내용</th>
					<td width="90%" align="left">
						<textarea rows="30" cols="140" name="content"><%=vo.getContent() %></textarea>
					</td>
				</tr>
				<tr>
					<th width="10%" align="right">비밀번호</th>
					<td width="90%" align="left">
						<input type="password" name="pwd" size="5">
					</td>
				</tr>
			</table>
			<table id="table_content3" width="1100">
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정">
						<input type="button" value="취소" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>