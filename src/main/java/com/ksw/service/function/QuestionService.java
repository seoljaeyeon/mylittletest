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
import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.relation.FileNoteDTO;
import com.ksw.dto.forObject.relation.NoteCategoryDTO;
import com.ksw.dto.forObject.relation.NoteUserDTO;
import com.ksw.dto.forObject.relation.NoteViewDTO;
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
import com.ksw.service.forObject.relation.NoteViewService;
import com.ksw.service.forObject.relation.ReplyUserService;
import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.entity.UserVO;
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
	@Autowired
	private ReplyUserService replyUserService;
	@Autowired
	private AuthService authService;
	@Autowired
	private NoteViewService noteViewService;

	public QuestionVO convertToVO(QuestionDTO questionDTO) {
		QuestionVO.Builder builder = new QuestionVO.Builder();

		if (questionDTO == null) {
			return builder.build();
		}
		
		builder.noteVO(noteService.convertToVO(questionDTO.getNoteDTO()))
				.writerVO(userService.convertToVO(questionDTO.getWriterDTO()))
				.categoryVO(categoryService.convertToVO(questionDTO.getCategoryDTO()))
				.fileVO(fileService.convertToVO(questionDTO.getFileDTO()))
				.replies(replyUserService.convertToVOList(questionDTO.getReplies()));
//				.viewCount(questionDTO.getViewCount())
//				.favoriteCount(questionDTO.getFavoriteCount())
//				.answerType(questionDTO.getAnswerType())
//				.isFavorite(questionDTO.getIsFavorite());
		return builder.build();
	}

	// CertifiedDetails -> 사용자 정보를 담고 있는 인증 객체. .getUserVO로 VO 얻을 수 있음.
	@Transactional
	public QuestionVO Write(NoteDTO noteDTO, MultipartFile notefile, CategoryDTO categoryDTO,
			UserVO userVO) {
		// 반환할 QuestionVO 객체 준비
		QuestionVO questionVO = null;

		// QuestionVO에 포함시킬 FileVO를 위한 file entity 준비 (null 대비)
		File file = null;

		try {
			// MultipartFile file 객체 DTO로 변환
			FileDTO fileDTO = fileService.uploadFile(notefile);

			// 사용자 정보 활용을 위해 DTO로 변환 (작성자)
			UserDTO userDTO = userService.convertVOToDTO(authService.getUserVO());

			// note 데이터 DTO로 변환
			Note note = noteService.convertToEntity(noteDTO);

			// category 데이터 DTO로 변환
			Category category = categoryService.convertToEntity(categoryDTO);

			// 데이터 DB에 저장
			note = noteRepository.save(note); // JPA 기본 문법으로 note 데이터 저장
			category = categoryRepository.save(category); // JPA 기본 문법으로 category 데이터 저장

			// DTO로 바뀐 파일이 null이 아니면, 데이터를 저장
			if (fileDTO.getUploadName() != null && !fileDTO.getUploadName().isEmpty()) {
				file = fileService.convertToEntity(fileDTO); // DTO -> Entity 변환
				file = fileRepository.save(file); // JPA 기본 문법으로 file 데이터 저장
				// 관계형 테이블 데이터 삽입
				FileNoteDTO fileNoteDTO = new FileNoteDTO(noteDTO, fileDTO); // 관계 테이블 DTO 생성
				fileNoteMapper.insert(fileNoteService.convertToEntity(fileNoteDTO)); // 엔티티로 변환 & 데이터 삽입
			}
			// 관계형 테이블 데이터 삽입 - note+category 관계테이블
			noteDTO = noteService.convertToDTO(note);
			categoryDTO = categoryService.convertToDTO(category);
			NoteCategoryDTO noteCategoryDTO = new NoteCategoryDTO(noteDTO, categoryDTO);
			noteCategoryMapper.insert(noteCategoryService.convertToEntity(noteCategoryDTO));

			// 관계형 테이블 데이터 삽입 - note+user 관계테이블 
			NoteUserDTO noteUserDTO = new NoteUserDTO(noteDTO, userDTO);
			noteUserMapper.insert(noteUserService.convertToEntity(noteUserDTO));

			// QuestionVO 빌더 패턴으로 생성 후 반환 (원래는 DTO 세팅하고 VO로 변환)
			questionVO = new QuestionVO.Builder()
					.noteVO(noteService.convertToVO(noteService.convertToDTO(note)))
					.categoryVO(categoryService.convertToVO(categoryService.convertToDTO(category)))
					.fileVO((fileDTO.getUploadName() != null && !fileDTO.getUploadName().isEmpty()) ? fileService.convertToVO(fileService.convertToDTO(file)) : new FileVO.Builder().build()).build();
		} catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while writing question", e); // 예외를 다시 던져 트랜잭션 롤백을 유도
		}
		return questionVO;
	}

	@Transactional
	public QuestionVO Read(
		Integer noteNo, 
		UserVO userVO) { 
		
		//조회자 정보 로딩
		UserDTO readerDTO = userService.convertVOToDTO(userVO);
		
		//글쓴이 정보 로딩 
		UserDTO writerDTO = userService.convertToDTO(noteUserMapper.getUserByNoteNo(noteNo)); 

		//문제 카테고리 정보 로딩
		CategoryDTO categoryDTO = categoryService.convertToDTO(noteCategoryMapper.getCategorybyNoteNo(noteNo));
		
		//문제 내용 정보 로딩
		NoteDTO noteDTO = noteService.convertToDTO(noteRepository.findById(noteNo).orElse(null));
	 
		//문제 첨부파일 데이터 로딩
		FileDTO fileDTO = fileService.convertToDTO(fileNoteMapper.getFileByNoteNo(noteNo));
		
		// 해당 카테고리에서 사용자가 오늘 본 목록 가져오기
		Integer todayNoteViewInCategory = noteViewService.GetTodayHistoryCount(categoryDTO.getCategoryNo(), userVO.getUserNo());
		
		// 조회 이력 확인 및 등록
		
		//
		
		//문제 댓글 목록 로딩 
		List<ReplyUserDTO> replyList =	questionMapper.getRepliesByNoteNo(noteNo);
		
		//해당 게시글 조회 수 로딩 
		int viewCount = questionMapper.getViewCountByNoteNo(noteNo);
		
		//해당 게시글 댓글 수 로딩
		int favoriteCount = questionMapper.getfavoriteCountByNoteNo(noteNo);
		
		//조회자가 해당 글 좋아요 했는 지 로딩
		Boolean isFavorite = questionMapper.getIsFavoriteByNoteNoAndUserNo(noteNo, readerDTO.getUserNo());
		
		//조회자가 해당 글을 정답으로 했는 지, 오답으로 했는 지 로딩
		Integer answerType = answerHistoryService.getAnswerHistoryByNoteNoAndUserNo(noteNo, readerDTO.getUserNo());
		
		//QuestionDTO에 모든 정보 담고 VO 반환
		QuestionDTO questionDTO = new QuestionDTO(); 
		questionDTO.setWriterDTO(writerDTO); 
		questionDTO.setCategoryDTO(categoryDTO);
		questionDTO.setNoteDTO(noteDTO); 
		questionDTO.setFileDTO(fileDTO);
		questionDTO.setReplies(replyList); 
		questionDTO.setViewCount(viewCount);
		questionDTO.setFavoriteCount(favoriteCount);
		questionDTO.setAnswerType(answerType); 
		questionDTO.setIsFavorite(isFavorite);
		questionDTO.setTodayNoteViewInCategory(todayNoteViewInCategory);
		return this.convertToVO(questionDTO);
	}
}
