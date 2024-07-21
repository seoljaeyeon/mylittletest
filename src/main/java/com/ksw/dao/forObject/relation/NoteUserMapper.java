package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.User;
import com.ksw.object.relation.NoteUser;

@Mapper
public interface NoteUserMapper {
	
	@Select("SELECT DISTINCT(c.categoryNo) "
			+ "FROM category c "
			+ "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo "
			+ "JOIN noteUser nu ON nu.noteNo = nc.noteNo "
			+ "WHERE nu.userNo = #{userNo} ")
	List<Integer> getCategoryListByUserNo(@Param("userNo") Integer userNo);
	
	@Select("SELECT c.categoryTitle, n.noteTitle, n.createdAt, "
	        + "COUNT(CASE WHEN fn.favoriteType = 2 THEN 1 ELSE NULL END) AS favorite_count, "
			+ "count(r.replyNo) as reply_count"
	        + "FROM note n "
	        + "JOIN noteUser nu ON nu.noteNo = n.noteNo "
	        + "JOIN noteCategory nc ON nc.noteNo = n.noteNo "
	        + "JOIN category c ON c.categoryNo = nc.categoryNo "
	        + "LEFT JOIN favoriteNote fn ON fn.noteNo = n.noteNo "
	        + "LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo "
	        + "LEFT JOIN reply r ON r.noteNo = n.noteNo "
	        + "JOIN noteView nv ON nv.noteNo = n.noteNo "
	        + "JOIN view v ON v.viewNo = nv.viewNo "
			+ "WHERE nu.userNo = #{userNo} "
			+ "GROUP BY c.categoryTitle, n.noteTitle, n.createdAt "
			+ "ORDER BY n.createdAt DESC ")
	List<Map<String,Object>> getNoteListByUserNo(@Param("userNo") Integer userNo);

	@Insert(""
			+ "INSERT INTO noteUser "
			+ "(noteNo, userNo) "
			+ "VALUES "
			+ "(#{note.noteNo}, #{user.userNo})")
	void insert(NoteUser noteUser);
	
	@Select(""
			+ "SELECT u.* "
			+ "FROM user u JOIN noteUser nu on u.userNo = nu.userNo "
			+ "WHERE nu.noteNo = #{noteNo}" )
	User getUserByNoteNo(Integer noteNo); 
}
