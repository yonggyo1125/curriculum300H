/**
* 메인 메뉴 고정 
*
* @author 만든사람
*/

/**
* 메인 상단 메뉴 고정 
* 
* #pos_scroll 기준
* 스크롤 많으면 fixed 클래스 추가
* 스크롤이 적으면 fixed 클래스 제거 
*/
function naviFixed() {
	const top = $("#pos_scroll").offset().top;
	const scrollTop = $(window).scrollTop();
	
	$(".navi").removeClass("fixed");
	
	if (top < scrollTop) { 
		$(".navi").addClass("fixed");
	} 
}

$(() => {
	naviFixed(); // 페이지가 로딩되었을 때 호출 
	
	$(window).scroll(function() {
		naviFixed(); // 스크롤 되었을 때 호출 
	});
});