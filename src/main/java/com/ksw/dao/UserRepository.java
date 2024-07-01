package com.ksw.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ksw.object.entity.jpa.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO user ("
			+ "user_id, "
			+ "password, "
			+ "nickname, "
			+ "email, "
			+ "security_question, "
			+ "security_answer, "
			+ "is_active, "
			+ "type, "
			+ "created_at) "
			+ "VALUES ("
			+ ":userId, "
			+ "SHA2(:password, 256), "
			+ ":nickname, "
			+ ":email, "
			+ ":securityQuestion, "
			+ ":securityAnswer, "
			+ ":isActive, "
			+ ":type, "
			+ ":createdAt)", 
			nativeQuery = true)
	public void join(
			@Param("userId") String userId, 
			@Param("password") String password,
			@Param("nickname") String nickname, 
			@Param("email") String email,
			@Param("securityQuestion") Integer securityQuestion, 
			@Param("securityAnswer") String securityAnswer,
			@Param("isActive") Boolean isActive, 
			@Param("type") Integer type, 
			@Param("createdAt") Timestamp createdAt);
	
	@Query(value = "SELECT * FROM user "
			+ "WHERE user_id = :userId "
			+ "AND password = SHA2(:password, 256)", 
            nativeQuery = true)
    User findByUserIdAndPassword(
    		@Param("userId") String userId, 
    		@Param("password") String password);
}
