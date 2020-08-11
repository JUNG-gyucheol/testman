<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="Main.css">
    <link rel="stylesheet" href="login.css">
    <script type="text/javascript" language="javascript" src="ajax.js"></script>
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
            <li><a onclick="board()" href="#">게시판</a></li>
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
    <div class="container">
    <form action="MemberLoginProc" method="post">
        <h2>로그인 화면</h2>
        <input type="text" name="id" placeholder="아이디"><br>
        <input type="password" name="pass" placeholder="패스워드">
       <input class="loginvar" type="submit" value="로그인">
        <div id="join"><a href="join.jsp">회원가입</a></div>
        <div><a href="main.jsp">돌아가기</a></div>
       </form>
    </div>
</html>