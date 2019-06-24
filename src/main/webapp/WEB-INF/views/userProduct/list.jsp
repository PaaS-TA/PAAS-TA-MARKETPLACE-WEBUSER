<%--
  Created by IntelliJ IDEA.
  User: hrjin
  Date: 2019-06-17
  Time: 오후 6:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <title>사용 상품 목록 조회</title>
    <%@include file="../common/commonLibs.jsp" %>
</head>
<body>
    <div>
        <div>
            <select onchange="selectBox();" id="categoryList"></select>
        </div>

        <div class="keyword_search">
            <input id="search_keyword" type="text" name="search_keyword" style="-ms-ime-mode: active;" placeholder="상품명 검색" autocomplete="on" onkeypress="if(event.keyCode==13) {gCheckMore = false; search(''); }" value="${listRequest.name}" />

            <button type="button" href="javascript:void(0);" id="btnSearch">검색</button>
        </div>
    </div>
    <div>
        <tr>
            <td>상품명</td>&nbsp;&nbsp;
            <td>버전</td>&nbsp;&nbsp;
            <td>카테고리</td>&nbsp;&nbsp;
            <td>사용시작일</td>&nbsp;&nbsp;&nbsp;&nbsp;
            <td>상태</td>&nbsp;&nbsp;&nbsp;
            <td>접속 URL</td>
        </tr>
        <div id="productListArea">
        </div>
    </div>
    <br>
    <div>
        <button type="button" href="javascript:void(0);" onclick="procMovePage('/market')">메인으로</button>
    </div>
</body>
</html>
<script type="text/javascript">

    var CATEGORY_LIST = [];
    var USER_PRODUCT_LIST = [];

    // 카테고리 목록 조회
    var getCategoryList = function () {
        var reqUrl = "<%=UserConstants.URI_DB_CATEGORY_LIST%>";

        procCallAjax(reqUrl, "GET", null, null, callbackCategoryList);
    };

    // 카테고리 목록 CALLBACK
    var callbackCategoryList = function (data) {
        CATEGORY_LIST = data.items;

        var categoryListArea = $("#categoryList");
        var htmlArray = [];
        var option = "<option selected='selected' value='ALL'>전체</option>";

        for(var i = 0; i < CATEGORY_LIST.length; i++){
            option += "<option value=" + CATEGORY_LIST[i].id + ">" + CATEGORY_LIST[i].categoryName + "</option>"
        }

        htmlArray.push(option);
        categoryListArea.html(htmlArray);
    };


    // SORT(Category)
    var selectBox = function () {
        getUserProductList();

    };


    // BIND
    $("#btnSearch").on("click", function() {
        search();
    });

    // SEARCH
    var search = function(){
        getUserProductList();
    };


    // 상품 목록 조회
    var getUserProductList = function() {
        var selectedCategory = $("#categoryList option:selected").val();
        var searchKeyword = $("#search_keyword").val();

        console.log("선택된 카테고리는? ::: " + selectedCategory + " ::: 검색 키워드는? ::: " + searchKeyword);

        if (selectedCategory === "ALL" || selectedCategory === '') {
            selectedCategory = '';
        }

        if(searchKeyword === null || searchKeyword === '') {
            searchKeyword = '';
        }

        var reqUrl = "<%=UserConstants.URI_DB_USER_PRODUCT_LIST%>" + "?userId=" + USER_ID + "&categoryId=" + selectedCategory + "&productName=" + searchKeyword;

        procCallAjax(reqUrl, "GET", null, null, callbackGetUserProductList);
    };


    // 상품 목록 CALLBACK
    var callbackGetUserProductList = function(data) {
        USER_PRODUCT_LIST = data.items;

        var productListArea = $("#productListArea");
        var htmlString = [];
        var listLength = USER_PRODUCT_LIST.length;

        if(listLength > 0){
            for(var i = 0; i < listLength; i++){
                var goDetailUrl = "<%=UserConstants.URI_WEB_USER_PRODUCT_DETAIL%>".replace("{id}", USER_PRODUCT_LIST[i].id);
                htmlString.push(
                    "<tr>"
                    + "<td><a href='javascript:void(0);' onclick=\"procMovePage(\'" + goDetailUrl + "')\">" + USER_PRODUCT_LIST[i].productName + "&nbsp;&nbsp;</a></td>"
                    + "<td>" + USER_PRODUCT_LIST[i].product.versionInfo + "&nbsp;&nbsp;&nbsp;</td>"
                    + "<td>" + USER_PRODUCT_LIST[i].product.category.categoryName + "&nbsp;&nbsp;&nbsp;</td>"
                    + "<td>" + USER_PRODUCT_LIST[i].strUseStartDate + "&nbsp;&nbsp;</td>"
                    + "<td>" + USER_PRODUCT_LIST[i].provisionStatus + "&nbsp;&nbsp;</td>"
                    + "<td>" + USER_PRODUCT_LIST[i].accessUrl + "</td>"
                    +"</tr>"
                );
            }
        }else{
            htmlString = "<tr>"
                + "<td colspan='6'><p class='user_p'>상품이 존재하지 않습니다.</p></td>" + "</tr>"
        }

        productListArea.html(htmlString);
    };

    // ON LOAD
    $(document.body).ready(function () {
        getCategoryList();
        getUserProductList();
    });
</script>
