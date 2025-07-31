package com.ensat.service;


import java.util.List;


import com.ensat.dto.NoteListDto;
import com.ensat.dto.NotesDto;


public interface NotesService {
	
	public boolean savenote(NotesDto noteDto);
	
public List<NoteListDto> getNotesList();
}
