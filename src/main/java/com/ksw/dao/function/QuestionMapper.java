package com.ksw.dao.function;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.relation.ReplyUserDTO;

@Mapper
public interface QuestionMapper {

    @Select("SELECT * FROM user u JOIN noteUser nu "
    		+ "ON u.userNo = nu.userNo "
    		+ "WHERE nu.noteNo = #{noteNo}")
    UserDTO getUserByNoteNo(int noteNo);

    @Select("SELECT * FROM category c JOIN noteCategory nc "
    		+ "ON c.categoryNo = nc.categoryNo "
    		+ "WHERE nc.noteNo = #{noteNo}")
    CategoryDTO getCategoryByNoteNo(int noteNo);

    @Select("SELECT * FROM note "
    		+ "WHERE noteNo = #{noteNo}")
    NoteDTO getNoteByNoteNo(int noteNo);

    @Select("SELECT * FROM file f JOIN fileNote fn "
    		+ "ON f.fileNo = fn.fileNo "
    		+ "WHERE fn.noteNo = #{noteNo}")
    FileDTO getFileByNoteNo(int noteNo);

    @Select("SELECT "
    		+ "r.replyNo, "
    		+ "r.createdAt, "
    		+ "r.isActive, "
    		+ "r.parentReply, "
    		+ "r.replyContent, "
    		+ "r.updatedAt, " 
    		+ "u.userNo, "
    		+ "u.userId, "
    		+ "u.nickname, "
    		+ "u.email, "
    		+ "u.isActive as userIsActive, "
    		+ "u.type as userType, "
    		+ "u.createdAt as userCreatedAt " 
    		+ "FROM noteReply nr " 
    		+ "JOIN reply r ON nr.replyNo = r.replyNo " 
    		+ "JOIN replyUser ru ON r.replyNo = ru.replyNo " 
    		+ "JOIN user u ON ru.userNo = u.userNo " 
    		+ "WHERE nr.noteNo = #{noteNo}")
    @Results({
        @Result(property = "replyDTO.replyNo", column = "replyNo"),
        @Result(property = "replyDTO.createdAt", column = "createdAt"),
        @Result(property = "replyDTO.isActive", column = "isActive"),
        @Result(property = "replyDTO.parentReply", column = "parentReply"),
        @Result(property = "replyDTO.replyContent", column = "replyContent"),
        @Result(property = "replyDTO.updatedAt", column = "updatedAt"),
        @Result(property = "userDTO.userNo", column = "userNo"),
        @Result(property = "userDTO.userId", column = "userId"),
        @Result(property = "userDTO.nickname", column = "nickname"),
        @Result(property = "userDTO.email", column = "email"),
        @Result(property = "userDTO.isActive", column = "userIsActive"),
        @Result(property = "userDTO.type", column = "userType"),
        @Result(property = "userDTO.createdAt", column = "userCreatedAt")
    })
    List<ReplyUserDTO> getRepliesByNoteNo(int noteNo);
    
    @Select("SELECT COUNT(*) FROM noteView "
    		+ "WHERE noteNo = #{noteNo}")
    int getViewCountByNoteNo(int noteNo);
    
    @Select("SELECT COUNT(*) FROM favorite fv JOIN favoriteNote fvn "
    		+ "ON fv.favoriteNo = fvn.favoriteNo "
    		+ "WHERE fvn.noteNo = #{noteNo}")
    int getfavoriteCountByNoteNo(int noteNo);

    @Select("SELECT COUNT(*) > 0 " +
            "FROM favoriteNote " +
            "WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    Boolean getIsFavoriteByNoteNoAndUserNo(
    		@Param("noteNo") Integer noteNo, 
    		@Param("userNo") Integer userNo);
    
    @Select("SELECT u.* " +
            "FROM user u " +
            "JOIN noteUser nu ON u.userNo = nu.userNo " +
            "WHERE nu.noteNo = #{noteNo}")
    UserDTO getWriterByNoteNo(@Param("noteNo") Integer noteNo);
}