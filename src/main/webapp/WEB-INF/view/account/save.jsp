<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/header.jsp" />

<div class="col-sm-8">
	<h2>계좌 생성 페이지</h2>
	<h5>어서오세요 환영합니다.</h5>
	<div class="bg-light p-md-5">
		<form action="/account/save" method="post">
			<div class="form-group">
				<label for="number">계좌번호</label> <input type="text"
					class="form-control" placeholder="number" id="number"
					name="number">
			</div>
			<div class="form-group">
				<label for="password">계좌 비밀번호</label> <input type="password"
					class="form-control" placeholder="Enter password" id="password"
					name="password">
			</div>
			<div class="form-group">
				<label for="balance">입금 금액</label> <input type="text"
					class="form-control" placeholder="Enter balance" id="balance"
					name="balance">
			</div>

			<button type="submit" class="btn btn-primary">계좌생성</button>
		</form>
	</div>
</div>
</div>
</div>

<jsp:include page="../layout/footer.jsp" />