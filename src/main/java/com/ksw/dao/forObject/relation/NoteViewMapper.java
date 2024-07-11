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
    
    @Select("SELECT n.*, v.* " +
            "FROM note n " +
            "JOIN noteCategory nc ON n.noteNo = nc.noteNo " +
            "JOIN noteView nv ON n.noteNo = nv.noteNo " +
            "JOIN view v ON nv.viewNo = v.viewNo " +
            "WHERE nc.categoryNo = #{categoryNo} AND nc.noteNo = #{noteNo} AND nv.userNo = #{userNo}")
    List<NoteView> getHistory(@Param("categoryNo") Integer categoryNo,
                              @Param("noteNo") Integer noteNo,
                              @Param("userNo") Integer userNo);
}