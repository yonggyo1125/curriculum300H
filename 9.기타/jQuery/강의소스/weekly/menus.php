<ul>
<?php for ($i = 0; $i < 12; $i++) : ?>
	<li class='menu'>
		<input type='checkbox' name='menu[]' value='고추잡채<?=$i+1?>' id='menu_<?=$i+1?>'>
		<label for='menu_<?=$i+1?>'>
			<img src='img/menu.jpg'>
		</label>
		<div class='menu_nm'>고추잡채<?=$i+1?></div>
		<div class='view_more' data-num='menu_<?=$i+1?>'>상품상세보기</div>
	</li>
<?php endfor; ?>
</ul>