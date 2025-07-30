package com.ensat.serviceimpl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ensat.dto.NotesDto;
import com.ensat.entity.Notes;
import com.ensat.repository.NoteRepository;
import com.ensat.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {
     
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private NoteRepository noterepository;
	@Override
	public boolean savenote(NotesDto noteDto) {
		Notes notes = mapper.map(noteDto, Notes.class);
		noterepository.save(notes);
		return true;
	}

	@Override
	public Boolean saveNotes(NotesDto notesDto, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	

	



	
}