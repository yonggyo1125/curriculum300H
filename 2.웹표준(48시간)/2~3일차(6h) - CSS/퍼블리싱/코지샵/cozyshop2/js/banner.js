window.addEventListener("DOMContentLoaded", function(e) { // HTML 태그 로딩 완료
	var swiper = new Swiper('.swiper-container', {
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
	  pagination: {
        el: '.swiper-pagination',
      },
    });
}, false);
