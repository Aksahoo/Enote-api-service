package com.ensat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ensat.dto.FavouriteNoteDto;
import com.ensat.dto.NoteListDto;

import com.becoder.util.CommonUtil;

import com.ensat.dto.NotesDto;
import com.ensat.execption.ResourceNotFoundException;
import com.ensat.service.NotesService;

import com.becoder.util.CommonUtil;

@RestController
@RequestMapping("/notes")
public class NotesController {

	@Autowired
	private NotesService notesService;

	@PostMapping("/rty")
	public ResponseEntity<?> saveNotes(@RequestBody NotesDto notesDto) throws Exception {
		Boolean saveNotes = notesService.savenote(notesDto);
		if (saveNotes) {
			return CommonUtil.createBuildResponseMessage("saved successfully", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorResponseMessage("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listnote")
	public ResponseEntity<?> getlistNotes() {
		List<NoteListDto> notes = notesService.getNotesList();
		if (CollectionUtils.isEmpty(notes)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletFile(@PathVariable Integer id) throws ResourceNotFoundException {
		notesService.DeletFile(id);
		return CommonUtil.createBuildResponse("Note Deleted Sucessfully", HttpStatus.OK);

	}

	@GetMapping("/fav/{noteId}")
	public ResponseEntity<?> makefavoriteNote(@PathVariable Integer noteId) throws Exception {
		notesService.favoriteNotes(noteId);
		return CommonUtil.createBuildResponseMessage("Notes added Favorite", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findByid(@PathVariable Integer id) throws ResourceNotFoundException {
		NotesDto findByIdnotes = notesService.findByIdnotes(id);
		return CommonUtil.createBuildResponse(findByIdnotes, HttpStatus.OK);
	}

	@GetMapping("/unfav/{favId}")
	public ResponseEntity<?> makeUnfavourite(@PathVariable Integer favId) throws Exception {
		notesService.unfavoriteNotes(favId);

		return CommonUtil.createBuildResponseMessage("Notes Remove Favorite", HttpStatus.CREATED);

	}

	@GetMapping("/favlist")
	public ResponseEntity<?> getfavouriteList() {

		List<FavouriteNoteDto> favouriteList = notesService.getFavouriteList();
		if (CollectionUtils.isEmpty(favouriteList)) {
			ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(favouriteList, HttpStatus.OK);

	}

}
