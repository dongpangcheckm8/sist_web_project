<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.freeboard.*, java.util.*"%>
<%
	String strPage=request.getParameter("page");
	BoardDAO dao=new BoardDAO(new DBConOracle());
	List<BoardVO> list=dao.freeboard_main();
	if(strPage==null)
	strPage="1";
	int curpage=Integer.parseInt(strPage);
	int totalpage=dao.boardTotalPage();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css">
</head>
<body>
	<h2 id="table_menu">&nbsp;&nbsp;<a href="../freeboard/list.jsp">자유게시판</a><font size="2"><a style="margin-left: 200px" href="../freeboard/list.jsp">more</a></font></h2>
	<center>
		<table id="table_content" border="1" cellspacing="0" width="375" height="100%">
			<tr height="20">
				<th width="10">NO</th>
				<th width="20%">이름</th>
				<th width="40%">제목</th>
				<th width="30%">작성일</th>
			</tr>
			<%
				for(BoardVO vo:list) {
					%>
					<tr>
						<td width="10%" align="center"><%=vo.getPostingNo() %></td>
						<td width="20%" align="center"><%=vo.getName() %></td>
						<td width="40%">
						<a href="../freeboard/content.jsp?no=<%=vo.getPostingNo()%>&page=<%=curpage%>">
						<%
						if(vo.getSubject().length()>10){
							String subjecttext=vo.getSubject().substring(0, 8);
							out.println(subjecttext+".."); }
						else{
							out.print(vo.getSubject());
						}
						%>
					</a></td>
						<td width="30%" align="center"><%=vo.getRegdate() %></td>
					</tr>
					<%
				}
			%>
		</table>
	</center>
</body>
</html>