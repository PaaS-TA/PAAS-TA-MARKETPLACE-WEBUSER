<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
							<div class="thum_img">
								<img src="../common/images/thum_ico06.png" alt="" />
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
						<span class="cnt_tit">영카트</span>
						<span class="pn_cate type4">커머스</span>
					</div>
					<div class="cnt_infoBox">
						<ul>
							<li><div class="tit_area"><span class="ico ico01"></span><span class="tit">버전 :</span><span class="txt">1.0</span></div></li>
							<li><div class="tit_area"><span class="ico ico04"></span><span class="tit">개요 :</span><span class="txt m_line">그누보드의 쇼핑몰 플러그인 프로그램으로 주문, 결제 등의 기능을 포함하고 있어 홈페이지에서 쇼핑몰을 직접 운영</span></div></li>
							<li><div class="tit_area"><span class="ico ico05"></span><span class="tit">가격 :</span><span class="txt">1,000원/일</span></div></li>
							<li><div class="tit_area"><span class="ico ico02"></span><span class="tit">판매자 :</span><span class="txt">에스아이알소프트</span></div></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="cnt_infoBox outer pb40">
				<ul>
					<li>
						<div class="tit_area"><span class="ico ico03"></span><span class="tit">상품 상세 정보</span></div>
						<div class="info_inner_txt">
							<pre>
							● 회원관리
							  - 회원관리, 회원메일발송, 접속자집계, 접속자 검색, 접속자로그관리, 포인트관리, 투표관리
							
							● 쇼핑몰관리
							  - 쇼핑몰설정, 분류관리, 상품관리, 주문관리, 주문개별관리, 주문통합관리, 배송일괄처리, 온라인견적, 상품유형관리, 상품재고관리, 이벤트관리, 사용후기, 상품문의, 내용관리, FAQ관리, 새창관리, 배너관리, 배송회사관리
							
							● 게시판관리
							  - 게시판관리, 게시판그룹관리, 인기검색어관리, 인기검색어순위, 1대1 문의설정, 내용관리, FAQ 관리

							</pre>
						</div>
					</li>
				</ul>
			</div>
			<!-- //inner -->

			<!-- 미리보기 -->
			<div class="slideBox">
				<h4>미리보기</h4>
				<div class="slideWrap">
					<div class="slide_list">
						<ul class="nav nav-tabs">
							<li><a href=""><img src="../common/images/thum_test01.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test02.png" alt="" /></a></li>
							<li><a href="" class="video"><img src="../common/images/thum_test05.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test03.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test04.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test05.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test01.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test02.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test03.png" alt="" /></a></li>
							<li><a href=""><img src="../common/images/thum_test04.png" alt="" /></a></li>
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