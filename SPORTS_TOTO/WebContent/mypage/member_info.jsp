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
      <%if(id==null){
         
      }else{
      %>
      <table id="table_member" border="0" align="center" width="700" height="650" cellspacing="0">
      	<h3>ȸ������</h3>
         <%
            for(MemberVO vo:list){
         %>
         <tr>
            <th width="25%">ID</th> 
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getId() %></td>
         </tr>
         <tr>
            <th width="25%">�̸�</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getName() %></td>
         </tr>
         <tr>
            <th width="25%">����</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getGender() %></td>
         </tr>
         <tr>
            <th width="25%">����</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getBirthday() %></td>
         </tr>
         <tr>
            <th width="25%">�ּ�</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getAddress() %></td>
         </tr>
         <tr>
            <th width="25%">��ȭ��ȣ</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getPhone() %></td>
         </tr>
         <tr>
            <th width="25%">�̸��� �ּ�</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getEmail() %></td>
         </tr>
         <tr>
            <th width="25%">���� ��¥</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getRegdate() %></td>
         </tr>
         <tr>
            <th width="25%">���� �Ӵ�</th>
            <td>&nbsp;&nbsp;&nbsp;<%=vo.getGamemoney() %></td>
         </tr>
         <%
            }
         %>
      </table>
      <%   
      }
      %>
   </center>
</body>
</html>