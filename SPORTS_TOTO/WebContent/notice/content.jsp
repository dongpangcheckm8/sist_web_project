<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.notice.*"%>
<%
   String strNo=request.getParameter("no");
   String strPage=request.getParameter("page");
   
   //데이터베이스 연동
   NoticeDAO dao=new NoticeDAO(new DBConOracle());
   NoticeVO vo=dao.noticeContentData(Integer.parseInt(strNo));
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
   <link rel="stylesheet" type="text/css" href="../css/table.css">
   <% if(ck.equals("2")){
      %>
      <style type="text/css">
		#update{
			visibility: hidden;
		}
		#delete{
			visibility: hidden;
		}
      </style>
      <%
   }
   %>
   <script type="text/javascript">
		function deleteb() {
			alert("삭제가 완료 되었습니다.");
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
     <table id="table_content2" width="1100" border="1" cellspacing="0">
		<tr>
			<td colspan="8" align="center" style="border-top-left-radius: 1em; border-top-right-radius: 1em;">
		 	<h2>공지 내용</h2>
		 </td>
		</tr>
         <tr>
            <th width="10%">번호</th>
            <td width="10%" align="center"><%=vo.getNoticeNo() %></td>
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
      <table id="table_content3" width="1100">
         <tr>
            <td align="right">
               <a id="update" href="update.jsp?no=<%=strNo%>&page=<%=strPage%>">수정</a>&nbsp;
               <a id="delete" onclick="deleteb()" href="delete.jsp?no=<%=vo.getNoticeNo()%>">삭제</a>&nbsp;
               <a href="list.jsp?page=<%=strPage%>">목록</a>&nbsp;&nbsp;
            </td>
         </tr>
      </table>
   </center>
</body>
</html>