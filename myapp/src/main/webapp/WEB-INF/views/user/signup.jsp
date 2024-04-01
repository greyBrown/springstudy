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
 <%-- 만약 ajax를 썻다면 이렇게 돌아돌아가야한다.(Promise 객체가 내장되어 있지 않기 때문)
 new Promise((resolve, reject) => {
$.ajax({
url: '이메일중복체크요청'
})
.done(resData => {
if(resData.enableEmail){
resolve();       
} else {
reject();}})})
.then( () => {   
  $.ajax({             
  url: '인증코드전송요청'
    })
.done(resData => {
   if(resData.code == 인증코드입력값
   })
})       
.catch(()=>{   // 실패
}) 
 --%>
 <%-- 이렇게 비동기처리가 연속적으로 필요할 때는 fetch를 쓰는 것이 훨씬훨씬 간단하다.  --%>
 
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
	})
	.then(response=>response.json()) // 받아온 응답 객체 데이터에서 json만 꺼내겠다. 이 json이 promise와 함께 오니까 다시 then.
	.then(resData => {                
		// 만약 $ajax로 처리했다면 new promise가 나오게 되는 예제이다. 비동기작업을 순차적으로 처리해야하기 때문에... 
		// 여기선 fetch가 좋은 선택임. promise가 내장되어 있기 때문에 따로 불러올 필요가 없다.
		if(resData.enableEmail){
			fetch(fnGetContextPath() + '/user/sendCode.do', {    // 이메일 중복체크 통과하면 이메일인증 시작하기. 이렇게 순서대로 진행해야 하기때문에 promise가 필요해 진다는 것.        
				method: 'POST',
			    headers: {
			        'Content-Type': 'application/json'
			      },
			      body: JSON.stringify({ 
			        'email':email.value
			     })
			});  
		} else {
			document.getElementById('msg-email').innerHTML = '이미 사용 중인 이메일입니다.';
			return;
		}
	})
} 

document.getElementById('btn-code').addEventListener('click', fnCheckEmail);

// POST 방식으로 email을 JSON 데이터로 만들어 controller로 보낼 것임
// -> 받는 쪽에서는 @RequestBody로 받는다. 받을 때 쓸 도구는 MAP이다. (Jackson이 상호 맵핑을 돕는다 (JSON <-> MAP)) 
 
 
 
 
 
 
 
 
 
 
 </script> 
  
</body>
</html>