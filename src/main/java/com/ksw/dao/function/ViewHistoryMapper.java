package com.ksw.dao.function;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.ViewUserNote;

@Mapper
public interface ViewHistoryMapper {

    @Select("SELECT * FROM viewUserNote WHERE userNo = #{userNo}")
    List<ViewUserNote> findByUserNo(int userNo);
}
