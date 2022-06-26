/**
* 상품 추가 양식
*
* @author 작성자 이름... 
*/
const goodsForm = {
	/**
	* 양식 추가 
	*
	*/
	add : function() {
		$html = $("#form .goods").first().clone();
		
		/* 삭제 버튼 추가 */
		const deleteBtn = "<button class='remove'>삭제</button>";
		$html.find(".btns").append(deleteBtn);
		
		/* 복사한 폼에 있는 입력 데이터 초기화 */
		$html.find("input[type='text']").val('');
		$html.find("input[type='number']").val('');
		$html.find("textarea").val('');
		
		
		$("#form > .inner").append($html);

	},
	/**
	* 양식 제거 
	*
	* @param thisObj - $this
	*/
	remove : function(thisObj) {
		thisObj.closest(".goods").remove();
	},
	/**
	* 상품 등록 
	*
	*/
	register : function() {
		// form 입력 데이터를 쿼리스트링(키=값&키=값)형태로 변환 
		const params = $("#form").serialize(); 
		
		$.ajax({
			url : "http://yonggyo.com/~webclass/ajax.php",
			type : "post",
			dataType : "html",
			data : params,
			success : function(data) {
				console.log(data);
			},
			error : function (err) {
				console.error(err);
			}
		});
	}
};

$(() => {
	$("body").on("click", ".goods .add", function(e) {
		e.preventDefault();
		goodsForm.add();
	});
	
	$("body").on("click", ".goods .remove", function(e) {
		e.preventDefault();
		goodsForm.remove($(this));
	});
	
	$("#register").click(function(e) {
		e.preventDefault();
		goodsForm.register();
	});		
});