<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="include/header.jsp" />
<h1>회원 등록</h1><br>
<form action='register' method='post'>
이름 : <input type='text' name='mname'><br>
이메일 : <input type='text' name='email'><br>
암호 : <input type='password' name='pwd'><br>
<input type='submit' value='추가'>
<input type='reset' value='취소'>
<input type='button' value='리스트로' onclick='location.href="list"'>
</form>
<jsp:include page="include/footer.jsp" />

<!-- dto에 있는 필드명과 input type=name 이 같도록 설정.. Spring에 값 달라고 요청하면  Mapping해서 넣어줌 -->