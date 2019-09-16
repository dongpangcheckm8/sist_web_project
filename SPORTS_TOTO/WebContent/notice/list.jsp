<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sist.notice.*,java.util.*,java.text.*" %>
<%
   String strPage=request.getParameter("page");
   
   if(strPage==null)
      strPage="1";
   int curpage=Integer.parseInt(strPage);

   NoticeDAO dao=new NoticeDAO(new DBConOracle());
   List<NoticeVO> list=dao.noticeAllData(curpage);
   int totalpage=dao.noticeTotalPage();
   
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
   <%if(ck.equals("2")){
      %>
      <style type="text/css">
         #write{
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
      <table id="table_content2" width="1100">
			<tr>
				<td align="center" colspan="5" style="border-top-left-radius: 1em; border-top-right-radius: 1em;">
					<h2>공지사항</h2>
				</td>
			</tr>
			<tr>
				<td align="right">
					<button id="write" onclick="write_click()"><a href="insert.jsp">글쓰기</a></button>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
      <table id="table_content" width="1100" border="1" cellpadding="0">
         <tr>
            <th width="7%">번호</th>
            <th width="58%">제목</th>
            <th width="10%">이름</th>
            <th width="15%">작성일</th>
            <th width="10%">조회수</th>
         </tr>
         <%
            for(NoticeVO vo:list){
         %>
         <tr height="28">
            <td width="7%" align="center"><%=vo.getNoticeNo() %></td>
            <td width="58%">
               <a href="content.jsp?no=<%=vo.getNoticeNo()%>&page=<%=curpage%>">
                  <%=vo.getSubject() %>
               </a>
               <%   //새글표시
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
               	   검색
                  <select name="fs" style="border: 1px solid #3ADF00">
                     <option value="subject">제목</option>
                     <option value="content">내용</option>
                     <option value="name">이름</option>
                  </select>
                  <input type="text" name="ss" size="40" style="border: 1px solid #3ADF00">
                  <input type="submit" value="검색">
               </form>
            </td>
            <td align="right">
				<a href="list.jsp?page=<%=curpage>1 ? curpage-1:curpage%>">이전</a>
				<%for(int i=1;i<=totalpage;i++){
					%>
					<%if(curpage<=5 && i<10){
						%>
						<a href="list.jsp?page=<%=i%>">
						<%=i %>
						</a>
						<%
					}else if(curpage-5<i && curpage+5>i){
						%>
						<a href="list.jsp?page=<%=i%>">
						<%=i %>
						</a>
						<%
					}%>
					<%
				}%>
				<a href="list.jsp?page=<%=curpage<totalpage ? curpage+1:curpage%>">다음</a>
				&nbsp;&nbsp;
				<%=curpage %> page / <%=totalpage %> pages&nbsp;
			</td>
         </tr>
      </table>
   </center>
</body>
</html>