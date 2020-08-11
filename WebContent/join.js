/**
 * 
 */

function CheckForm(){
    var name = document.getElementById('name');
    var id = document.getElementById('id');
    var idChk = document.getElementById('idChk');
    var pw = document.getElementById('pass1');
    var pw1 = document.getElementById('pass2');
    var tel = document.getElementById('tel');
    var email = document.getElementById('email');
    
    var reg1 = /^[가-힣]{2,5}$/;
    var result = reg1.test(name.value);
    if(!(result)){
        alert('이름은 2자~5자 이내로 작성하시오.');
        name.value = "";
        return false;
    }
    var reg2 = /^[a-zA-Z0-9]{4,12}$/;
    var result2 = reg2.test(id.value);
    if(!(result2)){
        alert('4~12자리 입력해주세요.');
        id.value="";
        return false;
    }
    
     if(idChk.value != "idCheck"){
    	 alert('아이디 중복체크를 해주세요');
    	 return false;
     }
    
    if(pw.value.length == 0){
        alert('비밀번호를 입력해주세요.');
        pw.focus();
        return false;
    }
    var reg3 = /^[a-zA-Z0-9]{8,12}$/;
    var result3 = reg3.test(pw.value);
    if(!(result3)){
        alert('8~12자리 입력해주세요.');
        pw.focus();
        return false;
    }
    if(pw1.value.length == 0){
        alert('패스워드확인을 하지않으셨습니다.');
        pw1.focus();
        return false;
    }
    if(pw.value != pw1.value){
        alert('패스워드가 일치하지 않습니다.');
        pw1.focus();
        return false;
    }
    if(tel.value.length == 0){
        alert('휴대폰번호를 입력하지 않으셨습니다.');
        tel.focus();
        return false;
    }
    var reg4 =/^[0-9]{10,12}$/;
    var result4 = reg4.test(tel.value);
    if(!(result4)){
        alert('-없이 입력해주세요.');
        tel.focus();
        tel.value="";
        return false;
    }
    
    if(email.value.length == 0){
        alert('이메일을 입력하지 않으셨습니다.');
        email.focus();
        return false;
    }
}

function idCheck() {
//	alert('아이디중복확인');
//	
//	var url= "idCheckProc?id=" + document.getElementById('id').value;
//	
//	window.open(url,"id check","width=500,height=500");
	
	window.name = "parentForm";
	var url = "idChkForm.jsp"
	window.open(url,"chkForm","width=500, height=300");
}

function inputIdChk(){
	document.getElementById('idChk').value = "idUncheck";
}
	

