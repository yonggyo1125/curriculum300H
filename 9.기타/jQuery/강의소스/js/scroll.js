/**
* 상하 이동 버튼 
*
*/

$(() => {
	$(".go_btns .btn").click(function() {
		let scrollTop = 0; // 상단일때
		if ($(this).hasClass("bottom")) { // 하단 이동 
			scrollTop = $("body").outerHeight();
		}
		
		$("html, body").animate({scrollTop : scrollTop + "px"}, 1000);
	});
});