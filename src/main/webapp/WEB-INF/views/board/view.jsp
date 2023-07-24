<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
	<h3>${board.member.username }의 글보기</h3>
	<div class="form-group">
		<label for="num">글번호:</label> <input type="text" class="form-control"
			id="num" name="num" value="${board.boardid }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="title">제목:</label> <input type="text" class="form-control"
			id="title" name="title" value="${board.title }" readonly="readonly">
	</div>
	
	<div class="form-group">
		<label for="writer">글쓴이:</label> <input type="text" class="form-control"
			id="writer" name="writer" value="${board.member.username }"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content">${board.content }</textarea>
	</div>
    
		<div class="form-group text-right">
			<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
			<button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
		</div>
		
	<div align="center">
		<textarea rows="3" cols="50" id="msg"></textarea>
		<button type="button" class="btn btn-secondary btn-sm" id="btnComment">댓글쓰기</button>
	</div>
	<div id="replyResult" class="container mt-3"></div>
</div>

<script>
	
var init = function () {
    $.ajax({
        type: 'get',
        url: '/comment/list/' + $("#num").val(),
    }).done(function (resp) {
        console.log(resp);
        var str = "<table class='table table-hover'>";
        $.each(resp, function (key, val) {
            str += "<tr>";
            if (val.member !== null) {
                str += "<td>" + val.member.username + "</td>"; // 사용자 이름 표시
            } else {
                str += "<td>Unknown User</td>"; // 또는 다른 특정 값
            }
            str += "<td>" + val.content + "</td>";
            str += "<td>" + val.regdate + "</td>";
            if (val.editable) {
                str += "<td><a href='javascript:fdel(" + val.cnum + ")'>삭제</a></td>";
            }
            str += "</tr>";
        });
        str += "</table>";
        $("#replyResult").html(str);
    });
}

//수정폼
$('#btnUpdate').click(function(){
	if(confirm("정말 수정할까요?"))
	location.href='/board/update/${board.boardid}'
})
	

$("#btnDelete").click(function(){
	if(!confirm('정말 삭제할까요'))
		return;
	$.ajax({
		type:'DELETE',
		url : '/board/delete/${board.boardid}'
	}).done(function(resp){
		alert("삭제 성공")
		location.href="/board/list"
	})
	.fail(function(e){
		alert("삭제 실패")
	})

})

$("#btnComment").click(function () {
    // 사용자가 댓글을 입력하지 않았을 경우 경고창을 띄웁니다.
    if ($("#msg").val() === "") {
        alert("댓글을 작성하세요");
        return;
    }

    // 댓글 데이터를 생성합니다.
    let data = {
        "bnum": $("#num").val(),
        "content": $("#msg").val()
    };

    // 서버로 댓글 데이터를 전송합니다.
    $.ajax({
        type: 'POST',
        url: '/comment/add/' + $("#num").val(),
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (resp) {
            // 댓글 추가 성공 시, 초기화 함수를 호출하여 댓글 목록 업데이트합니다.
            init();
            // 댓글 작성을 완료했으므로 입력창을 초기화합니다.
            $("#msg").val("");
            alert(resp);
        },
        error: function () {
            alert("댓글 추가 실패");
        }
    });
});

    // 초기화 시 댓글 목록 조회
    init();

 </script>
