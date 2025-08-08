package com.ensat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensat.entity.FavouriteNote;

import jakarta.transaction.Transactional;

public interface FavouriteNoteRepository extends JpaRepository<FavouriteNote, Integer> {

	@Transactional
    @Modifying
    @Query("DELETE FROM FavouriteNote f WHERE f.note.id = :id")
    void deleteByNoteId(@Param("id") Integer id);
	
	Boolean existsByNoteId(Integer id);
	public Optional<FavouriteNote> findByNoteId(Integer id);
}