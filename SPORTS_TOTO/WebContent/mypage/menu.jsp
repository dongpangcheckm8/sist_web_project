<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
   String id=(String)session.getAttribute("id");

   String ck="";
   if((String)session.getAttribute("permission_level")==null){
      ck="2";
   }else{
      ck=(String)session.getAttribute("permission_level");
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
   <title>Insert title here</title>
   <link rel="stylesheet" type="text/css" href="../css/table.css?ver=5">
   <%if(ck.equals("2")){
         %>
         <style type="text/css">
            #admin{
               visibility: hidden;
            }
         </style>
         <%
      }
      %>
</head>
<body>
   <%
         if(id==null){
            %>
            <p><font size="5px">�α��� �ϼ���.</font></p>
            </br></br></br></br></br></br></br></br></br></br></br>
            <%
         }else{
            %>
            </br>
            <table id="table_member" width="270">
               <tr>
                  <td align="left">
                     &nbsp;&nbsp;&nbsp;<a href="mypage.jsp?mode=0">ȸ������</a>
                  </td>
               </tr>
               <tr>
                  <td align="left">
                     &nbsp;&nbsp;&nbsp;<a href="mypage.jsp?mode=1">ȸ������ ����</a>
                  </td>
               </tr>
               <tr>
                  <td align="left">
                     &nbsp;&nbsp;&nbsp;<a href="mypage.jsp?mode=2">��������</a>
                  </td>
               </tr>
               <tr>
                  <td align="left">
                     &nbsp;&nbsp;&nbsp;<a href="mypage.jsp?mode=3">������</a>
                  </td>
               </tr>
            </table>
            </br>
            </br>
            <table id="table_member" width="270">
            	<tr>
            	  <p id="admin">������ ����</p>
                  <td id="admin" align="left">
                     &nbsp;&nbsp;&nbsp;<a href="mypage.jsp?mode=4">���� ����</a>
                  </td>
               </tr>
            </table>
            </br>
            </br>
            <%
         }
      %>
      <table align="center">
      	<tr>
      		<td>
      			<img alt="" src="../image/hou.jpg">
      		</td>
      	</tr>
      </table>
</body>
</html>