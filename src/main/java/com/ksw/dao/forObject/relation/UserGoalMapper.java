package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.Goal;

@Mapper
public interface UserGoalMapper {
	
	@Select("SELECT g.* FROM goal g JOIN userGoal ug ON g.goalNo = ug.goalNo " +
	        "WHERE ug.userNo = #{userNo} " +
	        "AND DATE(ug.createdAt) = CURDATE()")
	Goal getGoalByUserNo(@Param("userNo") Integer userNo);

	@Insert("INSERT INTO userGoal (userNo, goalNo) "
			+ "VALUES (#{userNo}, #{goalNo}) "
			+ "")
	Integer insert(@Param("userNo") Integer userNo, @Param("goalNo") Integer goalNo);
	
	@Select("SELECT nv.createdAt, COALESCE(nv.answerCount, 0) as answerCount, g.*, ug.userNo " +
	        "FROM ( " +
	        "    SELECT DATE(createdAt) as createdAt, COUNT(answerNo) as answerCount, userNo " +
	        "    FROM answerHistory " +
	        "    WHERE userNo = #{userNo} " +
	        "    AND createdAt >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
	        "    GROUP BY DATE(createdAt), userNo " +
	        ") nv " +
	        "LEFT JOIN userGoal ug ON DATE(ug.createdAt) = nv.createdAt AND ug.userNo = nv.userNo " +
	        "LEFT JOIN goal g ON ug.goalNo = g.goalNo " +
	        "WHERE nv.userNo = #{userNo} " +
	        "ORDER BY nv.createdAt DESC")
	List<Map<String, Object>> getRecentGoalsWithAnswerCounts(@Param("userNo") Integer userNo);


}
