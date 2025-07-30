package com.ensat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ensat.dto.NotesDto;
import com.ensat.service.UploadfileDownloadNotesService;

@RestController
public class FileUploadingDownloadingNotes {
	@Autowired
	private UploadfileDownloadNotesService uploadfileDownloadNotesService;

	@PostMapping("/uploadu")
	public ResponseEntity<?> uploadfilewithdata(@RequestBody NotesDto NoteDto, MultipartFile file ) throws Exception{
		boolean fileupload = uploadfileDownloadNotesService.fileupload(NoteDto, file);
		if(fileupload) {
			return new ResponseEntity("uploadfile sucessfully", HttpStatus.OK);
		}
		return new ResponseEntity("File upload fail", HttpStatus.BAD_REQUEST);
		
	}
}
