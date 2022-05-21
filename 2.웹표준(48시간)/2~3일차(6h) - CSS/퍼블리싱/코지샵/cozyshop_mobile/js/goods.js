window.addEventListener("DOMContentLoaded", function(e) {
	var tabs = document.querySelectorAll(".goods_description .tab_wrap li");
	tabs.forEach(function(el,index) {
		el.addEventListener("click", function(e) {
			/** 탭 활성화 표기 처리 */
			tabs.forEach(function(tab) {
				tab.classList.remove("on");
			});
			
			el.classList.add("on");
			
			/** 우선 모두 감춤 */
			var tabContents = document.querySelectorAll(".goods_description .tab_content");
			tabContents.forEach(function(content) {
				if (!content.classList.contains("dn")) {
					content.classList.add("dn");
				}
			});
			
			/** 해당하는 컨텐츠만 노출 */
			var tabContent = document.querySelector(`.goods_description .content${index+1}`);
			if (tabContent) {
				tabContent.classList.remove("dn");
			}
		}, false);
	});
	
}, false); 