package com.ksw.dao.relation;

import com.ksw.object.entity.mybatis.AnswerHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnswerHistoryMapper {

    @Insert("INSERT INTO answerHistory (noteNo, answerNo, userNo, createdAt, updatedAt) VALUES (#{noteNo}, #{answerNo}, #{userNo}, #{createdAt}, #{updatedAt})")
    void insert(AnswerHistory answerHistory);

    @Select("SELECT * FROM answerHistory WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
    List<AnswerHistory> findByNoteNoAndUserNo(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);

    @Select("SELECT * FROM answerHistory WHERE noteNo = #{noteNo}")
    List<AnswerHistory> findByNoteNo(Integer noteNo);

    @Select("SELECT * FROM answerHistory")
    List<AnswerHistory> findAll();

    @Update("UPDATE answerHistory SET answerNo = #{answerNo}, createdAt = #{createdAt}, updatedAt = #{updatedAt} WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
    void update(AnswerHistory answerHistory);

    @Delete("DELETE FROM answerHistory WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
    void delete(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);
}