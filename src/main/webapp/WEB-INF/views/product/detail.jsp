<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.openpaas.paasta.marketplace.web.user.common.UserConstants" %>

<div class="content">

	<!-- cBox1 -->
	<div class="cBox type1 appCnt-info">
		<div class="cBox-hd">
			<h4 class="c-tit">상품 상세정보</h4>
		</div>
		<div class="cBox-cnt">
			<!-- inner -->
			<div class="in pb10">
				
				<div class="l_box type1">
					<div class="pn_thumBox">
						<div class="pn_thum">
							<div class="thum_img" id="icon">
								
							</div>
							<!-- <div class="package_bg"></div> -->
						</div>
					</div>
					<button name="button" class="btn btn-color6 btn-sm" type="button" data-toggle="modal" data-target="#myModal">구매하기</button>
					
					<!-- 팝업 Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content modal-sm"><!-- 개발에서 사용하는 다른 팝업모듈 사용시 이부분만 사용하세요.  -->
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title" id="myModalLabel">상품 구매</h4>
								</div>
								<div class="modal-body">
									<p class="popTxt">선택한 상품을 구매하시겠습니까?</p>
								</div>
								<div class="modal-footer">
									<button type="button" name="button" class="btn btn-color1">확인</button>
									<button type="button" name="button" class="btn btn-color2" data-dismiss="modal">닫기</button>
								</div>
							</div>
						</div>
					</div>
					<!-- //팝업 Modal -->

				</div>
				<div class="r_box">
					<div class="cnt_titBox">
						<span class="cnt_tit" id="productName"></span>
						<span class="pn_cate type4" id="category"></span>
					</div>
					<div class="cnt_infoBox">
						<ul>
							<li><div class="tit_area"><span class="ico ico01"></span><span class="tit">버전 :</span><span class="txt" id="version"></span></div></li>
							<li><div class="tit_area"><span class="ico ico04"></span><span class="tit">개요 :</span><span class="txt m_line" id="simpleDesc"></span></div></li>
							<li><div class="tit_area"><span class="ico ico05"></span><span class="tit">가격 :</span><span class="txt" id="price"></span></div></li>
							<li><div class="tit_area"><span class="ico ico02"></span><span class="tit">판매자 :</span><span class="txt" id="sellerName"></span></div></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="cnt_infoBox outer pb40">
				<ul>
					<li>
						<div class="tit_area"><span class="ico ico03"></span><span class="tit">상품 상세 정보</span></div>
						<div class="info_inner_txt" id="productDetailDesc"></div>
					</li>
				</ul>
			</div>
			<!-- //inner -->

			<!-- 미리보기 -->
			<div class="slideBox">
				<h4>미리보기</h4>
				<div class="slideWrap">
					<div class="slide_list">
						<ul class="nav nav-tabs" id="screenshots">
							<li><a href=""><img src="/resources/images/thum_test01.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test02.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test03.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test04.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test05.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test01.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test02.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test03.png" alt="" /></a></li>
							<li><a href=""><img src="/resources/images/thum_test04.png" alt="" /></a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- //미리보기 -->
		</div>
	</div>
	<!-- //cBox1 -->

	<div class="cont_btnBox">
		<button name="button" class="btn btn-color1 btn-md" type="button" onclick="location.href='ProdList.html'">목록</button>
	</div>

</div>
<input type="hidden" id="id" value="<c:out value='${id}' default="" />"/>
<script type="text/javascript">
    var periodUnitName;

    // CUSTOM CODE GET
    var getCustomCode = function(unitCode) {
        var reqUrl = "<%= UserConstants.URI_DB_CUSTOM_CODE_DETAIL_BY_UNIT_CODE %>".replace("{unitCode}", unitCode);

        procCallAjax(reqUrl, "GET", null, null, callbackCustomCode);
    };

    // CUSTOM CODE GET CALLBACK
    var callbackCustomCode = function(data) {
        console.log("이 코드의 정보는? " + JSON.stringify(data));
        periodUnitName = data.unitCodeName;
    };

    // 상품 조회
    var getProduct = function() {
        var productId = $("#id").val();
        var reqUrl = "<%= UserConstants.URI_DB_PRODUCT_DETAIL %>".replace("{id}", productId);

        procCallAjax(reqUrl,"GET",null, null, callbackGetProduct);
    };

    // 상품 조회 CALLBACK
    var callbackGetProduct = function(data) {
        console.log("product detail ::: " + JSON.stringify(data));
        
        var filePath = data.filePath;
        // 기간 코드 변환
        getCustomCode(data.meteringType);
        // 가격
        var pricePerDay = data.unitPrice + "원/" + periodUnitName;
        // 아이콘이미지
        var iconImage = '<img src="' + filePath + data.iconFileName + '" alt="" />'

        $("#icon").html(iconImage)
        $("#productName").html(data.productName);
        $("#category").html(data.category.categoryName);
        $("#version").html(data.versionInfo);
        $("#simpleDesc").html(data.simpleDescription);
        $("#price").html(pricePerDay);
        $("#sellerName").html(data.seller.sellerName);
        $("#productDetailDesc").html('<pre>' + data.detailDescription + '</pre>');
        
        var htmlString = [];
        var listLength = data.screenshots.length;
        for (var i = 0; i < listLength; i++) {
        	htmlString.push(
        		'<li><a href=""><img src="' + filePath + data.screenshots[i].screenshotFileName + '" alt="" /></a></li>'
        	);
        }
        $("#screenshots").html(htmlString);
    };


    // BUTTON
    $("#goProductList").on("click", function () {
       procMovePage("<%= UserConstants.URI_WEB_PRODUCT_LIST %>");
    });


    // 구매하기 BUTTON
    $("#buyProduct").on("click", function () {
        var productId = $("#id").val();
        var reqUrl = "<%= UserConstants.URI_DB_USER_PRODUCT_CREATE %>";

        var reqParam = {
            "productId": productId,
            "userId": USER_ID,
            "userName": USER_NAME
        };

        procCallAjax(reqUrl,"POST", JSON.stringify(reqParam), null, callbackCreateUserProduct);
    });

    var callbackCreateUserProduct = function(data) {
        if (data.resultCode === "SUCCESS") {
            procMovePage("<%=UserConstants.URI_WEB_USER_PRODUCT_LIST%>");
        } else {
            alert("오류 발생!!! : [" + data.resultMessage + "]");
            return;
        }
    };

    $(document.body).ready(function () {
        getProduct();
    });
</script>