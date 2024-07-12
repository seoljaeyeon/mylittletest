package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.NoteView;

public interface NoteViewMapper {

    @Insert("INSERT INTO noteView (viewNo, noteNo) VALUES (#{viewNo}, #{noteNo})")
    void insertNoteView(NoteView noteView);
    
    @Select("SELECT n.noteNo, n.createdAt AS noteCreatedAt, n.isActive, n.noteAnswer, n.noteContent, n.noteHint, n.noteTitle, n.updatedAt, n.noteCommentary, v.viewNo, v.createdAt as viewCreatedAt " +
            "FROM note n " +
            "JOIN noteCategory nc ON n.noteNo = nc.noteNo " +
            "JOIN noteView nv ON n.noteNo = nv.noteNo " +
            "JOIN view v ON nv.viewNo = v.viewNo " +
            "WHERE nc.categoryNo = #{categoryNo} AND nc.noteNo = #{noteNo} AND nv.userNo = #{userNo}")
    @Results({
    	@Result(property = "note.createdAt", column="noteCreatedAt"),
    	@Result(property = "view.createdAt", column="viewCreatedAt")
    })
    List<NoteView> getHistory(@Param("categoryNo") Integer categoryNo,
                              @Param("noteNo") Integer noteNo,
                              @Param("userNo") Integer userNo);
}