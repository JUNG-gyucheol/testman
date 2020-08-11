/**
 * 
 */

var boardRequest = new XMLHttpRequest();
function board(){
	
	boardRequest.onreadystatechange = callback;
	boardRequest.open("POST", "BoardListProc",true);
	boardRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoeded');
	boardRequest.send(null);
}
function callback() {
	if(boardRequest.readyState == 4){
		
		var resultText = boardRequest.responseText;
		if(resultText == 0){
			alert('로그인을 해주세요.');
		} else{
			alert("ddkssu아");
		}
	}
}