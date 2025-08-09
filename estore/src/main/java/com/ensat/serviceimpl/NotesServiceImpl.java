
package com.ensat.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensat.dto.FavouriteNoteDto;
import com.ensat.dto.NoteListDto;
import com.ensat.dto.NotesDto;
import com.ensat.entity.FavouriteNote;
import com.ensat.entity.Notes;
import com.ensat.execption.ExistDataException;
import com.ensat.execption.ResourceNotFoundException;
import com.ensat.repository.FavouriteNoteRepository;
import com.ensat.repository.NoteRepository;
import com.ensat.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {
	@Autowired
	private FavouriteNoteRepository favouriteNoteRepository;

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
		return findAll.stream().map(note -> mapper.map(note, NoteListDto.class)).toList();

	}

	@Override
	public void DeletFile(Integer id) throws ResourceNotFoundException {
		Notes notes = noterepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notes id invalid ! Not Found"));

		notes.setIsDeleted(true);
		notes.setDeleteon(new Date());
		noterepository.save(notes);
	}

//	@Override
	public NotesDto findByIdnotes(Integer id) throws ResourceNotFoundException {
		Notes notes = noterepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notes id invalid ! Not Found"));
		NotesDto notesDto = mapper.map(notes, NotesDto.class);

		return notesDto;
	}

	public FavouriteNoteDto favoriteNotes(Integer noteId) throws Exception {
		Notes notes = noterepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Notes Not found & Id invalid"));
		Boolean exist = favouriteNoteRepository.existsByNoteId(noteId);
		if (exist) {
			throw new ExistDataException("note alredy favourite ");
		}
		FavouriteNote favouriteNote = FavouriteNote.builder().note(notes).build();
		FavouriteNote savefav = favouriteNoteRepository.save(favouriteNote);
		return mapper.map(savefav, FavouriteNoteDto.class);
	}

	public void unfavoriteNotes(Integer id) throws Exception {
		// FavouriteNote favouriteNote = favouriteNoteRepository.findById(favId)
		Optional<FavouriteNote> favouriteNoteOpt = favouriteNoteRepository.findByNoteId(id);

		FavouriteNote fav = favouriteNoteOpt
				.orElseThrow(() -> new ResourceNotFoundException("Notes id invalid! Not Found"));

		favouriteNoteRepository.delete(fav);
	}
	// favouriteNoteRepository.deleteByNoteId(id);

	@Override
	public List<FavouriteNoteDto> getFavouriteList() {
		List<FavouriteNote> unfavourite = favouriteNoteRepository.findAll();
		return unfavourite.stream().map(fn -> mapper.map(fn, FavouriteNoteDto.class)).toList();

	}
