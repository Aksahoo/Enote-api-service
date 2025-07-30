package com.ensat.serviceimpl;

	import java.io.File;
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;

	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Service;
	import org.springframework.web.multipart.MultipartFile;

import com.ensat.service.Fileservice;

	
	@Service
	public class FileServiceImpl implements Fileservice {

	
	 // @Value("${file.upload.path}") private String uploadPath;
	 
		  @Value("${file.upload-dir}") private String uploadDir;
		 

		@Override
		public Boolean uploadFile(MultipartFile file) throws IOException {

			String fileName = file.getOriginalFilename();
			File savefile = new File(uploadDir);

			if (!savefile.exists()) {
				savefile.mkdir();
			}
			String storePath = uploadDir.concat(fileName);

			long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
			if (upload != 0) {
				return true;
			}
			return false;
		}
		
}
