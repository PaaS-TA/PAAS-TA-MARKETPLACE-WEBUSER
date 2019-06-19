<%--
  Created by IntelliJ IDEA.
  User: hrjin
  Date: 2019-06-18
  Time: 오후 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <title>구매 상품 상세 조회</title>
    <%@include file="../common/commonLibs.jsp" %>
</head>
<body>
    <table>
        <ul>
            <tr>
                <td>아이콘</td>
                <td id="iconArea"></td>
            </tr>
        </ul>
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
                <td>스크린 샷</td>
                <td id="screenshotArea"></td>
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
        <button type="button" class="button btn_default" title="연결" id="connectUrl">접속 URL 연결</button>
        <button type="button" class="button btn_default" title="정지" id="stopProduct">정지</button>
        <button type="button" class="button btn_default" title="목록" id="goUserProductList">목록</button>
    </div>
</body>
</html>
<input type="hidden" id="id" value="<c:out value='${id}' default="" />"/>
<script type="text/javascript">
    var periodUnitName;

    // CUSTOM CODE GET
    var getCustomCode = function (unitCode) {
        var reqUrl = "<%=UserConstants.URI_DB_CUSTOM_CODE_DETAIL_BY_UNIT_CODE%>".replace("{unitCode}", unitCode);

        procCallAjax(reqUrl, "GET", null, null, callbackCustomCode);
    };

    // CUSTOM CODE GET CALLBACK
    var callbackCustomCode = function (data) {
        console.log("이 코드의 정보는? " + JSON.stringify(data));
        periodUnitName = data.unitCodeName;
    };

    // 상품 조회
    var getUserProduct = function() {
        var userProductId = $("#id").val();
        var reqUrl = "<%=UserConstants.URI_DB_USER_PRODUCT_DETAIL%>".replace("{id}", userProductId);

        procCallAjax(reqUrl,"GET", null, null, callbackGetUserProduct);
    };

    // 상품 조회 CALLBACK
    var callbackGetUserProduct = function(data) {
        console.log("product detail ::: " + JSON.stringify(data));

        // 기간 코드 변환
        getCustomCode(data.meteringType);
        var pricePerDay = data.unitPrice + "원/" + periodUnitName;

        $("#productName").html(data.productName);
        $("#category").html(data.product.category.categoryName);
        $("#version").html(data.product.versionInfo);
        $("#simpleDesc").html(data.product.simpleDescription);
        $("#price").html(pricePerDay);
        $("#sellerName").html(data.product.seller.sellerName);
        $("#productDetailDesc").html(data.product.detailDescription);

        // 아이콘
        var iconArea = $("#iconArea");
        var iconImagePath = "/icon?" + "filePath=" + data.product.filePath + "&iconFileName=" + data.product.iconFileName;
        console.log("아이콘 경로 ::: " + iconImagePath);

        var htmlStr = "<tr><td><img src='" + iconImagePath + "' width='100' height='100'" + "></td></tr>";
        iconArea.html(htmlStr);


        // 스크린샷
        var screenshotArea = $("#screenshotArea");
        var htmlString = [];
        var screenshotList = data.product.screenshots;
        var listLength = screenshotList.length;

        if(listLength > 0) {
            for (var i = 0; i < listLength; i++) {
                var screenshotImagePath = "/screenshots?" + "filePath=" + data.product.filePath + "&screenshotFileName=" + screenshotList[i].screenshotFileName;
                console.log("스크린샷 경로 ::: " + screenshotImagePath);
                htmlString.push(
                    "<tr>"
                    + "<td><img src='" + screenshotImagePath + "' width='100' height='100'" + "></td>"
                    + "</tr>"
                );
            }
        }else{
            htmlString = "<tr>"
                + "<td colspan='" + listLength +"'><p class='user_p'>스크린샷이 존재하지 않습니다.</p></td>" + "</tr>"
        }

        screenshotArea.html(htmlString);

    };


    // BUTTON
    $("#goUserProductList").on("click", function () {
        procMovePage("<%=UserConstants.URI_WEB_USER_PRODUCT_LIST%>");
    });


    // ON LOAD
    $(document.body).ready(function () {
        getUserProduct();
    });
</script>