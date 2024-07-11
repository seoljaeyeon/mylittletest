package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.NoteView;

public interface NoteViewMapper {

    @Insert("INSERT INTO noteView (viewNo, noteNo) VALUES (#{viewNo}, #{noteNo})")
    void insertNoteView(NoteView noteView);

    @Select("SELECT * FROM noteView WHERE viewNo = #{viewNo}")
    @ResultMap("NoteViewResultMap")
    NoteView getNoteViewByViewNo(@Param("viewNo") int viewNo);

    @Select("SELECT * FROM noteView")
    @ResultMap("NoteViewResultMap")
    List<NoteView> getAllNoteViews();
}