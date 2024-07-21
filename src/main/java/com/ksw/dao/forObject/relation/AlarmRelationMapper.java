package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AlarmRelationMapper {

	@Insert("INSERT INTO alarmRelation "
			+ "(alarmNo, receiverNo, makerNo, noteNo, replyNo) "
			+ "VALUES "
			+ "(#{alarmNo}, #{receiverNo}, #{makerNo}, #{noteNo}, #{replyNo}) ")
	Integer insert(
			@Param("alarmNo") Integer alarmNo,
			@Param("receiverNo") Integer receiverNo,
			@Param("makerNo") Integer makerNo,
			@Param("noteNo") Integer noteNo,
			@Param("replyNo") Integer replyNo);
	
}
