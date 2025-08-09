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
public class FileServiceImpl implements Fileservice{
	
	@Value("${file.upload.path}")
	private String uploadPath;
	@Override
	public Boolean uploadFile(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		String fileName = file.getOriginalFilename();
		File savefile = new File(uploadPath);

		if (!savefile.exists()) {
			savefile.mkdir();
		}
		String storePath = uploadPath.concat(fileName);

		long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
		if (upload != 0) {
			return true;
		}
		return false;
	}

}
