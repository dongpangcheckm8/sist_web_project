<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/tab.css">
	<script type="text/javascript">
		function ShowTabex(val){
			for (i=0; i<6; i++) {
				var tb = document.getElementById('tab_' + i);
				if (i != val) tb.style.display = "none";
				else tb.style.display = "block";
			}
		}
	</script>
</head>
<body>
	<center>
		<h2 align="left">&nbsp;&nbsp;<font color="#3E9D37">리그정보</font></h2>
		<div align="left" style="height:25px;">
			<div style="margin-top:0px; margin-left: 10px;">
				<span id="bord1" onclick="ShowTabex('0')" style="padding:5px; padding-top:0pt; cursor: pointer;"><img src="../image/england.png" width="12" height="15" border="0">EPL</span>
				<span id="bord2" onclick="ShowTabex('1')" style="padding:5px; padding-top:0pt; cursor: pointer;"><img src="../image/laliga.png" width="12" height="15" border="0">프리메라리가</span>
				<span id="bord2" onclick="ShowTabex('2')" style="padding:5px; padding-top:0pt; cursor: pointer;"><img src="../image/bundes.png" width="12" height="15" border="0">분데스리가</span>
				<span id="bord2" onclick="ShowTabex('3')" style="padding:5px; padding-top:0pt; cursor: pointer;"><img src="../image/italy.png" width="12" height="15" border="0">세리에A</span>
				<span id="bord2" onclick="ShowTabex('4')" style="padding:5px; padding-top:0pt; cursor: pointer;"><img src="../image/league1.png" width="12" height="15" border="0">리그앙</span>
				<span id="bord3" onclick="ShowTabex('5')" style="padding:5px; padding-top:0pt; cursor: pointer;"><img src="../image/eredi.png" width="12" height="15" border="0">에레디비지에</span>
			</div>
		</div>
		<div align="center" style="height:293px; border: 1px solid rgb(58,223,0); border-radius: 1em;">
			<div id="tab_0" style="width: 100%; display: block;">
				<jsp:include page="../main_preview/tab_epl.jsp"></jsp:include>
			</div>
			<div id="tab_1" style="width: 100%; display: none;">
				<jsp:include page="../main_preview/tab_laliga.jsp"></jsp:include>
			</div>
			<div id="tab_2" style="width: 100%; display: none;">
				<jsp:include page="../main_preview/tab_bunde.jsp"></jsp:include>
			</div>
			<div id="tab_3" style="width: 100%; display: none;">
				<jsp:include page="../main_preview/tab_seriea.jsp"></jsp:include>
			</div>
			<div id="tab_4" style="width: 100%; display: none;">
				<jsp:include page="../main_preview/tab_pleague.jsp"></jsp:include>
			</div>
			<div id="tab_5" style="width: 100%; display: none;">
				<jsp:include page="../main_preview/tab_eredi.jsp"></jsp:include>
			</div>
		</div>
	</center>
</body>
</html>