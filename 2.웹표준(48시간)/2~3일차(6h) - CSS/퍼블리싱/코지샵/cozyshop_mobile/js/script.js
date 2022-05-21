window.addEventListener("DOMContentLoaded", function(e) {
	/** 메인 배너 롤링 S */
	var swiper = new Swiper('.swiper-container', {
		pagination: {
			el: '.swiper-pagination',
			clickable : true,
		},
		loop : true,
	});
	/** 메인 배너 롤링 E */
	
	/** 슬라이드 더보기 메뉴 S */
	var showAside = document.querySelector(".show_aside"); // 더보기 버튼 
	var slideMenu = document.querySelector(".slide_menu"); // 슬라이드 메뉴 
	var layerDim = document.querySelector(".layer_dim"); // 레이어 BG
	
	if (showAside) {
		showAside.addEventListener("click", function(e) {
			var clList = slideMenu.classList;
			var dim = layerDim.classList.remove("dn");
			if (clList.contains("on")) { // 슬라이드가 열려 있는 경우 
				// 닫기
				clList.remove("on");
				layerDim.classList.add("dn");
			} else { // 슬라이드가 닫혀 있는 경우 
				// 열기 
				clList.add("on");
			}
		}, false);			
	}
	// 슬라이드 닫기 
	var closeBtn = document.querySelector(".slide_menu .close_btn");
	if (closeBtn) {
		closeBtn.addEventListener("click", function(e) {
			slideMenu.classList.remove("on");
			layerDim.classList.remove("dn");
			layerDim.classList.add("dn");
		}, false);
	}
	
	/** 슬라이드 더보기 메뉴 E */
	
	/** 전체 메뉴 - 서브 메뉴 펼침 처리 S */
	var mainMenu = document.querySelector(".all_category .menu1");
	if (mainMenu) {
		mainMenu.addEventListener("click", function(e) {
			if (mainMenu.classList.contains("on")) { // 메뉴가 펼쳐 있음 
				// 메뉴 닫기 
				mainMenu.classList.remove("on");
			} else { // 메뉴가 닫혀 있음 
				// 메뉴 열기
				mainMenu.classList.add("on");
			}
			
			
			
		}, false);
	}
	
	
	/** 전체 메뉴 - 서브 메뉴 펼침 처리 E */
}, false);