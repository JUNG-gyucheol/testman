<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Main.css">
<link rel="stylesheet" href="board.css">
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
    <h2>게시판 목록</h2>
    <table border="1">
    	<tr>
    		<td id="num">번호</td>
    		<td id="subject">글제목</td>
    		<td id="writer">작성자</td>
    		<td id="date">작성일</td>
    		<td id="count">조회</td>
    	</tr>
    	<tr>
    <c:set var="number" value="${number }"/>
    	<c:forEach var="list" items="${list }"  >
    		<td id="num">${number }</td>
    		<td id="subject">
    		<c:if test="${list.reg_step > 1 }">
    		<c:forEach var="j" begin="1" end="${(list.reg_step -1)*5  }">
    			&nbsp;
    		</c:forEach>
    		</c:if>
    		<a href="showFormProc?num=${list.num}">${list.subject }</a></td>
    		<td id="writer">${list.userID }</td>
    		<td id="date">${list.date }</td>
    		<td id="count">${list.count }</td>
    	</tr>
    <c:set var="number" value="${number -1}" />
    	</c:forEach>
    </table>
   	<button id="write" onclick="location.href='boardWrite.jsp'">글쓰기</button><br>
   	<c:if test="${count > 0 }">
   	<c:set var="pageCount" value="${count / pageSize + (count%pageSize == 0 ? 0 : 1)}" />
   	<c:set var="startPage" value="${1 }"/>
   	<c:if test="${currentPage % 10 != 0 }">
   		<fmt:parseNumber var="result" value="${currentPage/10}" integerOnly="true"/>
   		<c:set var="startPage" value="${result * 10 + 1 }"/>
   	</c:if>
   	<c:if test="${currentPage % 10 == 0 }">
   		<c:set var="startPage" value="${(result-1)*10+1 }"/>
   	</c:if>
   	<c:set var="pageBlock" value="${10 }" />
   	<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
   	<c:if test="${endPage > pageCount }">
   		<c:set var="endPage" value="${pageCount }"/>
   	</c:if>
   	<c:if test="${startPage > 10 }">
   		<a href="BoardListProc?pageNum=${startPage-10 }">[이전]</a>
   	</c:if>
  	<c:forEach	var ="i" begin="${startPage }" end="${endPage}">
  		<a href="BoardListProc?pageNum=${i }">[${i }] </a>
  	</c:forEach>
  	<c:if test="${endPage < pageCount }">
   		<a href="BoardListProc?pageNum=${startPage+10 }">[다음]</a>
   	</c:if>
   	</c:if>
    </center>
</body>
</html>