<?php
/**
* 드래그 & 드롭 파일 업로드 처리 
*
* @author Lee, Yonggyo
*/
try {
	$data = file_get_contents("php://input");
	if (!$data) {
		throw new Exception("저장할 파일 데이터가 없습니다.");
	}
	
	$post = json_decode($data, true);
	if (!$post || !$post['name'] || !$post['data']) {
		throw new Exception("파일 업로드에 실패하였습니다.");
	}
	
	$fileName = time() . rand(1000, 9999) ."_" . $post['name'];
	$fileData = explode("base64,", $post['data']);
	$fileData = base64_decode($fileData[1]);
	
	$path = __DIR__ . "/data/{$fileName}";
	$result = file_put_contents($path, $fileData);
	if ($result === false) {
		throw new Exception("파일 업로드에 실패하였습니다.");
	}
	
	$result = [
		'error' => 0,
		'message' => '파일 업로드 성공',
		'path' => $path,
		'url' => "/data/{$fileName}",
		'fileName' => $fileName,
	];
	
	echo json_encode($result);
} catch (Exception $e) {
	$result = [
		'error' => 1,
		'message' => $e->getMessage(),
	];
	
	echo json_encode($result);
}