package com.ksw.dao.forObject.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.NoteView;

@Repository
public interface NoteViewRepository extends JpaRepository<NoteView, Integer> {
}
