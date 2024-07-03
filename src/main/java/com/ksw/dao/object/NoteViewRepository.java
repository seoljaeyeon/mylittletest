package com.ksw.dao.object;

import com.ksw.object.entity.jpa.NoteView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteViewRepository extends JpaRepository<NoteView, Integer> {
}
