<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>거래정보 조회</title>

<!-- <link rel="stylesheet" href="/css/bootstrap.css"> -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!--     <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=61ec611efb29e6e4f087ec7593c325ca"></script> -->

<link rel="stylesheet" href="${root}/css/user.css">
<link rel="stylesheet" href="${root}/css/main.css">

<!-- kakao map -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=daf70af7c5ea892b02e59b794599d14e"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<%--     <script type="text/javascript" src="${root}/js/dealInfo.js"></script> --%>
<%--     <script type="text/javascript" src="${root}/js/dong.js"></script> --%>

<!-- bootstrap css -->
<style>
.set_local {
	border: 1px solid black;
	padding: 50px 0px;
	text-align: center;
	min-width: 800px;
	width: 90%;
	margin: auto;
}

.local_box {
	margin: 10px 15px;
	display: inline-block;
	max-width: 350px;
	width: 25%;
	height: 50px;
	text-align: center;
	border: 1px solid black;
	vertical-align: middle;
	border-radius: 10px;
	font-size: 120%;
	font-weight: bold;
	border: 0;
}

#seoul_city {
	line-height: 200px;
	font-size: 130%;
	color: rgb(241, 241, 241);
	background-color: rgb(86, 212, 191);
	border-radius: 30px;
}

#local_si {
	border: 2px solid rgb(136, 136, 136);
	border-radius: 30px;
}

#local_gu {
	border: 2px solid rgb(136, 136, 136);
	border-radius: 30px;
}

#local_dong {
	border: 2px solid rgb(136, 136, 136);
	border-radius: 30px;
}

.information_text {
	text-align: center;
	font-size: 50px;
	min-width: 800px;
}

.information_table_box {
	min-width: 1000px;
}

.information_table {
	margin: auto;
	width: 90%;
	min-width: 800px;
	min-width: 500px;
}

.search_btn_box {
	/* text-align: center; */
	margin-top: 30px;
}

.search_bdnm {
	height: 60px;
	width: 200px;
	margin-right: 10px;
	display: inline-block;
}

.search_btn {
	height: 60px;
	width: 100px;
}

.page_switch_box {
	text-align: center;
}

.information_table tr td {
	padding: 0 10px;
}

h4 {
	margin: 30px 0px;
	font-weight: bold;
	font-size: 40px;
}

.page-link {
	border: none;
	font-weight: bold;
}

.prev_btn, .next_btn {
	font-weight: bold;
}

.kakaoMap {
	width: 800px;
	height: 100%;
	margin-left: 10%;
	margin-right: 10%;
}

#modal {
	display: none;
	position: relative;
	width: 100%;
	height: 100%;
	z-index: 1;
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/template/navbar.jsp"%>

	<main>
		<section class="setter">
			<article class="set_local">

				<select class="local_box " id="local_si">
					<option value="">--시 선택--</option>
					<c:if test="${not empty requestScope.slist}">
						<c:forEach items="${requestScope.slist}" var="s">
							<option value="${s.sidoCode}">${s.sidoName}</option>
						</c:forEach>
					</c:if>
				</select> <select class="local_box " id="local_gu" name="local_gu">
					<option value="">--구 선택--</option>

				</select> <select class="local_box" id="local_dong">
					<option value="">--동 선택--</option>
				</select>

				<div class="search_btn_box">
					<input type="text" placeholder="건물명을 검색하세요"
						class="search_bdnm form-control" id="aptNameSearch"> <input
						type="submit" value="검색" class="search_btn btn btn-outline-info"
						id="searchBtn">
				</div>
			</article>
		</section>

		<section class="kakaoMap">
			<div id="map" style="width: 100%; height: 350px;"></div>
		</section>

		<section class="information">
			<article class="information_text">
				<h4 id="page_title"></h4>
			</article>

			<article class="information_table_box">
				<table id="infoTable" class="table table-hover border-1">
					<tr>
						<th>건물명</th>
						<!--                         <th>거래구분</th> -->
						<th>거래금액</th>
						<th>면적</th>
						<th>건축년도</th>
					</tr>
				</table>
			</article>

			<article>
				<div class="page_switch_box">
					<div class="page_cnt">[1]</div>
					<button class="prev_btn btn btn-outline-dark">prev</button>
					<div class="page-area" style="display: inline-block"></div>
					<button class="next_btn btn btn-outline-dark">next</button>
				</div>
			</article>
		</section>
	</main>

	<div class="modal" id="myModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">아파트 거래정보</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="container">
					<table class="table" id = "dealTable">
						<thead>
							<tr>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>Email</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>John</td>
								<td>Doe</td>
								<td>john@example.com</td>
							</tr>
							<tr>
								<td>Mary</td>
								<td>Moe</td>
								<td>mary@example.com</td>
							</tr>
							<tr>
								<td>July</td>
								<td>Dooley</td>
								<td>july@example.com</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>


