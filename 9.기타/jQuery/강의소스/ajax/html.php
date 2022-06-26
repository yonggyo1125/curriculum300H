<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
		$(() => {
			$("#button").click(function() {
				$.ajax({
					url : "naver.php",
					type : "get", 
					dataType : "html",
					success : function (res) {
						$("#output").html(res);
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
		<button id='button'>웹페이지 가져오기</button>
		<div id='output'></div>
	</body>
</html>