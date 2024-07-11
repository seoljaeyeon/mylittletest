package com.ksw.service.function;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ksw.dao.forObject.entity.CategoryRepository;
import com.ksw.dao.forObject.entity.FileRepository;
import com.ksw.dao.forObject.entity.NoteRepository;
import com.ksw.dao.forObject.entity.UserRepository;
import com.ksw.dao.forObject.relation.FileNoteMapper;
import com.ksw.dao.forObject.relation.NoteCategoryMapper;
import com.ksw.dao.forObject.relation.NoteUserMapper;
import com.ksw.dao.function.QuestionMapper;
import com.ksw.dto.forObject.entity.AnswerDTO;
import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.relation.AnswerHistoryDTO;
import com.ksw.dto.function.QuestionDTO;
import com.ksw.object.entity.Answer;
import com.ksw.object.entity.Category;
import com.ksw.object.entity.File;
import com.ksw.object.entity.Note;
import com.ksw.object.entity.User;
import com.ksw.service.forObject.entity.AnswerService;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.FileService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.vo.forObject.entity.AnswerVO;
import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.entity.ReplyVO;
import com.ksw.vo.function.QuestionVO;

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
	private AnswerHistoryService answerHistoryService;
	
	@Autowired
	private AnswerService answerService;

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
		File file = null;
		
		try {
			FileDTO fileDTO = fileService.uploadFile(notefile);
		
			Note note = noteService.convertToEntity(noteDTO);
			Category category = categoryService.convertToEntity(categoryDTO);

			System.out.println(note.getNoteAnswer());
			System.out.println(note.getNoteContent());
			
			noteRepository.save(note);
            noteRepository.flush();  // 엔티티 매니저를 플러시하여 데이터베이스와 즉시 동기화

			categoryRepository.save(category);
			categoryRepository.flush();

			if (fileDTO != null) {
				file = fileService.convertToEntity(fileDTO);
				fileRepository.save(file);
				fileRepository.flush();
				//관계형 테이블 데이터 삽입
				fileNoteMapper.insert(file.getFileNo(), note.getNoteNo());
			}
			//관계형 테이블 데이터 삽입
			noteCategoryMapper.insert(category.getCategoryNo(), note.getNoteNo());
			noteUserMapper.insert(note.getNoteNo(), userDTO.getUserNo());

	        questionVO = new QuestionVO.Builder()
	                .noteVO(noteService.convertToVO(noteService.convertToDTO(note)))
	                .userVO(userService.convertToVO(userDTO))
	                .categoryVO(categoryService.convertToVO(categoryService.convertToDTO(category)))
	                .fileVO(fileDTO != null ? fileService.convertToVO(fileService.convertToDTO(file)) : null)
	                .build();
	        
	        System.out.println("questionVO created: " + questionVO);
	        System.out.println("NoteVO: " + questionVO.getNoteVO());
	        System.out.println("NoteNo: " + questionVO.getNoteVO().getNoteNo());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionVO; 
	}
	
	/*
	 * @Transactional(readOnly = true) public QuestionVO Read(Integer noteNo,
	 * Integer userNo) { UserDTO userDTO = questionMapper.getUserByNoteNo(noteNo);
	 * // 읽는 사람 정보 UserDTO writerDTO = questionMapper.getWriterByNoteNo(noteNo); //
	 * 글쓴이 정보 CategoryDTO categoryDTO = questionMapper.getCategoryByNoteNo(noteNo);
	 * NoteDTO noteDTO = questionMapper.getNoteByNoteNo(noteNo);
	 * 
	 * FileDTO fileDTO = questionMapper.getFileByNoteNo(noteNo); List<ReplyDTO>
	 * replyList = questionMapper.getRepliesByNoteNo(noteNo); int viewCount =
	 * questionMapper.getViewCountByNoteNo(noteNo); int favoriteCount =
	 * questionMapper.getfavoriteCountByNoteNo(noteNo); Boolean isFavorite =
	 * questionMapper.getIsFavoriteByNoteNoAndUserNo(noteNo, userNo);
	 * 
	 * AnswerHistoryDTO latestAnswer =
	 * answerHistoryService.getAnswerHistoryByNoteNoAndUserNo(noteNo, userNo);
	 * AnswerDTO answerDTO =
	 * answerService.getAnswerByNo(latestAnswer.getAnswerNo()); int answerType =
	 * answerDTO.getAnswerType();
	 * 
	 * QuestionDTO questionDTO = new QuestionDTO(); questionDTO.setUserDTO(userDTO);
	 * questionDTO.setWriterDTO(writerDTO); questionDTO.setCategoryDTO(categoryDTO);
	 * questionDTO.setNoteDTO(noteDTO); questionDTO.setFileDTO(fileDTO);
	 * questionDTO.setReplies(replyList); questionDTO.setViewCount(viewCount);
	 * questionDTO.setFavoriteCount(favoriteCount);
	 * questionDTO.setAnswerType(answerType); questionDTO.setIsFavorite(isFavorite);
	 * 
	 * return this.convertTVO(questionDTO); }
	 */
}
