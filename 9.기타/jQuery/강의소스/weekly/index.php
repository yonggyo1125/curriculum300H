<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src='js/weekly.js'></script>
	</head>
	<body>
		<div class='layout_width'>
			<ul class='set_select'>
				<li>
					<input type='radio' name='weeklySet' value='basic' id='weeklySet_basic' checked>
					<label for='weeklySet_basic'>BASIC</label>
				</li>
				<li>
					<input type='radio' name='weeklySet' value='classic' id='weeklySet_classic'>
					<label for='weeklySet_classic'>CLASSIC</label>
				</li>
				<li>	
					<input type='radio' name='weeklySet' value='premium' id='weeklySet_premium'>
					<label for='weeklySet_premium'>PREMIUM</label>
				</li>
			</ul>
			<!--// set_select -->
			
			<!-- 메뉴 선택 영역 S -->
			<div class='selection_box'>
				<div class='menus'></div>
				<div class='summary'>
					<ul class='selected'></ul>
					
					<div class='bottom'>
						현재 선택한 갯수 
						<span class='current'>0</span> / 
						<span class='max_cnt'>0</span>
					</div>
				</div>
			</div>
			<!--// selection_box -->
			<!-- 메뉴 선택 영역 E -->
		</div>
		<!--// layout_width -->
		
		<div id='layer_dim' class='dn'></div>
	</body>
</html>