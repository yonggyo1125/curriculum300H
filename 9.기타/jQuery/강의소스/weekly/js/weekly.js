/**
* 위클리 세트
*
* @author 만든사람
*/
const WeeklySet = {
	maxSelectCnt : 7, // 최대 선택 가능 갯수 
	/**
	* 위클리 세트 메뉴 출력
	*
	* @param String setNm - 세트 종류(basic, classic, premium)
	*/
	printMenus : function(setNm) {
		if (!setNm) { // setNm이 true 조건 아니라면("", null, undefined)
			return; // 실행 중지
		}
		
		$.ajax({
			url : "menus.php",
			type : "get",
			dataType : "html",
			data : { setNm : setNm },
			success : function(res) {
				$(".selection_box .menus").html(res);
				
				WeeklySet.updateSelection(); // 메뉴 변경시 선택 항목 업데이트 
			},
			error : function (err) {
				console.error(err);
			}
		});
		
	},
	/**
	* 선택한 메뉴 업데이트 
	*
	*/
	updateSelection : function() {
		$list = $(".menus input[name^='menu']:checked");
		let html = "";
		$.each($list, function() {
			const v = $(this).val();
			const num = $(".menu input[type='checkbox']").index($(this));
			html += `<li>
							${v}
							<span class='remove' data-num='${num}'>X</span>
						</li>`;
		});
		
		$(".summary .selected").html(html);
		
		$(".max_cnt").text(this.maxSelectCnt); // 최대 선택 가능 갯수 
		$(".current").text($list.length); // 현재 선택된 갯수 
	},
	/**
	* 최대 선택 갯수를 넘었는지 체크
	*
	* @throw 최대 갯수를 넘었을때 메세지 전달
	*/
	checkMaxSelection : function() {
		const maxCnt = this.maxSelectCnt;
		const selectedCnt = $(".menus input[name^='menu']:checked").length;
		if (selectedCnt > maxCnt) {
			const msg = `위클리 세트는 최대 ${maxCnt}개 선택 가능합니다.`;
			throw msg;
		}
	},
	/**
	* 상품 상세 정보 레이어로 출력 
	*
	* @param String num 상품 고유 번호
	*/
	popupViewMore : function (num) {
		$("#layer_dim").removeClass("dn");
		if ($("#layer").length == 0) { // 레이어가 없는 경우에만 추가
			$("body").append("<div id='layer'></div>");
		}
		
		$.ajax({
			url : "popup.php",
			type : "get",
			dataType : "html",
			data : { num : num },
			success : function (res) {
				$("#layer").html(res);
			},
			error : function (err) {
				console.error(err);
			}
		});
	},
	/**
	* 레이어 팝업 제거
	*
	*/
	removePopup : function() {
		$("#layer").remove();
		$("#layer_dim").removeClass("dn")
		                               .addClass("dn");
	}
};

$(() => {
	/** 초기 로딩시 basic 메뉴 선택 */
	WeeklySet.printMenus('basic');
	
	/** 위클리 세트 선택 탭 클릭시 */
	$(".set_select input[type='radio']").click(function() {
		const setNm = $(this).val();
		WeeklySet.printMenus(setNm); // 메뉴 출력 
	});
	
	/** 위클리 세트 메뉴 클릭시 */
	$("body").on("click", ".menus input[name^='menu']", function(e) {
		/* 최대 선택 갯수 체크 S */
		try {
			WeeklySet.checkMaxSelection();
		} catch(msg) {
			e.preventDefault();
			alert(msg);
			return;
		}
		/* 최대 선택 갯수 체크 E */
		
		WeeklySet.updateSelection();
	});
	
	/** 선택 메뉴 삭제 클릭시 */
	$("body").on("click", ".summary .remove", function() {
		const num = $(this).data("num");
		$(".menu input[type='checkbox']").eq(num).prop("checked", false);
		WeeklySet.updateSelection();
	});
	
	/** 상품 상세 보기 클릭 */
	$("body").on("click", ".view_more", function() {
		const num = $(this).data("num");
		WeeklySet.popupViewMore(num);
	});
	
	/** 레이어 백그라운드 클릭 */
	$("#layer_dim").click(function() {
		WeeklySet.removePopup();
	});
});