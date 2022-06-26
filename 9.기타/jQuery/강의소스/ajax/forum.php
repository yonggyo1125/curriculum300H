<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		
		<script>
		$(() => {
			$("#button").click(function() {
				$.ajax({
					url : "forum_data.php",
					type : "get",
					dataType : "json",  
					success : function (res) {
						let html = "";
						res.forEach((v) => {
							html += `<li>${v}</li>`;
						});
						
						$("#forum_data").append(html);
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
		<button id='button'>게시글 가져오기</button>
		<ul id='forum_data'></ul>
	</body>
</html>