package com.ksw.service.function;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.relation.AnswerHistoryDTO;
import com.ksw.dto.forObject.relation.FileNoteDTO;
import com.ksw.dto.forObject.relation.NoteCategoryDTO;
import com.ksw.dto.forObject.relation.NoteUserDTO;
import com.ksw.dto.forObject.relation.ReplyUserDTO;
import com.ksw.dto.function.QuestionDTO;
import com.ksw.object.entity.Category;
import com.ksw.object.entity.File;
import com.ksw.object.entity.Note;
import com.ksw.service.forObject.entity.AnswerService;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.FileService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.service.forObject.relation.FileNoteService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.forObject.relation.NoteUserService;
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
	@Autowired
	private FileNoteService fileNoteService;
	@Autowired 
	private NoteCategoryService noteCategoryService;
	@Autowired
	private NoteUserService noteUserService;

	public QuestionVO convertToVO(QuestionDTO questionDTO) {
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

	// CertifiedDetails -> 사용자 정보를 담고 있는 인증 객체. .getUserVO로 VO 얻을 수 있음.
	@Transactional
	public QuestionVO Write(
			NoteDTO noteDTO, 
			MultipartFile notefile, 
			CategoryDTO categoryDTO, 
			@AuthenticationPrincipal CertifiedUserDetails userinfo) {

		// 반환할 QuestionVO 객체 준비
		QuestionVO questionVO = null;
		
		// QuestionVO에 포함시킬 FileVO를 위한 file entity 준비 (null 대비)
		File file = null;
		
		try {
			// MultipartFile file 객체 DTO로 변환
			FileDTO fileDTO = fileService.uploadFile(notefile);

			UserDTO userDTO = userService.convertVOToDTO(userinfo.getUserVO());
			
			// note 데이터 DTO로 변환
			Note note = noteService.convertToEntity(noteDTO);
			
			// category 데이터 DTO로 변환
			Category category = categoryService.convertToEntity(categoryDTO);

			// 데이터 DB에 저장
			noteRepository.save(note); // JPA 기본 문법으로 note 데이터 저장
			noteRepository.flush(); // note 데이터의 noteNo 가져옴
			categoryRepository.save(category); // JPA 기본 문법으로 category 데이터 저장
			categoryRepository.flush(); // category 데이터의 categoryNo 가져옴

			// DTO로 바뀐 파일이 null이 아니면, 데이터를 저장
			if (!fileDTO.getUploadName().equals("")) {
				file = fileService.convertToEntity(fileDTO); // DTO -> Entity 변환
				fileRepository.save(file); // JPA 기본 문법으로 file 데이터 저장
				fileRepository.flush(); // file 데이터의 fileNo 가져옴
				//관계형 테이블 데이터 삽입
				FileNoteDTO fileNoteDTO = new FileNoteDTO(noteDTO, fileDTO); // 관계 테이블 DTO 생성
				fileNoteMapper.insert(fileNoteService.convertToEntity(fileNoteDTO)); // 엔티티로 변환 & 데이터 삽입
			}
			//관계형 테이블 데이터 삽입 - note+category 관계테이블
			NoteCategoryDTO noteCategoryDTO = new NoteCategoryDTO(noteDTO, categoryDTO);
			noteCategoryMapper.insert(noteCategoryService.convertToEntity(noteCategoryDTO));
			
			//관계형 테이블 데이터 삽입 - note+user 관계테이블 
			NoteUserDTO noteUserDTO = new NoteUserDTO(noteDTO, userDTO);
			noteUserMapper.insert(noteUserService.convertToEntity(noteUserDTO));

	        questionVO = new QuestionVO.Builder()
	                .noteVO(noteService.convertToVO(noteService.convertToDTO(note)))
	                .userVO(userService.convertToVO(userDTO))
	                .categoryVO(categoryService.convertToVO(categoryService.convertToDTO(category)))
	                .fileVO(fileDTO != null ? fileService.convertToVO(fileService.convertToDTO(file)) : null)
	                .build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionVO; 
	}
	
	@Transactional(readOnly = true) 
	public QuestionVO Read(
		Integer noteNo, @AuthenticationPrincipal CertifiedUserDetails userinfo) { 
		
		//조회자 정보 로딩
		UserDTO readerDTO = userService.convertVOToDTO(userinfo.getUserVO());
		
		//글쓴이 정보 로딩 
		UserDTO writerDTO = questionMapper.getWriterByNoteNo(noteNo);

		//문제 카테고리 정보 로딩
		CategoryDTO categoryDTO = questionMapper.getCategoryByNoteNo(noteNo);
		
		//문제 내용 정보 로딩
		NoteDTO noteDTO = questionMapper.getNoteByNoteNo(noteNo);
	 
		//문제 첨부파일 데이터 로딩
		FileDTO fileDTO = questionMapper.getFileByNoteNo(noteNo);
		
		/*
		 * //문제 댓글 목록 로딩 List<ReplyUserDTO> replyList =
		 * questionMapper.getRepliesByNoteNo(noteNo); int viewCount =
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
		 */
		// return this.convertTVO(questionDTO);
		return null;
	}
}
