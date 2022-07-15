# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1MomeIVEhJ627OQBh3IZFRsTrJQ2Nfv7e?usp=sharing)

# Promise
- [학습 URL](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise)

#### 1.js
```javascript
const condition = true; // true면 resolve, false면 reject
const promise = new Promise((resolve, reject) => {
  if (condition) {
    resolve('성공');
  } else {
    reject('실패');
  }
});
// 다른 코드가 들어갈 수 있음
promise
  .then((message) => {
    console.log(message); // 성공(resolve)한 경우 실행
  })
  .catch((error) => {
    console.error(error); // 실패(reject)한 경우 실행
  })
  .finally(() => { // 끝나고 무조건 실행
    console.log('무조건');
  });
```

#### 2.js

```javascript
/**
* promise 방식 
*
*/
function buyAsync(mymoney) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			const payment = parseInt(prompt("지불하고자 하는 금액을 입력하십시오"));
			let balance = mymoney - payment;
			if (balance > 0) {
				console.log(`${payment}원을 지불했습니다.`);
				resolve(balance);
			} else {
				reject(`잔액은 ${mymoney}원 입니다. 구매할 수 없습니다.`);
			}
		}, 1000);
	});
}

buyAsync(500)	
	.then((balance) => {
		console.log(`잔액은 ${balance}원 입니다.`);
		return buyAsync(balance);
	})
	.then((balance) => {
		console.log(`잔액은 ${balance}원 입니다.`);
		return buyAsync(balance);
	})
	.then((balance) => {
		console.log(`잔액은 ${balance}원 입니다.`);
		return buyAsync(balance);
	})
	.catch((error) => {
		console.log(error);
	});
```


## async ~ await 구문
- [학습 URL - async](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/async_function)
- [학습 URL - await](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/await)


#### 3.js

```javascript 
/**
* async ~ await 방식 
*
*/
function buyAsync(mymoney) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			const payment = parseInt(prompt("지불하고자 하는 금액을 입력하십시오"));
			let balance = mymoney - payment;
			if (balance > 0) {
				console.log(`${payment}원을 지불했습니다.`);
				resolve(balance);
			} else {
				reject(`잔액은 ${mymoney}원 입니다. 구매할 수 없습니다.`);
			}
		}, 1000);
	});
}

(async function() {
	try {
		let balance = await buyAsync(500);
		console.log(`잔액은 ${balance}원 입니다.`);
		balance = await buyAsync(balance);
		console.log(`잔액은 ${balance}원 입니다.`);
		balance = await buyAsync(balance);
		console.log(`잔액은 ${balance}원 입니다.`);
	} catch (error) {
		console.log(error);
	}
})();
```

## Promise.all 
- 이 프로그램을 실행하면 Promise.all에 지정한 모든 Promise 객체가 실행됩니다.
- 모든작업이 성공으로 끝나면 성공 콜백이 실행됩니다. 
- 실패로 끝난 Promise 객체가 하나라도 있으면 가장 먼저 실패로 끝난 Promise 객체에서 실행한 reject 함수의 인수가 실패 콜백 함수의 인수로 들어갑니다.

#### 4.js

```javascript
function buyAsync(name, mymoney) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			const payment = parseInt(prompt(`${name}님, 지불하고자 하는 금액을 입력하십시오`));
			let balance = mymoney - payment;
			if (balance > 0) {
				console.log(`${payment}원을 지불했습니다.`);
				resolve(balance);
			} else {
				reject(`잔액은 ${mymoney}원 입니다. 구매할 수 없습니다.`);
			}
		}, 1000);
	});
}

Promise.all([
	buyAsync("Tom", 500),
	buyAsync("Huck", 600),
	buyAsync("Becky", 1000)
])
.then((balance) => {
	console.log(balance);
})
.catch((error) => {
	console.log(error);
});
```

# XMLHttpRequest

- [학습 URL1](https://developer.mozilla.org/ko/docs/Web/API/XMLHttpRequest)
- [학습 URL2](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/Using_XMLHttpRequest)


# FileReader

- [학습 URL](https://developer.mozilla.org/ko/docs/Web/API/FileReader)


# 드래그 앤 드롭 API

- [학습 URL](https://developer.mozilla.org/ko/docs/Web/API/DragEvent)


#### js/file_upload.js

```javascript
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
			
			req.open("POST", "file_upload.html");
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
		req.open("POST", "file_delete.html");
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
```

#### css/style.css

```css
* { box-sizing: border-box; }
html { font-size: 13px; }
body { padding: 10px; margin: 0; }
a { color: #000000; text-decoration: underline; }
h1 { font-size: 1.2rem; text-align: center; }
ul { padding: 0; margin: 0; list-style: none; }

.file_box  .drop_box { border: 2px dashed #888888; width: 200px; height: 100px; text-align: center; padding: 10px; margin: 0 auto; }
.file_box  .drop_box .text { font-size: 0.92rem; margin-top: 5px; }

#file_list { display: table; margin : 10px auto; }
#file_list > li { border: 1px solid #d5d5d5; padding: 3px 10px; border-radius: 3px; }
#file_list > li > div { display: table-cell; padding: 0 10px; }
#file_list .progress { color: orange; font-weight: bold; }
#file_list .status { font-size: 1.2rem; cursor: pointer; }
```

#### index.html

```html
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8'>
	<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
	<title>마커스 파일 업로드</title>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<script src="js/file_upload.js"></script>
</head>
<body>
	<h1 class='title'>파일 업로드</h1>
	<div class='file_box'>
		<div class='drop_box'>
			<i class='xi-plus-thin xi-4x'></i>
			<div class='text'>업로드할 파일을 드래그 하세요</div>
		</div>
		<!--// drop_box -->
		<ul id='file_list'></ul>
	</div>
	<!--// file_box -->
</body>
</html>
```