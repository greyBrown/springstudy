<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>


<jsp:include page="../layout/header.jsp"/>

<style>

.blog {
   width: 640px;
   height: 180px;
   margin: 10px auto;
   border: 1px solid gray;
   cursor: pointer;
 }
 .blog > span {
   color: tomato;
   display: inline-block;
   box-sizing: border-box;
 
 }
 .blog > span:nth-of-type(1) {width : 100px;}
 .blog > span:nth-of-type(2) {width : 250px;}
 .blog > span:nth-of-type(3) {width : 50px;}
 .blog > span:nth-of-type(4) {width : 150px;}

</style>

 <h1 class="title">블로그 목록</h1>
 
<a href="${contextPath}/blog/write.page">블로그작성</a>

<div id="blog-list">


</div>



<script>

// 전역 변수
var page = 1;
var totalPage = 0; // 0으로 초기화 후 ajax success 부분에서 갱신


const fnGetBlogList = () => {
	
	// page 에 해당하는 목록 요청
	$.ajax({
	  // 요청
	  type: 'GET',
	  url: '${contextPath}/blog/getBlogList.do',                // ?page=' + page, 여기 이렇게 박아도 좋지만, url 과 파라미터를 분리할 수 있다면 분리하는 게 좋음. 분리 어떻게 하죠? 바로 아래 data에 적어주면 된다.
		data : 'page=' + page,	  
	  // 응답
	  dataType: 'json',
	  success: (resData) => {     // resData = {"blogList": [], "totalPage": 10} 스크롤이 증가하는건 최대 totalPage 까지임.
			totalPage = resData.totalPage;
	    $.each(resData.blogList, (i, blog) => {
	    	let str = '<div class="blog" data-blog-no="'+ blog.blogNo +'">';
	    	str += '<span>' + blog.title + '</span>';
	    	str += '<span>' + blog.user.email + '</span>';
	    	str += '<span>' + blog.hit + '</span>';
	    	str += '<span>' + moment(blog.createDt).format('YYYY.MM.DD') + '</span>';
	    	// 날짜 형식 지정을 위한 moment.js 사용
	    	str += '</div>';
	    	$('#blog-list').append(str);   // #blog-list에 만들어 놓은 str 삽입
	    })
	  },
	  error: (jqXHR) => {
		  alert(jqXHR.statusText + '(' + jqXHR.status + ')');
	  }
	});
	
	
	
}

const fnScrollHandler = () => {
	
	// 스크롤이 바닥에 닿으면 page 증가(최대 totalPage 까지) 후 새로운 목록 요청
	
	
}

  
  const fnInsertCount = () => {
	  let insertCount = '${insertCount}';
	  if(insertCount !== ''){
		  if(insertCount === '1'){
			  alert('블로그가 등록되었습니다.'); 
		  } else {
			  alert('블로그가 등록에 실패했습니다.');
		  }
	  }
  }
  
  fnInsertCount();
  fnGetBlogList();
</script>

 
 <%@include file="../layout/footer.jsp" %>