package com.ksw.service.function;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.ksw.dto.forObject.relation.NoteCategoryDTO;
import com.ksw.dto.forObject.relation.NoteUserDTO;
import com.ksw.dto.function.QuestionDTO;
import com.ksw.object.entity.Category;
import com.ksw.object.entity.File;
import com.ksw.object.entity.Note;
import com.ksw.object.entity.View;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.FileService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.entity.ViewService;
import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.service.forObject.relation.FileNoteService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.forObject.relation.NoteUserService;
import com.ksw.service.forObject.relation.NoteViewService;
import com.ksw.service.forObject.relation.ReplyUserService;
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
	private NoteService noteService;
	@Autowired
	private FileService fileService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AnswerHistoryService answerHistoryService;
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
	@Autowired
	private ClientInfoService clientInfoService;
	@Autowired
	private ViewService viewService;

	public QuestionVO convertToVO(QuestionDTO questionDTO) {
		QuestionVO.Builder builder = new QuestionVO.Builder();

		if (questionDTO == null || questionDTO.getNoteDTO().getNoteNo() == null) {
			return builder.build();
		}
		
		builder.noteVO(noteService.convertToVO(questionDTO.getNoteDTO()))
				.writerVO(userService.convertToVO(questionDTO.getWriterDTO()))
				.categoryVO(categoryService.convertToVO(questionDTO.getCategoryDTO()))
				.filelist(fileService.convertToVOList(questionDTO.getFileList()))
				.replies(questionDTO.getReplies())
				.todayNoteViewInCategory(questionDTO.getTodayNoteViewInCategory())
				.viewCount(questionDTO.getViewCount())
				.favoriteCount(questionDTO.getFavoriteCount())
				.answerType(questionDTO.getAnswerType())
				.isFavorite(questionDTO.getIsFavorite());
		
		return builder.build();
	}

	// CertifiedDetails -> 사용자 정보를 담고 있는 인증 객체. .getUserVO로 VO 얻을 수 있음.
	@Transactional
	public QuestionVO Write(NoteDTO noteDTO, List<MultipartFile> notefile, CategoryDTO categoryDTO,
			UserVO userVO) {
		// 반환할 QuestionVO 객체 준비
		QuestionVO questionVO = null;
		List<FileDTO> filelist = null;
		try {
			// MultipartFile file 객체 DTO로 변환
			filelist = fileService.uploadFile(notefile);

			// 사용자 정보 활용을 위해 DTO로 변환 (작성자)
			UserDTO userDTO = userService.convertVOToDTO(authService.getUserVO());

			Boolean relationIgnore = false;
			
			if(noteDTO.getNoteNo() != null) {
				relationIgnore = true;
			}
			// note 데이터 DTO로 변환
			Note note = noteService.convertToEntity(noteDTO);
			// category 데이터 DTO로 변환
			Category category = categoryService.convertToEntity(categoryDTO);

			// 데이터 DB에 저장
			note = noteRepository.save(note); // JPA 기본 문법으로 note 데이터 저장
			category = categoryService.saveCategory(category);

			// DTO로 바뀐 파일이 null이 아니면, 데이터를 저장
			if (filelist != null && !filelist.isEmpty()) {
				
				for (FileDTO fileDTO : filelist) {
					
					if(relationIgnore) {
						fileNoteMapper.delete(noteDTO.getNoteNo());
						fileNoteMapper.deleteFile(noteDTO.getNoteNo());
					}
					
					File file = fileService.convertToEntity(fileDTO); // DTO -> Entity 변환
					file = fileRepository.save(file); // JPA 기본 문법으로 file 데이터 저장
					// 관계형 테이블 데이터 삽입
					fileNoteMapper.insert(file.getFileNo(), note.getNoteNo()); // 엔티티로 변환 & 데이터 삽입
				}
			}
			
			if(!relationIgnore) {
				// 관계형 테이블 데이터 삽입 - note+category 관계테이블
				noteDTO = noteService.convertToDTO(note);
				categoryDTO = categoryService.convertToDTO(category);
				NoteCategoryDTO noteCategoryDTO = new NoteCategoryDTO(noteDTO, categoryDTO);
				noteCategoryMapper.insert(noteCategoryService.convertToEntity(noteCategoryDTO));
				
				// 관계형 테이블 데이터 삽입 - note+user 관계테이블 
				NoteUserDTO noteUserDTO = new NoteUserDTO(noteDTO, userDTO);
				noteUserMapper.insert(noteUserService.convertToEntity(noteUserDTO));
			}

			// QuestionVO 빌더 패턴으로 생성 후 반환 (원래는 DTO 세팅하고 VO로 변환)
			questionVO = new QuestionVO.Builder()
					.noteVO(noteService.convertToVO(noteService.convertToDTO(note)))
					.categoryVO(categoryService.convertToVO(categoryService.convertToDTO(category)))
					.filelist(fileService.convertToVOList(filelist)).build();
		} catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while writing question", e); // 예외를 다시 던져 트랜잭션 롤백을 유도
		}
		return questionVO;
	}

	@Transactional
	public QuestionVO Read(
		Integer noteNo, 
		UserVO userVO,
		HttpServletRequest request,
		HttpSession session) { 
		
		//조회자 정보 로딩
		UserDTO readerDTO = userService.convertVOToDTO(userVO);
		
		//글쓴이 정보 로딩 
		UserDTO writerDTO = noteUserService.getUserByNoteNo(noteNo); 

		//문제 카테고리 정보 로딩
		CategoryDTO categoryDTO = noteCategoryService.getCategorybyNoteNo(noteNo);
		
		//문제 내용 정보 로딩
		NoteDTO noteDTO = noteService.convertToDTO(noteRepository.findById(noteNo).orElse(null));
	 
		//문제 첨부파일 데이터 로딩
		List<FileDTO> fileDTO = fileService.convertToDTOList(fileNoteMapper.getFileByNoteNo(noteNo));
		
		// 해당 카테고리에서 사용자가 오늘 본 목록 가져오기
		Integer todayNoteViewInCategory = noteViewService.GetTodayHistoryCount(categoryDTO.getCategoryNo(), userVO.getUserNo())+1;
		
		// 조회 이력 확인 및 등록
		if(!clientInfoService.getSessionClientViewHistory(noteNo, session, request) || 
				(session.getAttribute("no_view_increase") == null || 
				!(Boolean) session.getAttribute("no_view_increase"))) {
			// 세션에 조회 이력 등
			System.out.println("session 체크 통과? "+clientInfoService.getSessionClientViewHistory(noteNo, session, request));
			clientInfoService.saveClientInfoInSession(noteNo, request, session);
			if (!noteViewService.checkRecentViewHistory(noteNo, readerDTO.getUserNo())) {
				System.out.println("최근 기록에도 없음");
				View view = new View(); // view Entity 객체 생성
				view = viewService.insert(view); // view DB에 기록
				HashMap<Integer, HashMap<String, String>> clientInfos = (HashMap<Integer, HashMap<String, String>>) session.getAttribute("clientInfos");
				HashMap<String,String> clientInfo = clientInfos.get(noteNo);
				Timestamp timestamp = Timestamp.valueOf(clientInfo.get("currentTime"));
				noteViewService.insertRelation(viewService.convertToDTO(view), noteDTO, readerDTO, timestamp);
				System.out.println("noteView insert 성공");
			}
		};
		
		session.removeAttribute("no_view_increase");
		
		//문제 댓글 목록 로딩 
		List<Map<String, Object>> replyList = replyUserService.getRepliesByNoteNo(noteNo, userVO.getUserNo());
		
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
		questionDTO.setFileList(fileDTO);
		questionDTO.setReplies(replyList); 
		questionDTO.setViewCount(viewCount);
		questionDTO.setFavoriteCount(favoriteCount);
		questionDTO.setAnswerType(answerType); 
		questionDTO.setIsFavorite(isFavorite);
		questionDTO.setTodayNoteViewInCategory(todayNoteViewInCategory);
		return this.convertToVO(questionDTO);
	}
}
