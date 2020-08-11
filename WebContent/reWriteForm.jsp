<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Main.css">
<link rel="stylesheet" href="boardWrite.css">
</head>
<body>
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
   <h2>게시판 글쓰기</h2>
   <form action="reWriteProc" method="post">
   <table border="1">
   	<tr>
   		<td id="name">작성자</td>
   		<td>${sessionScope.id }</td>
   	</tr>
   	<tr>
   		<td id="name">글제목</td>
   		<td>[답변]<input type="text" name="subject"></td>
   	</tr>
   	<tr>
   		<td id="name">글내용</td>
   		<td><textarea rows="20" cols="100" name="content" style="resize: none;"></textarea>
   		</td>
   	</tr>
   </table>
  	<div id="sub">
  	<input type="hidden" value="${num }" name="num">
  	<input type="hidden" value="${reg }" name="reg">
  	<input type="hidden" value="${reg_step }" name="reg_step">
  	<input type="hidden" value="${reg_count }" name="reg_count">
    <input type="submit" value="글등록" id="sub1">
   <input type="button" id="sub1" onclick="location.href='BoardListProc'" value="취소">
   </div>
   </form>
   </center>
</body>
</html>