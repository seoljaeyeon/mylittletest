package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlarmRelationMapper {
	
	@Select("SELECT u.nickname, a.makerNo, a.noteNo, a.replyNo, "
			+ "b.alarmType, b.createdAt, b.isRead " +
	        "FROM alarmRelation a " +
	        "JOIN alarm b ON a.alarmNo = b.alarmNo "
	        + "JOIN user u ON a.makerNo = u.userNo " +
	        "WHERE a.receiverNo = #{userNo} " +
	        "LIMIT #{limit} OFFSET #{offset}")
	List<Map<String, Object>> getAlarmDetail(
	        @Param("userNo") Integer userNo, 
	        @Param("limit") Integer limit,
	        @Param("offset") Integer offset);


	@Select("SELECT ar.alarmNo FROM alarmRelation ar " +
	        "JOIN alarm a ON ar.alarmNo = a.alarmNo " +
	        "WHERE ar.receiverNo = #{userNo} " +
	        "ORDER BY a.createdAt DESC")
	List<Integer> getAlarmListOrderByCreatedAt(@Param("userNo") Integer userNo);

	
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
