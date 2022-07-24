package spring;

import java.io.*;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileService {
		
	/**
	 * 파일 업로드 처리
	 * 
	 */
	public boolean upload(String uploadDir, MultipartFile file)  {
		if (uploadDir.isBlank() || file == null) {
			return false;
		}
		
		/** 업로드 처리 S */
		UUID uuid = UUID.randomUUID();
		String originalFilename = file.getOriginalFilename();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String savedFileName = uuid.toString() + extension;
		String uploadPath = uploadDir + File.separator + savedFileName;
		try (FileOutputStream fos = new FileOutputStream(uploadPath);
			BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			bos.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		/** 업로드 처리 E */
		
		return true;
	}
}
