package com.ensat.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.ensat.dto.NoteListDto;


import com.becoder.util.CommonUtil;

import com.ensat.dto.NotesDto;
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
	public ResponseEntity<?> getlistNotes(){
		List<NoteListDto> notes = notesService.getNotesList();
		if(CollectionUtils.isEmpty(notes)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}	
}
