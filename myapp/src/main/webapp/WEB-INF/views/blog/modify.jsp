<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>


<jsp:include page="../layout/header.jsp"/>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

 <h1 class="title">블로그 수정화면</h1>
 
 <form id="frm-blog-modify"
       method="POST"
       action = "${contextPath}/blog/modify.do">
 
       
 <div>
  <span>작성자</span>
  <span>${blog.user.email}</span>
 </div>      
 
  <div>
    <label for="title">제목</label>
    <input type="text" name="title" id="title" value="${blog.title}">
  </div>
 
 <div>
   <textarea id="contents" name="contents">${blog.contents}</textarea>
 </div>
 
 <div>
 <input type="hidden" name="userNo" value="${sessionScope.user.userNo}">
 <button type="submit">수정완료</button>
 <a href="${contextPath}/blog/list.page"><button type="button">작성취소</button></a>
 </div>
 
 </form>
 
 <script>
 
 const fnSummernoteEditor = () => {
	    $('#contents').summernote({
	      width: 1024,
	      height: 500,
	      lang: 'ko-KR',
	      callbacks: {
	        onImageUpload: (images)=>{
	          for(let i = 0; i < images.length; i++) {
	            let formData = new FormData();  
	            formData.append('image', images[i]); 
	            fetch('${contextPath}/blog/summernote/imageUpload.do', {  
	              method: 'POST',
	              body: formData
	            })
	            .then(response=>response.json())  
	            .then(resData=>{
	              $('#contents').summernote('insertImage', '${contextPath}' + resData.src); 
	            })
	          }
	        }
	      }
	    })
	  }
 
  const fnModifyBlog = (evt) => {
	    if(document.getElementById('title').value === '') {
	      alert('제목 입력은 필수입니다.');
	      evt.preventDefault();
	      return;
	    }
	  }
  
  document.getElementById('frm-blog-modify').addEventListener('submit', (evt) => {
	     fnModifyBlog(evt);
	  })
	  
	  fnSummernoteEditor();
 
 </script>
 

 <%@include file="../layout/footer.jsp" %>