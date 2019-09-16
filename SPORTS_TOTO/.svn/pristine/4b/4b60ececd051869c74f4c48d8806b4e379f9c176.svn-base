<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.notice.*"%>
<%
	//한글변환
	request.setCharacterEncoding("EUC-KR");
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	String strNo=request.getParameter("no");
	String strPage=request.getParameter("page");
	
	NoticeVO vo=new NoticeVO();
	vo.setNoticeNo(Integer.parseInt(strNo));
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	//Database 전송
	NoticeDAO dao=new NoticeDAO(new DBConOracle());
	boolean bCheck=dao.noticeUpdate(vo);
	
	if(bCheck==true){
		response.sendRedirect("content.jsp?no="+strNo+"&page="+strPage);
	}else{
		%>
			<script type="text/javascript">
				alert("비밀번호가 틀립니다.");
				history.back();
			</script>
		<%
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>

</body>
</html>