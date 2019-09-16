<%@page import="com.sist.conn.DBConOracle"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.dao.*,java.util.*"%>
<%
	String id=(String)session.getAttribute("id");
	
	request.setCharacterEncoding("EUC-KR");
	String gender = request.getParameter("gender");
	String birthday=request.getParameter("birthday");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	
	MemberVO vo = new MemberVO();
	vo.setGender(gender);
	vo.setBirthday(birthday);
	vo.setAddress(address);
	vo.setPhone(phone);
	vo.setEmail(email);
	
	if(vo.getBirthday().length()!=8){
		%>
			<script type="text/javascript">
			alert("생년월일의 형식이 올바르지 않습니다.");
			history.back();
			</script>
		<%
		}else{
			
			MemberDAO dao=new MemberDAO(new DBConOracle());
			List<MemberVO> list=dao.memberUpdate(gender,birthday,address,phone,email,id);
			
			response.sendRedirect("mypage.jsp");
	}
%>