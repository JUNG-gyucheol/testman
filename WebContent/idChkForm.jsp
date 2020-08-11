<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var httpRequest = new XMLHttpRequest();
	function pValue() {
		document.getElementById("userId").value = opener.document.getElementById("id").value
	}
	function idCheck() {
		var id = document.getElementById("userId").value;
		
		var param = "id=" + id;
		httpRequest.onreadystatechange = callback;
		httpRequest.open("POST","idCheckProc",true);
		httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		httpRequest.send(param);
	}
	function callback() {
		if(httpRequest.readyState == 4){
			
			var resultText = httpRequest.responseText;
			console.log(resultText);
			if(resultText == 0){
				alert('사용 할 수없는 아이디 입니다.');
				document.getElementById("cancelBtn").style.visibility='visible';
				document.getElementById("useBtn").style.visibility='hidden';
				document.getElementById('msg').innerHTML = "";
			} else if (resultText == 1){
				document.getElementById("cancelBtn").style.visibility='hidden';
				document.getElementById("useBtn").style.visibility='visible';
				document.getElementById('msg').innerHTML = "사용 가능한 아이디입니다.";
				opener.document.getElementById("idChk").value = "idCheck";
			}
		} 
	}
	
	function sendCheckValue() {
		if(opener.document.getElementById('idChk').value != "idCheck"){
			alert('중복체크해주세요.');
		} else{
		
		opener.document.getElementById("idChk").value = "idCheck";
		opener.document.getElementById("id").value = document.getElementById("userId").value;
		
		if(opener != null){
			opener.chkForm = null;
			self.close();
			}
		}
	}
</script>
</head>
<body onload="pValue()">
<div id="wrap">
	<br>
	<b><font size="4" color="gray">아이디 중복체크</font></b>
	<hr size="1" width="460">
	<br>
	<div id="chk">
		<form id="checkForm">
			<input type="text" name="idinput" id="userId">
			<input type="button" value="중복확인" onclick="idCheck()">
		</form>	
		<div id="msg"></div>
			<br>
			<input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">&nbsp;&nbsp;
			<input id="cancelBtn" type="button" value="취소" onclick="window.close()"> 
	</div>
</div>
		
</body>
</html>