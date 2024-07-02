package com.ksw.dao.combined;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.dto.forObject.object.CategoryDTO;
import com.ksw.dto.forObject.object.FileDTO;
import com.ksw.dto.forObject.object.NoteDTO;
import com.ksw.dto.forObject.object.UserDTO;
import com.ksw.dto.function.QuestionDTO;
import com.ksw.object.vo.object.ReplyVO;

@Mapper
public interface QuestionMapper {

    @Select("SELECT * FROM user u JOIN noteUser nu ON u.userNo = nu.userNo WHERE nu.noteNo = #{noteNo}")
    UserDTO getUserByNoteNo(int noteNo);

    @Select("SELECT * FROM category c JOIN noteCategory nc ON c.categoryNo = nc.categoryNo WHERE nc.noteNo = #{noteNo}")
    CategoryDTO getCategoryByNoteNo(int noteNo);

    @Select("SELECT * FROM note WHERE noteNo = #{noteNo}")
    NoteDTO getNoteByNoteNo(int noteNo);

    @Select("SELECT * FROM file f JOIN fileNote fn ON f.fileNo = fn.fileNo WHERE fn.noteNo = #{noteNo}")
    FileDTO getFileByNoteNo(int noteNo);

    @Select("SELECT * FROM reply r JOIN noteReply nr ON r.replyNo = nr.replyNo WHERE nr.noteNo = #{noteNo}")
    ReplyVO getReplyByNoteNo(int noteNo);
}