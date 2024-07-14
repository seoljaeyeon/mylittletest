package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.User;
import com.ksw.object.relation.NoteUser;

@Mapper
public interface NoteUserMapper {

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
