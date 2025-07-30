package com.ensat.service;


import org.springframework.web.multipart.MultipartFile;

import com.ensat.dto.NotesDto;


public interface NotesService {
	
	public boolean savenote(NotesDto noteDto);
	public Boolean saveNotes(NotesDto notesDto, MultipartFile file) throws Exception;
	

}
