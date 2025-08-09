
package com.ensat.service;


import java.util.List;

import com.ensat.dto.FavouriteNoteDto;
import com.ensat.dto.NoteListDto;
import com.ensat.dto.NotesDto;
import com.ensat.execption.ResourceNotFoundException;


public interface NotesService {
	
	public boolean savenote(NotesDto noteDto);
	
public List<NoteListDto> getNotesList();

public void DeletFile(Integer id) throws ResourceNotFoundException;

//public void favoriteNotes(Integer id) throws Exception ;
public NotesDto findByIdnotes(Integer id) throws ResourceNotFoundException ;
public FavouriteNoteDto favoriteNotes(Integer noteId) throws Exception;
public void unfavoriteNotes(Integer id) throws Exception;
public List<FavouriteNoteDto> getFavouriteList();
}
