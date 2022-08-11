# 강의 동영상 링크

[동영상 링크](https://drive.google.com/drive/folders/1_YzIZCb6xTFdYW5WrgBp60t__tJXZKfL?usp=sharing)

# 파일업로드

## 파일 업로드를 위한 form 태그 구성
```html
<form method=“POST” action=“JSP 파일” enctype=“multipart/form-data”>
   <input type=“file” name=“요청 파리미터 이름”>
</form>
```
- form 태그의 method 속성은 반드시 POST 방식으로 설정
- form 태그의 enctype 속성은 반드시 multipart/form-data로 설정
- form 태그의 action 속성은 파일 업로드를 처리할 JSP 파일로 설정
- 파일 업로드를 위해 input 태그의 type 속성을 file로 설정해야 합니다. 만약 여러 파일을 업로드 하려면 2개 이상의 input 태그를 사용하고 name 속성에 서로 다른 값을 설정합니다.


## Commons-FileUpload를 이용한 파일 업로드
- 파일 업로드 패키지인 Commons-FileUpload는 서버의 메모리상에서 파일 처리가 가능하도록 지원합니다.
- 이 패키지는 Commons-io 패키지를 바탕으로 작성되었기 때문에 웹 브라우저에서 서버로 파일을 업로드하기 위한 오픈 라이브러리 commons-fileupload.jar, common-io.jar 파일을 다음 배포 사이트에서 직접 다운로드해서 사용합니다.
- JSP 페이지에 page 디렉티브 태그의 import 속성을 사용하여 패키지 org.apache.commons.fileupload.\*를 설정해야 합니다.

- 배포사이트: [http://commons.apache.org/downloads/](http://commons.apache.org/downloads/)
- 다운로드 파일 : commons-fileupload-1.4-bin.zip, commons-io-2.11.0-bin.zip

#### ServletFileUpload 클래스의 메서드

|메서드|유형|설명|
|-----|----|-------|
|setSizeMax(long sizeMax)|void|최대 파일의 크기를 설정합니다.|
|setFileSizeMax(long fileSizeMax)|void|메모리상에 저장할 최대 크기를 설정|
|parseRequiest(RequestContext ctx)|List<FileItem>|multipart/form-data 유형의 요청 파라미터를 가져옵니다.|

#### FileItem 클래스의 메서드

|메서드|유형|설명|
|-----|----|-------|
|isFormField()|boolean|요청 파라미터가 파일이 아니라 일반데이터인 경우 true를 반환합니다.|
|getFieldName()|String|요청 파라미터의 이름을 얻어옵니다.|
|getString()|String|기본 문자 인코딩을 사용하여 요청 파라미터의 값을 얻어옵니다.|
|getString(String encoding)|String|설정한 문자 인코딩을 사용하여 요청 파라미터의 값을 얻어옵니다.|
|getName()|String|업로드된 파일(경로 포함)의 이름을 얻어옵니다.|
|getSize()|long|업로드된 파일의 크기를 얻어옵니다.|
|get()|byte[]|업로드된 파일을 바이트 배열로 얻어옵니다.|
|isInMemory()|boolean|업로드된 파일이 메모리에 저장된 상태인 경우 true를 반환하고 임시 디렉터리에 저장된 경우 false를 반환합니다.|
|delete()|void|파일과 관련된 자원을 삭제합니다. 메모리상에 저장된 경우 할당된 메모리를 반환하고 임시 파일로 저장된 경우 파일을 삭제합니다.|
|write()|void|파일과 관련된 자원을 저장합니다.|
|getContentType()|String|웹 브라우저가 전송하는 콘텐츠 유형을 반환하고 정의되어 있지 않은 경우 null을 반환합니다.|

#### fileupload03.jsp
```html
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
	<form method=“post” action=“fileupload03_process.jsp” enctype=“multipart/form-data”>
		파일 <input type=“file” name=“filename”><br>
		<input type=“submit” value=“파일 올리기”>
	</form>
</body>
</html>
```

#### fileupload03_process.jsp
```java
<%@ page contentType=“text/html; charset=utf-8”%>
<%@ page import=“org.apache.commons.fileupload.*”%>
<%@ page import=“org.apache.commons.fileupload.disk.*” %>
<%@ page import=“org.apache.commons.fileupload.servlet.*” %>
<%@ page import=“java.util.*” %>
<%@ page import=“java.io.*” %>
<html>
<body>
<%
	String fileUploadPath = “D:\\upload”;
	
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);

	List<FileItem> items = upload.parseRequest(request);

	Iterator<FileItem> params = items.iterator();
	while(params.hasNext()) {
		FileItem fileItem = params.next();
		if (!fileItem.isFormField()) {
			String fileName = fileItem.getName();
			fileName = fileName.substring(fileName.lastIndexOf(“\\”) + 1);
			File file = new File(fileUploadPath + “/” + fileName);
			fileItem.write(file);		}
	}
%>
</body>
</html>	
```

#### fileupload04.jsp 
```html
<%@ page contentType=“text/html; charset=utf-8” %>
<html>
<body>
	<form name=“listForm” method=“post” enctype=“multipart/form-data” action=“fileupload04_process.jsp”>
		이 름 : <input type=“text” name=“name”><br>
		제 목 : <input type=“text” name=“subject”><br>
		파 일 : <input type=“file” name=“filename”><br>
		<input type=“submit” value=“파일 올리기”>
	</form>
</body>
</html>
```

#### fileupload04_process.jsp
```java
<%@ page contentType=“text/html; charset=utf-8” %>
<%@ page import=“org.apache.commons.fileupload.*” %>
<%@ page import=“org.apache.commons.fileupload.disk.*”%>
<%@ page import=“org.apache.commons.fileupload.servlet,*” %>
<%@ page import=“java.io.*” %>
<%@ page import=“java.util.*” %>
<html>
<body>
<%
	String path = “D:\\upload”;
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);

	upload.setSizeMax(1000000);

	List<FileItem> items = upload.parseRequest(request);
	Iterator<FileItem> params = items.iterator();

	while(params.hasNext()) {
		FileItem item = params.next();
		if (item.isFormField()) {
			String name = item.getFieldName();
			String value = item.getString(“UTF-8”);
			out.println(name + “ = ” + value + “<br>”);
		} else {
			String fileFieldName = item.getFieldName();
			String fileName = item.getName();
			String contentType = item.getContentType();

			fileName = fileName.substring(fileName.lastIndexOf(“\\”) + 1);
			long fileSize = item.getSize();
			
			File file = new File(path + “/” + fileName);
			item.write(file);			

			out.println(“---------------<br>”);
			out.println(“요청 파라미터 이름 : ” + fileFieldName + “<br>”);
			out.println(“저장 파일 이름 : ” + fileName + “<br>”);
			out.println(“파일 콘텐츠 유형 : ” + contentType + “<br>”);
			out.println(“파일 크기 : ” + fileSize);
		}
	}
%>
</body>
</html>
```

# 파일다운로드
```java
package com.core;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;


public class FileManager {

	private static final int MAX_UPLOAD_SIZE = 10000000;
	
	/**
	 * 파일 업로드
	 * 
	 * @param request
	 */
	public static HashMap<String, Object> upload(HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			String path = request.getServletContext().getRealPath(File.separator + "resources" + File.separator + "/upload");
			String uploadURL  = request.getServletContext().getContextPath()+ "/resources/upload";
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); // 한글 파일 깨짐 방지
			upload.setSizeMax(MAX_UPLOAD_SIZE);
		
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> params = items.iterator();
			while(params.hasNext()) {
				FileItem item = params.next();
				String key = item.getFieldName();
				if (item.isFormField()) { // 일반 양식 데이터 
					String value = item.getString("UTF-8");
					result.put(key, value);
				} else { //파일 데이터
					HashMap<String, String> fileInfo = new HashMap<>();
					String fNm = item.getName();
					String contentType = item.getContentType();
					
					fNm = System.currentTimeMillis() + "_" + fNm.substring(fNm.lastIndexOf(File.separator) + 1);
					long fileSize = item.getSize();
					
					String uploaded = path + File.separator + fNm;
					String uploadedURL = uploadURL + "/" + fNm;
					File file = new File(uploaded);
					item.write(file);
					fileInfo.put("fileName", fNm);
					fileInfo.put("contentType", contentType);
					fileInfo.put("fileSize", String.valueOf(fileSize));
					fileInfo.put("uploadedPath", uploaded);
					fileInfo.put("uploadedURL", uploadedURL);
					
					result.put(key, fileInfo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 파일 다운로드 
	 * 
	 * @param request
	 * @param filePath
	 * @throws UnsupportedEncodingException 
	 */
	public static void download(HttpServletResponse response, String filePath)  {
		if (response == null || filePath == null || filePath.trim().equals("")) 
			return;
		
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
		File file = new File(filePath); 
		
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
			OutputStream out = response.getOutputStream();
			
			response.setHeader("Content-Description", "File Transfer");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859_1"));
			response.setHeader("Content-Type", "application/octet-stream");
			response.setIntHeader("Expires", 0);
			response.setHeader("Cache-Control", "must-revalidate");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-Length", String.valueOf(file.length()));
			
			int i;
			while((i = bis.read()) != -1) {
				out.write(i);
			}

			out.flush();
		} catch (IOException | IllegalStateException  e) {
			e.printStackTrace();
		}
	}
}
```

* * * 

# 게시판 프로젝트(파일업로드, 다운로드 구현)

- [예제 소스](https://github.com/yonggyo1125/Board_JSP/tree/fileupload_download)

