<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp"%>
<h1>삭제 여부확인</h1>
<form action='delete' method='post'>
${mno }번 회원님 정말 삭제 하시겠습니까?<br>
<input type='hidden' name='mno' value=${mno }>
<input type='submit' value='삭제'>
<input type='button' value='취소' onclick='window.location.href="list"'>
</form>
<%@include file="include/footer.jsp"%>