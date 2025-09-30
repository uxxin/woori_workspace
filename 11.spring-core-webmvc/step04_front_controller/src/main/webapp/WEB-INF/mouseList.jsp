<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>
<table>
    <tr>
      <th>번호</th>
      <th>이름</th>
      <th>국가</th>
      <th>주소</th>
    </tr>
    
    <!-- DB에 Mouse 데이터가 1개도 없을 경우, 등록된 Mouse 정보가 없습니다 출력 -->
    <c:choose>
      <c:when test="${empty requestScope.mouseList || fn:length(mouseList) == 0}">
        <tr>
          <td>등록된 Mouse 정보가 없습니다.</td>
        </tr>
      </c:when>
      <c:otherwise>
        <c:forEach items="${requestScope.mouseList}" var="mouse">
          <tr>
            <td>${mouse.id}</td>
            <td>${mouse.name}</td>
            <td>${mouse.country}</td>
            <td>${mouse.address}</td>
          </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
    
  </table>
</body>








</html>