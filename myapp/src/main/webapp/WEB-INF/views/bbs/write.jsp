<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>


<jsp:include page="../layout/header.jsp"/>

 <h1 class="title">BBS 작성화면</h1>
 
 <!-- 작성만 write에서 하고 나머지는 다 목록에서 한다. 간단한 게시판임! -->
 <!-- 작성할때 즈에발 중요한것만 정렬해놓고 작업해요 뭐 물어봤는데 탭 20개고 이러면 캭 -->
 
 

 <%@include file="../layout/footer.jsp" %>