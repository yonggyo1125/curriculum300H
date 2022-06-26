/**
* 모바일 메뉴
*
* @author 만든사람
*/
const mobileSlide = {
	/**
	* 슬라이드 메뉴 열기 
	*
	*/
	open : function() {
		// 레이어 노출 
		$("#layer_dim").removeClass("dn");
		
		// 슬라이드 메뉴 노출
		$(".slide").removeClass("on")
					 .addClass("on");
		
	},
	/**
	* 슬라이드 메뉴 닫기 
	*
	*/
	close : function() {
		// 슬라이드 메뉴 감추기 
		$(".slide").removeClass("on");
		
		// 레이어 감추기 
		$("#layer_dim").removeClass("dn")
						   .addClass("dn");
	}
};

$(() => {
	$(".slide_open").click(function() {
		mobileSlide.open();
	});
	
	$(".slide_close, #layer_dim").click(function() {
		mobileSlide.close();
	});
});