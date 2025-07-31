package com.ensat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensat.entity.Notes;

public interface NoteRepository extends JpaRepository<Notes, Integer>{

}
