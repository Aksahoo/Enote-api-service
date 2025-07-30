package com.ensat.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ensat.dto.NotesDto;
import com.ensat.entity.Notes;
import com.ensat.repository.NoteRepository;
import com.ensat.service.UploadfileDownloadNotesService;

@Service
public class UploadfileDownloadNotesServiceImpl implements UploadfileDownloadNotesService {
	@Autowired
	private NoteRepository notesRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public boolean fileupload(NotesDto Ntodto, MultipartFile file) throws Exception {
		
		Notes notes = mapper.map(Ntodto, Notes.class);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}

}
