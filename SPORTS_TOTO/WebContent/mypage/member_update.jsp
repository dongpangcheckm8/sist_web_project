<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.dao.*,java.util.*"%>
<%
   String id=(String)session.getAttribute("id");
   
   MemberDAO dao=new MemberDAO(new DBConOracle());
   List<MemberVO> list=dao.memberAllData(id);
   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css?ver=2">
</head>
<body>
	<center>
		<form method="post" action="member_update_ok.jsp">
			<table id="table_member" border="0" align="center" width="700" height="650" cellspacing="0">
      			<h3>회원정보 수정</h3>
      			<%
		            for(MemberVO vo:list){
		        %>
      			<tr>
		            <th width="25%">ID</th> 
		            <td>&nbsp;&nbsp;&nbsp;<%=vo.getId() %></td>
		        </tr>
				<tr>
					<th width="25%">이름</th>
          			<td>&nbsp;&nbsp;&nbsp;<%=vo.getName() %></td>
				</tr>
				<tr>
					<th width="25%">성별</th>
					<td>
						&nbsp;
						<input type="radio" name="gender" value="남자" checked="checked">남자
						<input type="radio" name="gender" value="여자">여자
					</td>
				</tr>
				<tr>
					<th width="25%">생일</th>
					<td><jsp:include page="../member/calendar.jsp"></jsp:include></td>
				</tr>
				<tr>
					<th width="25%">주소</th>
					<td><input type="text" name="address" value="<%=vo.getAddress()%>"></td>
				</tr>
				<tr>
					<th width="25%">전화번호</th>
					<td><input type="text" name="phone" value="<%=vo.getPhone()%>"></td>
				</tr>
				<tr>
					<th width="25%">이메일 주소</th>
					<td><input type="text" name="email" value="<%=vo.getEmail()%>"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정">
						<input type="button" value="취소" onclick="javascripqt:history.back()">
					</td>
				</tr>
			</table>
				<%
	          	  }
	        	%>
		</form>
	</center>
</body>
</html>