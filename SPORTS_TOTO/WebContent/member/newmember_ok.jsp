<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%@page import="com.sist.dao.MemberDAO"%>
<%@page import="com.sist.dao.MemberVO"%>
<%
	request.setCharacterEncoding("UTF-8");

	String name=request.getParameter("name");
	String id=request.getParameter("id");
	String password=request.getParameter("password");
	String birthday=request.getParameter("birthday");
	String address=request.getParameter("address");
	String gender=request.getParameter("gender");
	String email=request.getParameter("email");
	String phone=request.getParameter("phone");
    
    MemberVO vo=new MemberVO();
	vo.setName(name);
	vo.setId(id);
	vo.setPwd(password);
	vo.setBirthday(birthday);
	vo.setAddress(address);
	vo.setGender(gender);
	vo.setEmail(email);
	vo.setPhone(phone);
	
	if(vo.getBirthday().length()!=8){
		%>
		<script type="text/javascript">
			alert("생년월일의 형식이 올바르지 않습니다.");
			history.back();
		</script>
		<%
	}else{
		//DataBase 전송
		MemberDAO dao=new MemberDAO(new DBConOracle());
		dao.memberInsert(vo);
	}
%>
<html>
<head>
	<title>Close Window Timer</title>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
	<script language="JavaScript">
		function closeWin(thetime) {
			setTimeout("window.close()", thetime); //1000 은 1초를 의미합니다.
		}
	</script>
</head> 
<body onLoad="closeWin('1000')">
	회원가입 처리 중입니다.<br>
</body>
</html>