<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Test</title>
</head>
<body>
<h1>파일 업로드</h1>
<form action="/upload" method="post" enctype="multipart/form-data">
   <input type="file" value="파일 선택" name="file"/>
   <input type="submit" value="업로드"/>
</form>
</body>
</html>

<!-- 업로드 파일을 넣기 위해서는 apache commons-io,apache commons-fileupload를 추가해 주어야 한다.pom.xml 
	
-->