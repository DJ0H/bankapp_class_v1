<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp" />

<div class="col-sm-8">
      <h2> 계좌 목록</h2>
      <h5>어서오세요 환영합니다.</h5>
	  <div class="bg-light p-md-5">
	  <c:choose>
	  	<c:when test="${accountList != null }">
		<table class="table">
			<thead>
				<tr>
					<th>계좌 번호</th>
					<th>잔액</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${accountList }" var="accountList">
				<tr>
					<td>${accountList.number }</td>
					<td>${accountList.balance }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		<c:otherwise>
			<p>아직 생성된 계좌가 없습니다</p>
		</c:otherwise>
		</c:choose>
	  </div>	
    </div>
  </div>
</div>

<jsp:include page="../layout/footer.jsp" />