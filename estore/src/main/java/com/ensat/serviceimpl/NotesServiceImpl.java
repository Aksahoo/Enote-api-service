package com.ensat.serviceimpl;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ensat.dto.NoteListDto;
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
	public List<NoteListDto> getNotesList() {
		List<Notes> findAll = noterepository.findAll();
		return findAll.stream().map(note->mapper.map(note, NoteListDto.class)).toList();
				
	}

	

	

	



	
}