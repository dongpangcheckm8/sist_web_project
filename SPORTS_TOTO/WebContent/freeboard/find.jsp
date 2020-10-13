<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sist.freeboard.*,java.util.*,java.text.*" %>
<%
	request.setCharacterEncoding("EUC-KR");
	String fs=(String)session.getAttribute("fs");
	String ss=(String)session.getAttribute("ss");
	
	BoardDAO dao=new BoardDAO(new DBConOracle());
	
	String strPage=request.getParameter("page");
	
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	
	List<BoardVO> list=dao.boardAllData(curpage);
	int totalpage=dao.boardFindTotalPage(fs,ss);
	List<BoardVO> find=dao.boardFindData(fs,ss,curpage);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/table.css">
	<script type="text/javascript">
		function write_click() {
			<%
			if(session.getAttribute("id")==null){
				%>
				alert("�α��� �ϼ���.");
				<%
			}else{
				%>
				location.href="insert.jsp";
				<%
			}
			%>
	    }
   </script>
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
		<%
			if(find.size()==0){
				%>
				<table id="table_content2" width="1100">
					<tr>
						<td align="center">
							<h2>�˻� ���</h2>
						</td>
					</tr>
					<tr height="600">
						<td align="center">
							<a>�˻��� ����� �����ϴ�.</a>
						</td>
					</tr>
				</table>
				<table id="table_content3" border="0" width="1100">
					<tr>
						<td align="center">
							<a href="list.jsp">���</a>&nbsp;
						</td>
					</tr>
				</table>
				<%
			}else{
				%>
				<table id="table_content2" width="1100" cellspacing="0">
					<tr>
						<td align="center" colspan="5" style="border-top-left-radius: 1em; border-top-right-radius: 1em;">
							<h2>�˻� ���</h2>
						</td>
					</tr>
					<tr>
						<td align="right">
							<button id="write" onclick="write_click()">�۾���</button>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
				<table id="table_content" width="1100" border="1" cellspacing="0">
					<tr>
						<th width="7%">��ȣ</th>
						<th width="58%">����</th>
						<th width="10%">�̸�</th>
						<th width="15%">�ۼ���</th>
						<th width="10%">��ȸ��</th>
					</tr>
					<%
						for(BoardVO vo:find){
					%>
					<tr height="28">
						<td width="7%" align="center"><%=vo.getPostingNo() %></td>
						<td width="58%">
							<a href="content.jsp?no=<%=vo.getPostingNo()%>&page=<%=curpage%>">
								<%=vo.getSubject() %>
							</a>
							<%	//����ǥ��
								Date date=new Date();
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
								String today=sdf.format(date);
								String dbday=sdf.format(vo.getRegdate());
								if(today.equals(dbday)){
									%>
									<sup><font color="red">new</font></sup>
									<%
								}
							%>
						</td>
						<td width="10%" align="center"><%=vo.getName() %></td>
						<td width="15%" align="center"><%=vo.getRegdate().toString() %></td>
						<td width="10%" align="center"><%=vo.getHit() %></td>
					</tr>
					<%
						}
					%>
				</table>
				<table id="table_content3" width="1100">
					<tr align="left">
						<td>
							&nbsp;<form method="post" action="find_ok.jsp">
								�˻�
								<select name="fs" style="border: 1px solid #3ADF00">
									<option value="subject">����</option>
									<option value="content">����</option>
									<option value="name">�̸�</option>
								</select>
								<input type="text" name="ss" size="40" style="border: 1px solid #3ADF00">
								<input type="submit" value="�˻�">
							</form>
						</td>
						<td align="right">
							<a href="find.jsp?page=<%=curpage>1 ? curpage-1:curpage%>">����</a>
							<%for(int i=1;i<=totalpage;i++){
								%>
								<%if(curpage<=5 && i<10){
									%>
									<a href="find.jsp?page=<%=i%>">
									<%=i %>
									</a>
									<%
								}else if(curpage-5<i && curpage+5>i){
									%>
									<a href="find.jsp?page=<%=i%>">
									<%=i %>
									</a>
									<%
								}%>
								<%
							}%>
							<a href="find.jsp?page=<%=curpage<totalpage ? curpage+1:curpage%>">����</a>
							&nbsp;&nbsp;
							<%=curpage %> page / <%=totalpage %> pages&nbsp;
						</td>
					</tr>
				</table>
				<%
			}
		%>
	</center>
</body>
</html>