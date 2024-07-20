package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserGoalMapper {

	@Insert("INSERT INTO userGoal (userNo, goalNo) "
			+ "VALUES (#{userNo}, #{goalNo}) "
			+ "")
	Integer insert(@Param("userNo") Integer userNo, @Param("goalNo") Integer goalNo);
}
