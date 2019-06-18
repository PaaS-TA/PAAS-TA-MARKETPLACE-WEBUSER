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
        <h1>마켓플레이스에 오신 것을 환영합니다!</h1>
    </div>
    <div>
        <a href="/market/product">상품 목록</a>
    </div>
    <br>
    <div>
        <span>마이페이지</span>
        <ul>
            <a href="/market/user/product">사용 상품</a>
        </ul>
        <ul>
            <a href="">요금 계산</a>
        </ul>
    </div>
</body>
</html>