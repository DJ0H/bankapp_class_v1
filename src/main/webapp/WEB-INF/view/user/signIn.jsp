<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />

<div class="col-sm-8">
      <h2>로그인</h2>
      <h5>어서오세요 환영합니다.</h5>
	  <div>
			<form action="/user/sign-in" method="post">
			  <div class="form-group">
			    <label for="username">username:</label>
			    <input type="text" class="form-control" placeholder="username" id="username" name="username">
			  </div>
			  <div class="form-group">
			    <label for="password">Password:</label>
			    <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
			  </div>
			 
			  <button type="submit" class="btn btn-primary">로그인</button>
			  <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=db05ac6858ce996fdf7b28320c648a63&redirect_uri=http://localhost:80/user/kakao-callback">
			  <img alt="" src="/images/kakao_login_medium.png" width="75" height="40"></a>
			</form>
	  </div>	
    </div>
  </div>
</div>

<jsp:include page="../layout/footer.jsp" />