</body>
<script>
	$(function(){
		sidoList();
		let sidoCode = "";
		let sidoName = "";
		let dongCode = "";
		let dongName = "";
		
		//셀렉트 박스 - 시 선택시 동 셀렉트박스 갱신
		$("#local_si").change(function(){
			gugunList($("#local_si").val());
			sidoCode = "#"+$("#local_si").val();
			sidoName = $(sidoCode).text();
		})
		
		//셀렉트 박스 - 동 선택시 구 셀렉트박스 갱신
		$("#local_gu").change(function(){
			dongList($("#local_gu").val());
		})
		
		// 셀렉트 박스 - 구 선택시 동,구 정보를 가지고 ajax통신해서 거래정보 나열하기
		$("#local_dong").change(function(){
			let local = sidoName + " " + $("#local_gu").val();
			console.log(local);
			dongCode = "#" +$("#local_dong").val();
			dongName = $(dongCode).text();
			
			
			$.ajax({
				url:"${root}/apt/info/"+$("#local_dong").val(),
				method:'GET',
				success:function(data){
					let el = `<tr><th>건물명</th><th>주소</th><th>건축년도</th></tr>`;
					let positions = [];
					if(data.length == 0){
						alert("해당하는 아파트 정보가 없습니다");
						return true;
					}
					for(let info of data){
						console.log(info);
						let marker = {
								title: info.aptName,
								latlng: new kakao.maps.LatLng(info.lat, info.lng)
						}
						el += `
							<tr id = "getInfo" value = "\${info.aptCode}">
								<input type = "hidden" id = "child" value = "\${info.aptCode}">
								<input type = "hidden" id = "nameChild" value = "\${info.aptName}">
								<td>
									\${info.aptName}
								</td>
								<td>
								\${local} \${info.dongName} \${info.jibun}
								</td>
								<td>
								\${info.buildYear}
								</td>
							
						`;
						positions.push(marker);

					}
					
					$('#infoTable').html(el)
					$("#page_title").html($("select[name=local_gu] option:selected").text() + " " +dongName + "의 거래정보")
					
					makeMap(positions,map);
					console.log(positions[0].latlng)
					panTo(positions[0].latlng,map);
				}
			})
		})
		
		$("#searchBtn").click(function(){
			if(!$("#aptNameSearch").val()){
				alert("건물명을 입력해주세요!")
				return
			}else{ // 건물명 입력한 경우
				if($('#local_gu').val() && $("#local_dong").val()){ //구, 동 정보가 있는 경우
					let local = sidoName + " " + $("#local_gu").val();
					console.log($("#aptNameSearch").val());
					$.ajax({
						url:"${root}/apt/info/"+$("#local_dong").val()+"/"+$("#aptNameSearch").val(),
						method:'GET',
						success:function(data){
						let positions = [];
							let el = `<tr><th>건물명</th><th>주소</th><th>건축년도</th></tr>`;
							
							if(data.length == 0){
								alert("해당하는 아파트 정보가 없습니다");
								return true;
							}
							for(let info of data){
								let marker = {
										title: info.aptName,
										latlng: new kakao.maps.LatLng(info.lat, info.lng)
								}
								el += `
									<tr id = "getInfo" value = "\${info.aptCode}">
										<input type = "hidden" id = "child" value = "\${info.aptCode}">
										<input type = "hidden" id = "nameChild" value = "\${info.aptName}">
										<td>
											\${info.aptName}
										</td>
										<td>
										\${local} \${info.dongName} \${info.jibun}
										</td>
										<td>
										\${info.buildYear}
										</td>
									
								`;
								positions.push(marker);
							}
							console.log(positions);
							$('#infoTable').html(el)
							$("#page_title").html($("select[name=local_gu] option:selected").text() + " " + $("#local_dong").val() +" "+ $("#aptNameSearch").val() + "아파트의 의 거래정보")
							makeMap(positions,map);
							panTo(positions[0].latlng,map);
						}
					})		
				}
				else if(!$('#local_gu').val() && !$("#local_dong").val()){ // 구, 동 정보가 없는 경우 -> 전체에서 검색값으로
					let local = sidoName + " " + $("#local_gu").val();
					$.ajax({
						url:"${root}/apt/info/allDeal/"+$("#aptNameSearch").val(),
						method:'GET',
						contentType:"application/json; charset=UTF-8",
						success:function(data){
							let positions = [];
							let el = `<tr><th>건물명</th><th>주소</th><th>건축년도</th></tr>`;
							
							if(data.length == 0){
								alert("해당하는 아파트 정보가 없습니다");
								return true;
							}
							for(let info of data){
								let marker = {
										title: info.aptName,
										latlng: new kakao.maps.LatLng(info.lat, info.lng)
								}
								el += `
									<tr id = "getInfo" value = "\${info.aptCode}">
										<input type = "hidden" id = "child" value = "\${info.aptCode}">
										<input type = "hidden" id = "nameChild" value = "\${info.aptName}">
										<td>
											\${info.aptName}
										</td>
										<td>
										\${info.sidoName} \${info.dongName} \${info.jibun}
										</td>
										<td>
										\${info.buildYear}
										</td>
									
								`;
								positions.push(marker);
							}
							console.log(positions);
							$('#infoTable').html(el)
							$("#page_title").html($("#aptNameSearch").val() + "아파트의 거래정보")
							makeMap(positions,map);
							panTo(positions[0].latlng,map);
						}
					})		
				}
				
			}
		})
		
		// 거래정보 각 항목 클릭시 상세보기
		$(document).on("click", "#getInfo", function(e){
			let aptCode = $(this).children("#child").val();
			let aptName = $(this).children("#nameChild").val() + " 거래정보";
			$(".modal-title").text(aptName);
			$.ajax({
				url:"${root}/apt/deal/"+aptCode,
				method:'GET',
				contentType:"application/json; charset=UTF-8",
				success:function(data){
					
					
					let el = `
					<thead>
						<tr>
							<th>거래가격</th>
							<th>거래년도</th>
							<th>거래월</th>
							<th>거래일</th>
							<th>면적</th>
							<th>층정보</th>
						</tr>
					</thead>
					<tbody>
					`;
					
					if(data.length == 0){
						alert("해당하는 아파트 정보가 없습니다");
						return true;
					}
					for(let info of data){
						
						el += `
							<tr>
								<td>\${info.dealAmount}</td>
								<td>\${info.dealYear}</td>
								<td>\${info.dealMonth}</td>
								<td>\${info.dealDay}</td>
								<td>\${info.area}</td>
								<td>\${info.floor}</td>
							</tr>
						`;
						
					}
					el+=`</tbody>`;
					$("#dealTable").html(el);
				}
			})
			
			
			$(".modal").modal({
				fadeDuration:300,
				showClose:false
			});
		})
		
		
		//kakao map
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	    mapOption = { 
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	})
	function sidoList(){
		$.ajax({
			url:"${root}/code/sido",
			method:"GET",
			success:function(data){
				
				let el = `<option value="">--시 선택--</option>`;
				for(let si of data){
					
					el+=`<option value="\${si.sidoCode}" id = "\${si.sidoCode}">\${si.sidoName}</option>`;
				}
				
				$("#local_si").html(el);
			},
			error:function(){
				alert("sido error");
			}
		})
	}
	
	function gugunList(gugun){
		let code = '';
		for(let g of gugun){
			if(g == '0'){
				break;
			}
			code+=g;
		}
		console.log(code);
		$.ajax({
			url:"${root}/code/gugun/"+code,
			method:"GET",
			success:function(data){
				
				let el = `<option value="">--구 선택--</option>`;
				for(let gu of data){
					
					el+=`<option value="\${gu.gugunName}">\${gu.gugunName}</option>`;
				}
				
				$("#local_gu").html(el);
				dongList("zz"); // 아무값이나 넣어서 동초기화
			},
			error:function(){
				alert("gugun error");
			}
		})
	}
	
	function dongList(gugunName){
		$.ajax({
			url:"${root}/code/dong/"+gugunName,
			method:"GET",
			success:function(data){
				
				let el = `<option value="">--동 선택--</option>`;
				for(let dong of data){
					
					el+=`<option value="\${dong.dongCode}" id = "\${dong.dongCode}">\${dong.dongName}</option>`;
		
				}
				$("#local_dong").html(el);
				
				
			},
			error:function(){
				alert("gugun error");
			}
		})
			
	}
	
	
	
	function makeMap(positions,map){	
		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		    
		for (var i = 0; i < positions.length; i ++) {
		    
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		}
	}
	
	function panTo(latlng,map) {
	    // 이동할 위도 경도 위치를 생성합니다 
	    var moveLatLon = latlng;
	    
	    // 지도 중심을 부드럽게 이동시킵니다
	    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	    map.setCenter(moveLatLon);            
	}   
	

</script>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>