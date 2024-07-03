package com.ksw.dao.combined;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.dto.forObject.object.CategoryDTO;
import com.ksw.dto.forObject.object.FileDTO;
import com.ksw.dto.forObject.object.NoteDTO;
import com.ksw.dto.forObject.object.ReplyDTO;
import com.ksw.dto.forObject.object.UserDTO;
import com.ksw.dto.function.QuestionDTO;
import com.ksw.object.vo.object.ReplyVO;

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

    @Select("SELECT r.replyNo, r.createdAt, r.isActive, "
    		+ "r.parentReply, r.replyContent, r.updatedAt, "
    		+ "u.userNo, u.userName " +
            "FROM noteReply nr " +
            "JOIN reply r ON nr.replyNo = r.replyNo " +
            "JOIN replyUser ru ON r.replyNo = ru.replyNo " +
            "JOIN user u ON ru.userNo = u.userNo " +
            "WHERE nr.noteNo = #{noteNo}")
    List<ReplyDTO> getRepliesByNoteNo(int noteNo);
    
    @Select("SELECT COUNT(*) FROM noteView nv JOIN viewUserNote vun "
    		+ "ON nv.noteViewNo = vun.noteViewNo "
    		+ "WHERE vun.noteNo = #{noteNo}")
    int getViewCountByNoteNo(int noteNo);
    
    @Select("SELECT COUNT(*) FROM favorite fv JOIN favoriteNote fvn "
    		+ "ON fv.favorite = fvn favoriteNote "
    		+ "WHERE fvn.noteNo = #{noteNo}")
    int getfavoriteCountByNoteNo(int noteNo);
    
    
}