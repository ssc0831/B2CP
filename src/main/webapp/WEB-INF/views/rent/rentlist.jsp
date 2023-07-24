<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="container">
	<h2>대여 요청</h2>
 	<div class="row">
	  <c:forEach items="${lists }" var="item">
	  	<c:if test="${item.rentalid.rentstatus == 'request' }">
		  <div class="card col-4" style= "width:400px"> <!-- col-4 4개의 칼럼이 나타남 -->
			    <img class="card-img-top" src="/resource/img/${item.img }" alt="Card image" style="width:100px;height:100px">
			    <div class="card-body" >
			      <h4 class="card-title">${item.title }</h4>
			      <div class="form-group">
							<button type="button" class="btn btn-primary btn-sm" onclick="rentsubmit(${item.rentalid.rentalid})">대여수락</button>
						</div>
			    </div>
		   </div>
		   </c:if>
	  </c:forEach>
  </div>
  <hr/>
  <h2>대여 중</h2>
 	<div class="row">
	  <c:forEach items="${lists }" var="item">
	  	<c:if test="${item.rentalid.rentstatus == 'rent' }">
		  <div class="card col-4" style= "width:400px"> <!-- col-4 4개의 칼럼이 나타남 -->
			    <img class="card-img-top" src="/resource/img/${item.img }" alt="Card image" style="width:100px;height:100px">
			    <div class="card-body" >
			      <h4 class="card-title">${item.title }</h4>
			      
			    </div>
		   </div>
		   </c:if>
	  </c:forEach>
  </div>
  <hr/>
  <h2>요청 대기</h2>
 	<div class="row">
	  <c:forEach items="${lists }" var="item">
	  	<c:if test="${empty item.rentalid.rentstatus }">
		  <div class="card col-4" style= "width:400px"> <!-- col-4 4개의 칼럼이 나타남 -->
			    <img class="card-img-top" src="/resource/img/${item.img }" alt="Card image" style="width:100px;height:100px">
			    <div class="card-body" >
			      <h4 class="card-title">${item.title }</h4>
			      <p class="card-text">${item.content }</p>
			    </div>
		   </div>
		   </c:if>
	  </c:forEach>
  </div>
</div>

<script>
function rentsubmit(num){
	if(!confirm('요청 승낙하겠습니까?')){
		return false;
	}
	$.ajax({
		type:'get',
		url:'/rent/acceptrent/'+num
	})
	.done(function(){
		alert('성공')
		location.href="/rent/list"
	})
	.fail(function(){
		alert('실패')
	})
}
</script>


<%@ include file="../include/footer.jsp"%>