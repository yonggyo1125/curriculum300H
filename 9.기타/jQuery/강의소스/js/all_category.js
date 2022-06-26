/**
* 전체 메뉴
*
* @author 만든사람 
*/

const AllCategory = {
	/**
	* 전체 메뉴 열기
	*
	*/
	open : function() {
		$(".all_category").removeClass("on")
							   .removeClass("fadeIn")
						       .addClass("on");
		
		setTimeout(function() {
			$(".all_category").addClass("fadeIn");
		}, 300);
	},
	/**
	* 전체 메뉴 닫기 
	*
	*/
	close : function() {
		$(".all_category").removeClass("fadeIn");
		setTimeout(function() {
			$(".all_category").removeClass("on");
		}, 1000);
	}
};

$(() => {
	$("#open_all").click(function() {
		if ($(".all_category").hasClass("on")) { // 열려 있으면  
			// 닫기 
			AllCategory.close();
		} else { // 닫혀 있으면 
			// 열기
			AllCategory.open();
		}
	});
	
	$(".navi").mouseleave(function() {
		AllCategory.close();
	});
});