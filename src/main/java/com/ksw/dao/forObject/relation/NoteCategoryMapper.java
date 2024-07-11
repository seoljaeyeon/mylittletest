package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.NoteCategory;

@Mapper
public interface NoteCategoryMapper {

	@Insert(""
			+ "INSERT INTO noteCategory "
			+ "(categoryNo, noteNo) "
			+ "VALUES "
			+ "(#{category.categoryNo}, #{note.noteNo})")
	void insert(NoteCategory noteCategory);
	
    @Select("SELECT noteNo FROM noteCategory "
    		+ "WHERE categoryNo = #{categoryNo}")
    List<Integer> findNoteNosByCategoryNo(int categoryNo);
	
}
