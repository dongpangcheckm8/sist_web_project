<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.freeboard.*"%>
<%
	String strNo=request.getParameter("no");
	String strPage=request.getParameter("page");
	
	//데이터베이스 연동
	BoardDAO dao=new BoardDAO(new DBConOracle());
	BoardVO vo=dao.boardContentData(Integer.parseInt(strNo));
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css?ver=2">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		var i=0;
		$(function(){
			$('#delete').click(function(){
				if(i==0){
					$('#del').show();
					i=1;
				}else{
					$('#del').hide();
					i=0;
				}
			});
		});
		
		function delbtn() {
			location.href="delete.jsp?no=<%=vo.getPostingNo()%>";
	    }
	</script>
	<% if(session.getAttribute("id")==null){
      %>
      <style type="text/css">
         #fb{
            visibility: hidden;
         }
         #delete{
         	visibility: hidden;
         }
      </style>
      <%
   }
   %>
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
		<table id="table_content2" width="1100" border="1" cellspacing="0">
			<tr>
			 <td colspan="8" align="center" style="border-top-left-radius: 1em; border-top-right-radius: 1em;">
			 	<h2>내용 보기</h2>
			 </td>
			</tr>
			<tr>
				<th width="10%">번호</th>
				<td width="10%" align="center"><%=vo.getPostingNo() %></td>
				<th width="10%">이름</th>
				<td width="10%" align="center"><%=vo.getName() %></td>
				<th width="10%">조회수</th>
				<td width="10%" align="center"><%=vo.getHit() %></td>
				<th width="10%">작성일</th>
				<td width="30%" align="center"><%=vo.getRegdate().toString() %></td>
			</tr>
			<tr>
				<th width="10%">제목</th>
				<td colspan="7" align="left"><%=vo.getSubject() %></td>
			</tr>
			<tr>
				<th width="10%">내용</th>
				<td colspan="7" valign="top" align="left" height="500">
				<%=vo.getContent() %>
				</td>
			</tr>
		</table>
		<form method="post" action="delete.jsp?no=<%=strNo%>&page=<%=strPage%>">
			<table id="table_content3" width="1100">
				<tr>
					<td align="right">
						<a id="fb" href="update.jsp?no=<%=strNo%>&page=<%=strPage%>">수정</a>&nbsp;
						<a id="delete" href="#">삭제</a>&nbsp;
						<a href="list.jsp?page=<%=strPage%>">목록</a>&nbsp;&nbsp;
					</td>
				</tr>
				<tr id="del" style="display: none">
					<td align="right">
						비밀번호:
						<input type="password" name="pwd" size="10">
						<input type="submit" value="삭제">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>