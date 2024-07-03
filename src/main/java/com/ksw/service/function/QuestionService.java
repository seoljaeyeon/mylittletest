package com.ksw.service.function;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ksw.dao.combined.QuestionMapper;
import com.ksw.dao.object.CategoryRepository;
import com.ksw.dao.object.FileRepository;
import com.ksw.dao.object.NoteRepository;
import com.ksw.dao.object.UserRepository;
import com.ksw.dao.relation.FileNoteMapper;
import com.ksw.dao.relation.NoteCategoryMapper;
import com.ksw.dao.relation.NoteUserMapper;
import com.ksw.dto.forObject.object.CategoryDTO;
import com.ksw.dto.forObject.object.FileDTO;
import com.ksw.dto.forObject.object.NoteDTO;
import com.ksw.dto.forObject.object.ReplyDTO;
import com.ksw.dto.forObject.object.UserDTO;
import com.ksw.dto.function.QuestionDTO;
import com.ksw.object.entity.jpa.Category;
import com.ksw.object.entity.jpa.File;
import com.ksw.object.entity.jpa.Note;
import com.ksw.object.entity.jpa.User;
import com.ksw.object.vo.combined.QuestionVO;
import com.ksw.object.vo.object.FileVO;
import com.ksw.object.vo.object.ReplyVO;
import com.ksw.service.forObject.object.CategoryService;
import com.ksw.service.forObject.object.FileService;
import com.ksw.service.forObject.object.NoteService;
import com.ksw.service.forObject.object.ReplyService;
import com.ksw.service.forObject.object.UserService;

@Service
public class QuestionService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoteCategoryMapper noteCategoryMapper;

	@Autowired
	private FileNoteMapper fileNoteMapper;

	@Autowired
	private NoteUserMapper noteUserMapper;
	
	@Autowired
	private QuestionMapper questionMapper;

	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private NoteService noteService;

	@Autowired
	private FileService fileService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	public QuestionVO convertTVO(QuestionDTO questionDTO) {
		QuestionVO.Builder builder = new QuestionVO.Builder();

		builder.noteVO(noteService.convertToVO(questionDTO.getNoteDTO()))
				.userVO(userService.convertToVO(questionDTO.getUserDTO()))
				.categoryVO(categoryService.convertToVO(questionDTO.getCategoryDTO()))
				.fileVO(fileService.convertToVO(questionDTO.getFileDTO()))
				.replies(replyService.convertToVOList(questionDTO.getReplies()))
				.viewCount(questionDTO.getViewCount());

		if (questionDTO.getFileDTO() != null) {
			builder.fileVO(fileService.convertToVO(questionDTO.getFileDTO()));
		}
		return builder.build();
	}

	@Transactional
	public QuestionVO Write(NoteDTO noteDTO, MultipartFile notefile, CategoryDTO categoryDTO, UserDTO userDTO) {

		QuestionVO questionVO = null;
		
		try {
			FileDTO fileDTO = fileService.uploadFile(notefile);
		
			Note note = noteService.convertToEntity(noteDTO);
			Category category = categoryService.convertToEntity(categoryDTO);

			noteRepository.save(note);
			categoryRepository.save(category);

			if (fileDTO != null) {
				File file = fileService.convertToEntity(fileDTO);
				fileRepository.save(file);
				//관계형 테이블 데이터 삽입
				fileNoteMapper.insert(file.getFileNo(), note.getNoteNo());
			}
			//관계형 테이블 데이터 삽입
			noteCategoryMapper.insert(note.getNoteNo(), category.getCategoryNo());
			noteUserMapper.insert(note.getNoteNo(), userDTO.getUserNo());

	        questionVO = new QuestionVO.Builder()
	                .noteVO(noteService.convertToVO(noteDTO))
	                .userVO(userService.convertToVO(userDTO))
	                .categoryVO(categoryService.convertToVO(categoryDTO))
	                .fileVO(fileDTO != null ? fileService.convertToVO(fileDTO) : null)
	                .build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return questionVO; 
	}
    @Transactional(readOnly = true)
    public QuestionVO Read(int noteNo) {
        UserDTO userDTO = questionMapper.getUserByNoteNo(noteNo);
        CategoryDTO categoryDTO = questionMapper.getCategoryByNoteNo(noteNo);
        NoteDTO noteDTO = questionMapper.getNoteByNoteNo(noteNo);
        FileDTO fileDTO = questionMapper.getFileByNoteNo(noteNo);
        List<ReplyDTO> replyList = questionMapper.getRepliesByNoteNo(noteNo);
        int viewCount = questionMapper.getViewCountByNoteNo(noteNo);
        int favoriteCount = questionMapper.getfavoriteCountByNoteNo(noteNo);

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setUserDTO(userDTO);
        questionDTO.setCategoryDTO(categoryDTO);
        questionDTO.setNoteDTO(noteDTO);
        questionDTO.setFileDTO(fileDTO);
        questionDTO.setReplies(replyList);
        questionDTO.setViewCount(viewCount);
        questionDTO.setFavoriteCount(favoriteCount);

        return this.convertTVO(questionDTO);
    }
}
