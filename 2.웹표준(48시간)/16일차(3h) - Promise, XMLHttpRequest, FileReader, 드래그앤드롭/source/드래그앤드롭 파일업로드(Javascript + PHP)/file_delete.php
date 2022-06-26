<?php
/**
* 파일 삭제 처리 
*
* @author Lee, Yonggyo
*/
try {
	$data = file_get_contents("php://input");
	if (!$data) {
		throw new Exception("삭제할 파일 데이터가 없습니다.");
	}
	
	$post = json_decode($data, true);
	if (!$post || !$post['name']) {
		throw new Exception("파일 삭제에 실패하였습니다.");
	}
	
	$path = __DIR__ . "/data/{$post['name']}";
	if (!unlink($path)) {
		throw new Exception("파일 삭제에 실패하였습니다.");
	}
	
	$result = [
		'error' => 0,
		'message' => '파일 삭제 성공',	
	];
	
	 echo json_encode($result);
} catch (Exception $e) {
	$result = [
		'error' => 1,
		'message' => $e->getMessage(),
	];
	
	echo json_encode($result);
}