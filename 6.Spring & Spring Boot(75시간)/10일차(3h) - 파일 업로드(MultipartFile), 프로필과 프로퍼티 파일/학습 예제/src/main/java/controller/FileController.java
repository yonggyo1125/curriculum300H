package controller;

import java.io.IOException;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import spring.FileService;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/upload")
	public String form() {
		return "file/upload";
	}
	
	@ResponseBody
	@PostMapping("/upload")
	public void  process(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		if (file != null) {
			String uploadDir = request.getServletContext().getRealPath("/") + "/../resources/static/upload";
			File _uploadDir = new File(uploadDir);
			if (!_uploadDir.isDirectory()) {
				_uploadDir.mkdir();
			}
			
			fileService.upload(uploadDir, file);
		}
	}
}
