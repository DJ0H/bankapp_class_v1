<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />

<div class="col-sm-8">
      <h2>회원 가입</h2>
      <h5>어서오세요 환영합니다.</h5>
	  <div>
			<form action="/user/sign-up" method="post">
			  <div class="form-group">
			    <label for="username">username:</label>
			    <input type="text" class="form-control" placeholder="username" id="username" name="username">
			  </div>
			  <div class="form-group">
			    <label for="password">Password:</label>
			    <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
			  </div>
			  <div class="form-group">
			    <label for="fullname">fullname:</label>
			    <input type="text" class="form-control" placeholder="Enter fullname" id="fullname" name="fullname">
			  </div>
			  <button type="submit" class="btn btn-primary">회원가입</button>
			</form>
	  </div>	
    </div>
  </div>
</div>

<jsp:include page="../layout/footer.jsp" />