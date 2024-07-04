package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.ViewUserNote;

@Mapper
public interface ViewUserNoteMapper {

    @Select("SELECT * FROM viewUserNote WHERE userNo = #{userNo}")
    List<ViewUserNote> findByUserNo(int userNo);
	
    @Select("SELECT noteNo FROM viewUserNote WHERE userNo = #{userNo}")
    List<Integer> findViewedNoteNosByUserNo(int userNo);
}
