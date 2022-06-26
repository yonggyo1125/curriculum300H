<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
		$(() => { // ready 
			$(".post").click(function() {
				const num = $(this).data("num");
				//const url = `post/${num}.php`; -> HTML 데이터 변경 
				const url = "post/images.php"; // JSON 데이터 변경
				
				$.ajax({
					url : url, 
					type : "get",
					dataType : "json", // json(자바스크립트 객체), text
					data : { num : num },
					success : function (res) {
						const image = `<img src='${res.imageSrc}'>`;
						$("#text").text(res.text); // 문구 출력
						$("#image").html(image); // 이미지 출력 
					},
					error : function (err) {
						console.error(err);
					}
				});
				
			});
		});
		</script>
	</head>
	<body>
		<div>
			<span class='post' data-num='1'>글1</span>
			<span class='post' data-num='2'>글2</span>
			<span class='post' data-num='3'>글3</span>
		</div>
		<div id='output'>
			<div id='text'></div> <!-- 텍스트 -->
			<div id='image'></div> <!-- 이미지 -->
		</div>
	</body>
</html>