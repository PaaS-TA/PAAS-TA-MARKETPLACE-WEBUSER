<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="org.openpaas.paasta.marketplace.web.user.common.UserConstants" %>

<div class="content">
	<!-- Top 비주얼 -->
	<div class="top_visualWrap">
		<div class="top_visual">
			<p class="top_title">Welcome to PaaS-TA Market place</p>
			<p class="top_stitle">
				Thanks to our partnership with PaaS-TA, we have the ability to gain new revenues, and our customers will gain<br />
				access to all the new technologies and benefits those technologies bring. They are exceptionally forward thinking.<br />
				they've built their business around the cloud.
			</p>
		</div>
	</div>
	<!-- //Top 비주얼 -->
	
	<!-- 검색 Form -->
	<div class="searchWrap">
		<div class="searchBox">
			<!-- Form 그룹 -->
			<div class="form-group">
				<div class="form-group-area">
					<div class="fm_label"><label for="" class="label">카테고리</label></div>
					<div class="fm_box">
						<select id="categoryList" name="categoryList" onchange="selectBox();"></select>
					</div>
				</div>
			</div>
			<!-- //Form 그룹 -->
			<!-- Form 그룹 -->
			<div class="form-group">
				<div class="form-group-area">
					<div class="fm_label"><label for="" class="label">상품명</label></div>
					<div class="fm_box">
						<input type="text" id="search_keyword" name="search_keyword" placeholder="검색어를 입력해 주세요." style="width:400px;">
					</div>
				</div>
			</div>
			<!-- //Form 그룹 -->
			<button type="button" id="btnSearch" name="btnSearch" class="btn btn-sch" title="검색"><span class="ico">검색</span></button>
		</div>
	</div>
	<!-- //검색 Form -->

	<div class="cont-titWrap mt30">
		<h3 class="h-tit3">최근 등록 상품 <span class="point4">(20)</span></h3>
	</div>

	<!-- panel area-->
	<ul class="ul-panel" id="productListArea">
	</ul>
	<!-- //panel area-->
</div>

<script type="text/javascript">
    var periodUnitName;

    // 카테고리 목록 조회
    var getCategoryList = function () {
        var reqUrl = "<%= UserConstants.URI_DB_CATEGORY_LIST %>";

        procCallAjax(reqUrl, "GET", null, null, callbackCategoryList);
    };

    // 카테고리 목록 CALLBACK
    var callbackCategoryList = function (data) {
        var CATEGORY_LIST = data.items;

        var categoryListArea = $("#categoryList");
        var htmlArray = [];
        var option = "<option selected='selected' value='ALL'>전체</option>";

        for(var i = 0; i < CATEGORY_LIST.length; i++){
            option += "<option value=" + CATEGORY_LIST[i].id + ">" + CATEGORY_LIST[i].categoryName + "</option>"
        }

        htmlArray.push(option);
        categoryListArea.html(htmlArray);
    };


    // CUSTOM CODE GET
    var getCustomCode = function (unitCode) {
        var reqUrl = "<%= UserConstants.URI_DB_CUSTOM_CODE_DETAIL_BY_UNIT_CODE %>".replace("{unitCode}", unitCode);

        procCallAjax(reqUrl, "GET", null, null, callbackCustomCode);
    };

    // CUSTOM CODE GET CALLBACK
    var callbackCustomCode = function (data) {
        console.log("이 코드의 정보는? " + JSON.stringify(data));
        periodUnitName = data.unitCodeName;
    };


    // Category change
    var selectBox = function () {
        getProductList();
    };


    // BIND search
    $("#btnSearch").on("click", function() {
    	getProductList();
    });

    // 상품 목록 조회
    var getProductList = function() {
        var selectedCategory = $("#categoryList option:selected").val();
        var searchKeyword = $("#search_keyword").val();

        console.log("선택된 카테고리는? ::: " + selectedCategory + " ::: 검색 키워드는? ::: " + searchKeyword);

        if (selectedCategory === "ALL" || selectedCategory === '') {
            selectedCategory = '';
        }

        if(searchKeyword === null || searchKeyword === ''){
            searchKeyword = '';
        }

        var reqUrl = "<%=UserConstants.URI_DB_PRODUCT_LIST%>" + "?categoryId=" + selectedCategory + "&productName=" + searchKeyword;

        procCallAjax(reqUrl, "GET", null, null, callbackGetProductList);
    };


    // 상품 목록 CALLBACK
    var callbackGetProductList = function(data) {
        var PRODUCT_LIST = data.items;

        var htmlString = [];
        var listLength = PRODUCT_LIST.length;

        if (listLength > 0) {
            for (var i = 0; i < listLength; i++) {
	            // 기간 코드 변환
	            getCustomCode(PRODUCT_LIST[0].meteringType);
                var goDetailUrl = "<%=UserConstants.URI_WEB_PRODUCT_DETAIL%>".replace("{id}", PRODUCT_LIST[i].id);
				var iconImagePath = "/icon?" + "filePath=" + PRODUCT_LIST[i].filePath + "&iconFileName=" + PRODUCT_LIST[i].iconFileName;

                htmlString.push(
                	'<li>'
            		+	'<div class="panelWrap" onclick="moveDetail(' + PRODUCT_LIST[i].id + ')">'
            		+		'<div class="panel type3">'
            		+			'<div class="panelBox">'
            		+				'<div class="pn_thumBox">'
            		+					'<div class="pn_thum">'
            		+						'<div class="thum_img">'
            		+							'<img src="' + iconImagePath + '" alt="" />'
            		+						'</div>'
            		+					'</div>'
            		+				'</div>'
            		+				'<span class="pn_cate type3">' + PRODUCT_LIST[i].category.categoryName + '</span>'
            		+				'<div class="pn_tit">'
            		+					'<span class="pn_tit_txt">' + PRODUCT_LIST[i].productName + '<span class="point5">(' + PRODUCT_LIST[i].versionInfo + ')</span></span>'
            		+					'<span class="price_info">가격: ' + PRODUCT_LIST[i].unitPrice + '원/' + periodUnitName + '</span>'
            		+				'</div>'
            		+				'<div class="seller_info"><span class="ico_person"></span><span class="txt">판매자: ' + PRODUCT_LIST[i].seller.sellerName + '</span></div>'
            		+			'</div>'
            		+		'</div>'
            		+	'</div>'
            		+ '</li>'
                );
            }
        } else {
            htmlString.push('<div class="search_resultBox">'
            	+ '<p class="txt_lg">검색조건에 대한 검색결과가 없습니다.</p>'
				+ '<p class="txt_sm">단어의 철자를 확인하거나 다른 검색어를 넣어 다시 검색해주세요.</p>'
				+ '</div>'
			);
        }

        $("#productListArea").html(htmlString);
    };
    
    var moveDetail = function(id) {
    	procMovePage("<%= UserConstants.URI_WEB_PRODUCT_DETAIL %>".replace("{id}", id));
    }


    // ON LOAD
    $(document.body).ready(function () {
        getCategoryList();
        getProductList();
    });
</script>