<%--
  Created by IntelliJ IDEA.
  User: hrjin
  Date: 2019-06-11
  Time: 오전 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <title>상품 상세 조회</title>
    <%@include file="../common/commonLibs.jsp" %>
</head>
<body>
    <table>
        <ul>
            <tr>
                <td>상품 이름 : </td>
                <td id="productName"></td>
            </tr>
        </ul>
        <ul>
            <tr>
                <td>카테고리 : </td>
                <td id="category"></td>
            </tr>
        </ul>
        <ul>
            <tr>
                <td>버전 : </td>
                <td id="version"></td>
            </tr>
        </ul>
        <ul>
            <tr>
                <td>개요 : </td>
                <td id="simpleDesc"></td>
            </tr>
        </ul>
        <ul>
            <tr>
                <td>가격 : </td>
                <td id="price"></td>
            </tr>
        </ul>
        <ul>
            <tr>
                <td>판매자 : </td>
                <td id="sellerName"></td>
            </tr>
        </ul>
        <ul>
            <tr>
                <td>상품 상세 정보 : </td>
                <td id="productDetailDesc"></td>
            </tr>
        </ul>
    </table>
    <div>
        <button type="button" class="button btn_default" title="구매하기" id="buyProduct">구매하기</button>
        <button type="button" class="button btn_default" title="목록" id="goProductList">목록</button>
    </div>
</body>
</html>
<input type="hidden" id="id" value="<c:out value='${id}' default="" />"/>
<script type="text/javascript">

    var getProduct = function() {
        var productId = $("#id").val();
        var reqUrl = "<%=UserConstants.URI_DB_PRODUCT_DETAIL%>".replace("{id}", productId);

        procCallAjax(reqUrl,"GET",null, null, callbackGetProduct);
    };

    var callbackGetProduct = function(data) {
        console.log("product detail ::: " + JSON.stringify(data));

        var pricePerDay = data.unitPrice + "원/" + data.meteringType;

        $("#productName").html(data.productName);
        $("#category").html(data.category.categoryName);
        $("#version").html(data.versionInfo);
        $("#simpleDesc").html(data.simpleDescription);
        $("#price").html(pricePerDay);
        $("#sellerName").html(data.seller.sellerName);
        $("#productDetailDesc").html(data.detailDescription);

    };

    $("#goProductList").on("click", function () {
       procMovePage("<%=UserConstants.URI_WEB_USER_PRODUCT_LIST%>");
    });

    $(document.body).ready(function () {
        getProduct();
    });

</script>
