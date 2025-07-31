package com.ensat.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface Fileservice {
	public Boolean uploadFile(MultipartFile file) throws IOException;
}
