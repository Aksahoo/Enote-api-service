package com.ensat.service;

import org.springframework.web.multipart.MultipartFile;

import com.ensat.dto.NotesDto;

public interface UploadfileDownloadNotesService {
	
	public boolean fileupload(NotesDto Ntodto, MultipartFile file) throws Exception;

}
