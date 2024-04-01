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
  
 <h1>Sign Up</h1>
 
 <!-- 정규식으로 양식한번 걸러내고, 이메일인증(자바로 가능!) 해 볼 겁니당 오오 이메일인증 폰 인증은 안해요 : 사유 돈들음 -->
 <form method="POST"
       action="${contextPath}/user/signup.do"
       id="frm-signup">
 
 <!-- bootstrap class를 적용해봄 -->
 <div class="mb-3"> 
  <label for="email">아이디</label>
  <input type="text" id="email" name="email" placeholder="example@example.com">
  <button type="button" id="btn-code">인증코드받기</button>
  <div id="msg-email"></div>
 </div>
 <div class="mb-3">
 <input type="text" id="code" placeholder="인증코드입력" disabled>
 <button type="button" class="btn btn-primary" id="btn-verify-code">인증하기</button>
 </div>
       
 </form>
  
 <script>

const fnGetContextPath = ()=>{
	const host = location.host; 
	const url = location.href; 
	const begin = url.indexOf(host) + host.length;
	const end = url.indexOf('/', begin + 1);
	return url.substring(begin, end);
}
 
const fnCheckEmail = ()=>{
	let email = document.getElementById('email');
	let regEmail = /^[A-Za-z0-9-_]{2,}@[A-Za-z0-9]{2,}(\.[A-Za-z]{2,6}){1,2}$/;
	if(!regEmail.test(email.value)){
		alert('이메일 형식이 올바르지 않습니다.');
		return;
	}
	fetch(fnGetContextPath() + '/user/checkEmail.do', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ //자바스크립트 객체를 JSON으로 변환해주는 메소드
			'email':email.value
		})
	});
} 

document.getElementById('btn-code').addEventListener('click', fnCheckEmail);

// POST 방식으로 email을 JSON 데이터로 만들어 controller로 보낼 것임
// -> 받는 쪽에서는 @RequestBody로 받는다. 받을 때 쓸 도구는 MAP이다. (Jackson이 상호 맵핑을 돕는다 (JSON <-> MAP)) 
 
 
 
 
 
 
 
 
 
 
 </script> 
  
</body>
</html>