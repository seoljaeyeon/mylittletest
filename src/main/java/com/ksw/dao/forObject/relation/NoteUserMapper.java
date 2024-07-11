package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ksw.object.relation.NoteUser;

@Mapper
public interface NoteUserMapper {

	@Insert(""
			+ "INSERT INTO noteUser "
			+ "(noteNo, userNo) "
			+ "VALUES "
			+ "(#{note.noteNo}, #{user.userNo})")
	void insert(NoteUser noteUser);
}
