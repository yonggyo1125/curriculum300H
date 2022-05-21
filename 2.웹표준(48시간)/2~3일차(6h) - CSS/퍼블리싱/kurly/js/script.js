window.addEventListener("DOMContentLoaded", function(e) {
	/** 메인 배너 롤링 S */
	var swiper = new Swiper('.main_banner .swiper-container', {
      navigation: {
        nextEl: '.main_banner .navi.next',
        prevEl: '.main_banner .navi.prev',
       },
	   speed : 2000,
	   loop: true,
	   autoplay : {
			delay : 5000,
	   }
    });
	/** 메인 배너 롤링 E */
	
	/** 메인 상품 롤링 S */
	var swiperGoods = new Swiper('.goods_rolling .swiper-container', {
      slidesPerView: 4,
      spaceBetween: 18,
	  loop: true,
	  navigation: {
        nextEl: '.goods_rolling .navi.next',
        prevEl: '.goods_rolling .navi.prev',
      },
    });
	/** 메인 상품 롤링 E */
	
	/** MD 추천 상품 롤링 S */
	var swiperMdGoods = new Swiper('.md_choice .swiper-container', {
      slidesPerView: 4,
      spaceBetween: 18,
	  loop: true,
	  navigation: {
        nextEl: '.md_choice .navi.next',
        prevEl: '.md_choice .navi.prev',
      },
    });
	/** MD 추천 상품 롤링 E */
	
}, false);