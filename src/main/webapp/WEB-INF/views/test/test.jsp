<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <title>사용자 포탈</title>

    <%@include file="../common/commonLibs.jsp" %>
</head>
<body>
    <div>
        <h1>Hello World hrjin!!!</h1>
    </div>
    <div>
        <a href="/market/product">상품 목록 조회</a>
    </div>
</body>
</html>