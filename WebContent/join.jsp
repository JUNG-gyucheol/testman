<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="Main.css">
    <link rel="stylesheet" href="join.css">
    <script type="text/javascript" language="javascript" src="join.js"></script>
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
    <center>
    <div class="container">
        <h2>회원가입</h2>
    <form action="MemberJoinProc" method="post" onsubmit="return CheckForm()" id="MemberForm">
        <table class="joinbar" >
        <tr>
        <td class="name">이름</td>
        <td class="in"><input type="text" name="name" placeholder="이름" id="name"></td>
        </tr>
        <tr>
        <td class="name">아이디</td>
        <td class="in"><input type="text" name="id" placeholder="아이디" id="id" onkeydown="inputIdChk()">&nbsp;&nbsp;
        <input type="button" value="중복확인" onclick="idCheck()">
        <input type="hidden" name="idDuplication" value="idUncheck" id="idChk">
        </td>
         </tr>
        <tr>
        <td class="name">패스워드</td>
        <td class="in"><input type="password" name="pass1" placeholder="패스워드" id="pass1"></td>
        </tr>
        <tr>
        <td class="name">패스워드</td>
        <td class="in"><input type="password" name="pass2" placeholder="패스워드 확인" id="pass2"></td>
        </tr>
        <tr>
        <td class="name">휴대폰번호</td>
          <td class="in"><select name="phone">
                    <option value="KT">KT</option>
                    <option value="SKT">SKT</option>
                    <option valie="LGU">LGU</option>
            </select>
                &nbsp;&nbsp;<input type="tel" name="tel" placeholder="휴대폰번호" id="tel">
            </td>
        </tr>
        <tr>
        <td class="name">이메일</td> 
        <td class="in"><input type="email" name="email" placeholder="이메일" id="email"></td>
        </tr>
        <tr>
        <td class="name">성별 </td>
        <td class="in"><input type="radio" name="sex" value="man" checked="checked">남 &nbsp;
         <input type="radio" name="sex" value="woman">여자 
        </td>
        </tr>
        </table>
        <input type="submit" value="회원가입" >&nbsp;&nbsp;
        <input type="button" value="취소" onclick="location.href='main.jsp'">      
    </form>  
    </div>
    </center>
</body>
</html>