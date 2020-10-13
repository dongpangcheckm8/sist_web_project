<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.freeboard.*"%>
<%
	String strNo=request.getParameter("no");
	String strPage=request.getParameter("page");
	
	//�����ͺ��̽� ����
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
			 	<h2>���� ����</h2>
			 </td>
			</tr>
			<tr>
				<th width="10%">��ȣ</th>
				<td width="10%" align="center"><%=vo.getPostingNo() %></td>
				<th width="10%">�̸�</th>
				<td width="10%" align="center"><%=vo.getName() %></td>
				<th width="10%">��ȸ��</th>
				<td width="10%" align="center"><%=vo.getHit() %></td>
				<th width="10%">�ۼ���</th>
				<td width="30%" align="center"><%=vo.getRegdate().toString() %></td>
			</tr>
			<tr>
				<th width="10%">����</th>
				<td colspan="7" align="left"><%=vo.getSubject() %></td>
			</tr>
			<tr>
				<th width="10%">����</th>
				<td colspan="7" valign="top" align="left" height="500">
				<%=vo.getContent() %>
				</td>
			</tr>
		</table>
		<form method="post" action="delete.jsp?no=<%=strNo%>&page=<%=strPage%>">
			<table id="table_content3" width="1100">
				<tr>
					<td align="right">
						<a id="fb" href="update.jsp?no=<%=strNo%>&page=<%=strPage%>">����</a>&nbsp;
						<a id="delete" href="#">����</a>&nbsp;
						<a href="list.jsp?page=<%=strPage%>">���</a>&nbsp;&nbsp;
					</td>
				</tr>
				<tr id="del" style="display: none">
					<td align="right">
						��й�ȣ:
						<input type="password" name="pwd" size="10">
						<input type="submit" value="����">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>