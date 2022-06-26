/**
* 상품 폼 관련 모듈
*
* @author 만든사람 
*/
const goodsForm = {
	/**
	* 상품 등록 양식 추가 
	*
	*/
	add : function() {
		// 첫번째 goods를 선택한 후에 복사
		$html = $("#form .goods").first().clone();
		
		// 삭제 버튼 추가
		const deleteBtn = "<button class='remove'>삭제</button>";
		$html.find(".btns").append(deleteBtn);
		
		// 입력 데이터 초기화
		$html.find("input[type='text']").val(''); // 빈값 '' 지정하면 초기화
		$html.find("input[type='number']").val('');
		$html.find("textarea").val('');
		
		// 복사한 $html을 #form에 append
		$("#form").append($html);
	},
	/**
	* 상품 등록 양식 제거
	*
	* @param Object thisObj - 이벤트가 발생한 요소 객체(클릭한 삭제 버튼)
	*/
	remove : function(thisObj) {
		// 선택한 요소에서 위로 올라가면서 .goods를 찾아 선택 하고 제거(remove)
		thisObj.closest(".goods").remove(); 
	}
};

$(() => { 
	// 추가 버튼 클릭시 처리 
	//$(".btns .add").click(function(e) {
	$("body").on("click", ".btns .add", function(e) {
		e.preventDefault();
		
		goodsForm.add();
	});
	
	// 삭제 버튼 클릭시 처리 
	//$(".btns .remove").click(function(e) {
	$("body").on("click", ".btns .remove", function(e) {
		e.preventDefault();
		
		goodsForm.remove($(this));
		
		//e.target === this // 이벤트가 발생한 요소(button.remove)
	});
});