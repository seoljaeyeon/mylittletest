package com.ksw.dao.object;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.jpa.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

}
