package com.ksw.dao.relation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.mybatis.ViewUserNote;

@Mapper
public interface ViewUserNoteMapper {

    @Select("SELECT * FROM viewUserNote WHERE userNo = #{userNo}")
    List<ViewUserNote> findByUserNo(int userNo);
	
}
