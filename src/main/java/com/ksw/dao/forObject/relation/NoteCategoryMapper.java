package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.Category;
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
    
    @Select("SELECT c.* FROM category c "
    		+ "join categoryView cv "
    		+ "on cv.categoryNo = c.categoryNo "
    		+ "where cv.noteNo = #{noteNo}")
    @Results({
    	@Result(property = "category.categoryNo", column = "categoryNo"),
    	@Result(property = "category.categoryTitle", column = "categoryTitle"),
    	@Result(property = "category.isActive", column = "isActive"),
    	@Result(property = "category.createdAt", column = "createdAt")
    	})
    NoteCategory getNoteCategorybynoteNo(Integer noteNo);
    
    @Select(""
    		+ "SELECT c.* FROM category c JOIN noteCategory nc "
    		+ "ON c.categoryNo = nc.categoryNo "
    		+ "WHERE nc.noteNo = #{noteNo} ")
    Category getCategorybyNoteNo(Integer noteNo);
	
}
