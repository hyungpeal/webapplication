<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="update">
	<form action="/webapplication/modifyProc" method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name" value="${pb.name }"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" id="hp" name="hp" value="${pb.hp }"></td>
			</tr>
			<tr>
				<td>메모</td>
				<td><input type="text" id="memo" name="memo" value="${pb.memo}"></td>
			</tr>
			<input type="hidden" id="id" name="id" value="${pb.id }">
		</table>
		<input type="submit" value="전화번호 수정">
	</form>
</div>
</body>
</html>