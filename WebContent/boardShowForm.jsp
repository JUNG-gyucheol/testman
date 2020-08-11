<!-- sadsadadsaㅅㄷㄴㅅ텟, -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Main.css">
<link rel="stylesheet" href="boardWrite.css">
<script type="text/javascript">

	var deleteRequest = new XMLHttpRequest();
	function deleteFnc(){
		var con = confirm('정말로 삭제하시겠습니까?');
		if(con == true){
			var num = document.getElementById("num").value;
			deleteRequest.onreadystatechange = callback;			
			deleteRequest.open("POST", "boardDeleteProc?num="+num ,true);
			deleteRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			deleteRequest.send(null);
		}
	}
	function callback() {
		if(deleteRequest.readyState ==  4 ){
			
			var resultText = deleteRequest.responseText;
			if(resultText == 0){
				alert('권한이 없습니다!!!');
			}else{
				alert('삭제완료했습니다!!!');
				location.href='BoardListProc';
			}
		}
	}
	var commentRequest = new XMLHttpRequest();
		function com() {
			var comment = document.getElementById("comment").value;
			var num = document.getElementById("num").value;
			commentRequest.onreadystatechange = comm;
			commentRequest.open("POST", "CommentProc?comment="+comment+"&num="+num, true);
			commentRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			commentRequest.send(null);
		}
		function comm() {
			if(commentRequest.readyState == 4){
				
				var result = commentRequest.responseText;
				if(result == 0){
					alert('등록이 되지 않으셨습니다.');
				} else{
					alert('등록을 완료하였습니다.');
					document.getElementById("comment").value = "";
					window.onload();
				}
			}
		}
window.onload = function(){
	var getComment = new XMLHttpRequest();
	 function getCom() {
		 var boardNum = document.getElementById('num');
		 getComment.onreadystatechange = getAll;
		 getComment.open("POST", "CommentGetProc?num="+boardNum.value, true);
		 getComment.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		 getComment.send(null);
	}
	 
	function getAll(){
		 if(getComment.readyState == 4){
			
			var result = getComment.responseText;
			console.log(result);
			var val = document.getElementById('com');
			val.innerHTML = result;
		 }
	}
	getCom();
}

</script>
<style>
        .parent{
            position: relative;
            text-align: left;
            width : 800px;
        }
        .sub{
            position: absolute;
            top: 4px;
            left : 90%;
        }
    </style>
</head>
<body>
    <header>  
        <h1><a href="main.jsp">MyProject one</a></h1>    
    </header>
    <nav>
        <ul class="menu">
            <li class="menu1">소개
                <ul>
                    <li>자기소개</li>
                </ul>
            </li>
            <li class="menu2">여행
                <ul>
                    <li>일본여행</li>
                    <li>필리핀유학</li>
                    <li>국내여행</li>
                </ul>
            </li >
            <li classs="menu3">브랜드
                <ul>
                   <li>BAPE</li>
                   <li>Niddless</li>
                   <li>Nike</li>
                   <li>Undercover</li>     
                </ul> 
            </li>
            <li><a href="BoardListProc">게시판</a></li>
        </ul>
        <div class=login>
        <c:if test="${sessionScope.id == null }">
            <a href="login.jsp">로그인</a>&nbsp;&nbsp;
            <a href="join.jsp">회원가입</a>
        </c:if>
        <c:if test="${sessionScope.id != null }">
            ${sessionScope.id} 님 반갑습니다!!! &nbsp;&nbsp;
            <button onclick="location.href='MemberLogoutProc'">로그아웃</button>
        </c:if>
        </div>
    </nav>
    <center class="container">
   <h2>게시판 보기</h2>
   <table border="1">
   <c:set var="bean" value="${bean }"/>
   	<tr>
   		<td id="name">작성자</td>
   		<td>${bean.userID}</td>
   	</tr>
   	<tr>
   		<td id="name">조회수</td>
   		<td>${bean.count }</td>
   	</tr>
   	<tr>
   		<td id="name">작성일자</td>
   		<td>${bean.date }</td>
   	</tr>
   	<tr>
   		<td id="name">글제목</td>
   		<td>${bean.subject }</td>
   	</tr>
   	<tr>
   		<td id="name">글내용</td>
   		<td>${bean.content }
   		</td>
   	</tr>
   </table>
   <a>sdsadsadsadHell어렵당!!</a>
  	<div id="sub">
  	<input type="hidden" value="${bean.num }" id="num">
  	<input type="button" value="답글쓰기" id="sub1" onclick="location.href='reWriteGetFormProc?num=${bean.num }'">
   <input type="button" value="수정하기" id="sub1" onclick="location.href='boardUpdateProc?num=${bean.num}'">
   <input type="button" id="sub1" onclick="location.href='BoardListProc'" value="목록">
   <input type="button" value="삭제하기" id="sub1" onclick="deleteFnc()">
   </div>
 	<h4>댓글</h4>
 	<textarea rows="8" cols="70" style="resize:none;" id="comment"></textarea>
 	<button width="100px" onclick="com()">등록</button><br>
 	<div id="com"></div>
   </center>
</body>
</html>