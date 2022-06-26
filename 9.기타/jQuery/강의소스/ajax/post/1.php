<?php
header("Content-Type", "application/json; charset=utf-8");
$data = [
	'test1' => 1,
	'test2' => 2,
	'test3' => 3,
];

echo json_encode($data); // JSON 데이터를 출력해주는 연습 