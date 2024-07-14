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
    
    @Select(""
    		+ "SELECT COUNT(*) FROM "
    		+ "noteView nv JOIN noteCategory nc ON nv.noteNo = nc.noteNo "
    		+ "WHERE nv.userNo = #{userNo} AND nc.categoryNo = #{categoryNo} "
    		+ "AND DATE(nv.createdAt) = CURDATE()") // 날짜 조건 - 오늘 
	Integer getTodayHistory(@Param("categoryNo") Integer categoryNo,
                              @Param("userNo") Integer userNo);
}