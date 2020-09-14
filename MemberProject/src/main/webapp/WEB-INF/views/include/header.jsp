<%@page import="com.hk.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Member member = (Member) session.getAttribute("member");
%>
<!DOCTYPE html>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>hk Member</title>

  <!-- Custom fonts for this template -->
  <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

<div
	style="background-color: #00008b; color: #ffffff; height: 30px; padding: 5px;">
	SPMP(Simple Project Management System)
	<%if (member != null) {%>
	<span style="float: right;"> 
	${member.mname } 
	<a style="color: white;" href="/member/logout">로그아웃</a>
	</span>
	<% } else {%>
	<span style="float: right;"> <a style="color: white;"
		href="/member/login">로그인</a>
	</span>
	<% } %>
</div>

  <!-- Page Wrapper -->
  <div id="wrapper">