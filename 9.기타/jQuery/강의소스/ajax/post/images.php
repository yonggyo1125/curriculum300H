<?php
header("Content-Type", "application/json; charset=utf-8");
$_GET['num'] = $_GET['num'] ?? [];
$text = $imageSrc = "";

switch ($_GET['num']) {
	case 1:
		$text = "글과 이미지1";
		$imageSrc = "post/img/goods1.jpg";
		break;
	case 2:
		$text = "글과 이미지2";
		$imageSrc = "post/img/goods2.jpg";
		break;
	default :
		$text = "글과 이미지3";
		$imageSrc = "post/img/goods3.jpg";
}

$data = [
	'text' => $text,
	'imageSrc' => $imageSrc,
];

echo json_encode($data);