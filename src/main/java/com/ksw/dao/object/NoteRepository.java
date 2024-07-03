package com.ksw.dao.object;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.jpa.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

    @Query("SELECT n.noteNo FROM Note n WHERE n.categoryNo = :categoryNo")
    List<Integer> findNoteNosByCategory(@Param("categoryNo") Integer categoryNo);

    @Query("SELECT n FROM Note n WHERE n.noteNo IN :noteNos")
    List<Note> findNotesByNoteNos(@Param("noteNos") List<Integer> noteNos);	

	
}
