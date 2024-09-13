<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function hrefmodify(){
	$("#listForm").attr("action", "/webapplication/modify").submit();
}
function hrefdelete(){
	$("#listForm").attr("action", "/webapplication/delete").submit();
}

</script>
</head>
<body>
<div class="signup">
	<form action="/webapplication/reg" method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" id="hp" name="hp"></td>
			</tr>
			<tr>
				<td>메모</td>
				<td><input type="text" id="memo" name="memo"></td>
			</tr>
		</table>
		<input type="submit" value="전화번호 입력">
	</form>
</div>

<br>
======================
<br>

<div class="showuser">
	검색<input type="text" id="searchtxt" name="searchtxt">
	<table>
	<c:forEach items="${lists }" var="item" varStatus="status">
		<tr>
			<td><a href="/webapplication/${item.id}"><c:out value=" ${item.name}"></c:out></a></td>
			<td><c:out value=" ${item.hp }"></c:out></td>
		</tr>
	</c:forEach>
	</table>
</div>

<br>
======================
<br>

<div class="showresult">
	<form id="listForm" name="listForm" action="#" method="get">
		<table>
			<tr>
				<td>${pb.name }</td>
			</tr>
			<tr>
				<td>${pb.hp }</td>
			</tr>
			<tr>
				<td>${pb.memo }</td>
			</tr>
			<input type="hidden" value="${pb.id }" name="id" id="id">
			2021-08-01<br>
		</table>
		<input type="button" id="modify" value="수정" onclick="hrefmodify();" href="javascript:void(0);">
		<input type="button" id="delete" value="삭제" onclick="hrefdelete();" href="javascript:void(0);">
	</form>	
</div>

<script type="text/javascript">
$(document).ready(function() {
	$("#searchtxt").on("propertychange change keyup paste input", function(){
		var _search = $("#searchtxt").val();
		/* alert(_search); */
		
		$.ajax({
		    type: "POST",
		    url: "/webapplication/search",
		    data: { "_search": _search }, // 이 경우 Content-Type은 기본적으로 application/x-www-form-urlencoded
		    success: function(result){
		        // 결과 처리
		        var html = "";
		        
		        $.each(result, function(index, item) {
		            html += "<tr>";
		            html += "<td><a href='/webapplication/" + item.id + "'>" + item.name + "</a></td>";
		            html += "<td>" + item.hp + "</td>";
		            html += "</tr>";
		        });
		        
		        $(".showuser table").html(html);
		    },
		    error: function(xhr, status, error) {
		        console.error("AJAX Error: ", status, error);
		    }
		});

	});
});

</script>
</body>
</html>