<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h1>회원수정</h1>
<form action='update' method='post'>
번호 : <input type='text' name='mno' value='${member.mno }' readonly><br>
이름 : <input type='text' name='mname' value='${member.mname }'><br>
이메일 : <input type='text' name='email' value='${member.email }'><br>
가입일 : <fmt:formatDate pattern="yyyy-MM-dd" value="${member.cre_date}" /><br>
암호 : <input type='password' name='pwd' value='${member.pwd }'><br>
<input type='submit' value='수정'>
<input type='reset' value='초기화'>
<input type='button' value='리스트로' onclick='location.href="list"'>
<input type='button' value='탈퇴' onclick='location.href="delete?mno=${member.mno }"'>
</form>
<jsp:include page="./include/footer.jsp" />
