package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlarmRelationMapper {
	
	@Select("SELECT u.nickname, "
	        + "ar.makerNo, "
	        + "ar.noteNo, "
	        + "ar.replyNo, "
	        + "b.alarmType, "
	        + "b.createdAt, "
	        + "b.isRead, "
	        + "c.categoryTitle "
	        + "FROM alarmRelation ar "
	        + "JOIN alarm b ON ar.alarmNo = b.alarmNo "
	        + "JOIN user u ON ar.makerNo = u.userNo "
	        + "LEFT JOIN noteCategory nc ON ar.noteNo = nc.noteNo "
	        + "LEFT JOIN category c ON nc.categoryNo = c.categoryNo "
	        + "WHERE ar.receiverNo = #{userNo} "
	        + "AND ar.receiverNo != ar.makerNo "
	        + "ORDER BY b.createdAt "
	        + "LIMIT #{limit} OFFSET #{offset}")
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
