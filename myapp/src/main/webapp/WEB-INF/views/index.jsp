<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- include libraries(jquery, bootstrap) -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap');
* {
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 400;
}
</style>

<!-- include summernote css/js -->
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote.min.css">
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
</head>
<body>
  
  <!-- 아이콘 사용 
  cdnjs.com 의 https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css 적용
  https://fontawesome.com/ 에서 원하는 아이콘 찾아서 적용-->
  <!--  폰트사용
  https://fonts.google.com/ (기본 굵기는 regular 400) 에서 원하는 font 골라서 embed import-->
  <!-- bootstrap은 class에 대한 공부임. 사이트에도 설명 잘 나와있고, 구글링은 더 잘 알려줌 ㅋㅋㅋ 잘 공부해서 적용시키면 된다 -->
  
   <!-- Sign In 안 된 경우 -->
   <c:if test="${sessionScope.user == null}">  
    <a href="${contextPath}/user/signin.page"><i class="fa-solid fa-arrow-right-to-bracket"></i> Sign In </a>
    <a href="${contextPath}/user/signup.page"><i class="fa-solid fa-user-plus"></i> Sign Up </a>
  </c:if>
  
  <!-- Sign In 된 경우 -->
  <c:if test="${sessionScope.user != null}">
    ${sessionScope.user.name}님 반갑습니다
    <!-- serviceImpl에서 session에 저장해놓은 그 정보 -->
  </c:if>
  
  
  
  
  
        
</body>
</html>