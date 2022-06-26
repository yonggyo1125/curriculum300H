/**
* 드래그앤 드롭 파일업로드 
*
* @author Lee, Yonggyo 
*/ 
var CodeFty = CodeFty || {};
CodeFty.fileUpload = {
	/**
	* 파일업로드 
	* 
	* @param File file 업로드 파일 객체
	*/
	uploadFile : function(file) {
		var reader = new FileReader();
		
		/** 파일 업로드 시작 시 */
		reader.addEventListener("loadstart", function(e) {
			var fileList = document.getElementById("file_list");
			var li = document.createElement("li");
			var span1 = document.createElement("div");
			span1.className = "file_name";
			var span2 = document.createElement("div");
			span2.className = "progress";
			
			var span3 = document.createElement("div");
			span3.className = "status";	
			
			var fileName = document.createTextNode(file.name);
			fileList.appendChild(li);
			li.appendChild(span3);
			li.insertBefore(span2, span3);
			li.insertBefore(span1, span2);
			fileList.appendChild(li);
			span1.appendChild(fileName);
		}, false);
		
		/** 파일 업로드 진행 시 */
		reader.addEventListener("progress", function(e) {
			var loaded = e.loaded;
			var total = e.total;
			var per = Math.round(loaded / total * 10000) / 100;
			
			var progress = document.querySelector(".progress");
			if (progress) {
				progress.innerHTML = per + "%";
			}
		}, false);
		
		/** 파일 읽기 완료 시 */
		reader.addEventListener("load", function(e) {
			var params = {
				name :  file.name, 
				data : reader.result,
			};
			
			params = JSON.stringify(params);
			var req = new XMLHttpRequest();
			req.addEventListener("load", function(e) {
				const result = JSON.parse(req.responseText);
				var status = document.querySelector(".status"); // 업로드 상태 
				
				if (result) {
					if (result.error) { // 파일 업로드 실패 
						status.innerHTML = result.message;
					} else { // 파일 업로드 성공 
						var classList = status.classList;
						if (!classList.contains("xi-file-remove")) {
							classList.add("xi-file-remove");
						}
						
						status.setAttribute("filename", result.fileName);
						
						/** 파일 삭제 이벤트 등록 처리 S */
						const fileDelete = document.querySelector(".xi-file-remove");
						if (fileDelete) {
							fileDelete.removeEventListener("click", CodeFty.fileUpload.deleteFile);
							fileDelete.addEventListener("click", CodeFty.fileUpload.deleteFile, false);
						}
						/** 파일 삭제 이벤트 등록 처리 E */
						
						
					    /** 부모창에서 업로드 성공 콜백 용 데이터 전송 S */
						const callbackData = {
							mode : "uploaded",
							fileName : result.fileName,
							fileUrl : result.url
						};
						parent.postMessage(callbackData, "*");
						 /** 부모창에서 업로드 성공 콜백 용 데이터 전송 E */
					}
				}
			}, false);
			
			req.open("POST", "file_upload.php");
			req.setRequestHeader("content-type", "application/json");
			req.send(params);
			
		}, false);
		
		/** 파일 읽기 에러 발생 시 */
		reader.addEventListener("error", function(e) {
			console.log("Error", e);
		}, false);
		reader.readAsDataURL(file);
	},
	/**
	* 파일 삭제 
	*
	* @param Event e
	* @return Boolean 
	*/ 
	deleteFile : function(e) {
		if (!confirm('정말 삭제하시겠습니까?')) {
			return false;
		}
		
		const fileName = e.target.getAttribute("filename");
		if (!fileName) {
			return false;
		}
		
		var params = {
			name : fileName
		}
		params = JSON.stringify(params);
		
		const req = new XMLHttpRequest();
		req.addEventListener("load", function(e) {
			var result = JSON.parse(req.responseText);
			if (result) {
				if (result.error) { // 파일 삭제 실패 
					alert(result.message);
				} else { // 파일 삭제 성공 
					document.getElementById("file_list").innerHTML = "";
					
					/** 부모창에서 삭제 콜백 용 데이터 전송 S */
					const callbackData = {
						mode : "delete",
						fileName : fileName,
					};
					parent.postMessage(callbackData, "*");
					/** 부모창에서 삭제 콜백 용 데이터 전송 E */
				}
			}
		}, false);
		req.open("POST", "file_delete.php");
		req.setRequestHeader("content-type", "application/json");
		req.send(params);
	}
};


/** 이벤트 처리 S */
window.addEventListener("DOMContentLoaded", function(e) {
	/**  파일 업로드 처리 S */
	const dropBox = document.querySelector(".drop_box");
	if (dropBox) {
		dropBox.addEventListener("dragover", function(e) {
			e.preventDefault();
		}, false);
		
		dropBox.addEventListener("drop", function(e) {
			e.preventDefault();
			var files = e.dataTransfer.files;
			if (files && files.length > 0) {
				for (var key in files) {
					var file = files[key];
					if (typeof file != 'object') continue;
					
					// 파일 업로드 처리 
					CodeFty.fileUpload.uploadFile(file);	
				}
			} 
		}, false);
	}
	/** 파일 업로드 처리 E */
}, false);
/** 이벤트 처리 E */