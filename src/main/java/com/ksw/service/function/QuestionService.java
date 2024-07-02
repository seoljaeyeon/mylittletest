package com.ksw.service.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ksw.dao.CategoryRepository;
import com.ksw.dao.FileNoteMapper;
import com.ksw.dao.FileRepository;
import com.ksw.dao.NoteCategoryMapper;
import com.ksw.dao.NoteRepository;
import com.ksw.dao.NoteUserMapper;
import com.ksw.dao.UserRepository;
import com.ksw.dto.forObject.CategoryDTO;
import com.ksw.dto.forObject.FileDTO;
import com.ksw.dto.forObject.NoteDTO;
import com.ksw.dto.forObject.UserDTO;
import com.ksw.dto.forUtil.QuestionDTO;
import com.ksw.object.entity.jpa.Category;
import com.ksw.object.entity.jpa.File;
import com.ksw.object.entity.jpa.Note;
import com.ksw.object.entity.jpa.User;
import com.ksw.object.vo.FileVO;
import com.ksw.object.vo.QuestionVO;
import com.ksw.service.object.NoteService;
import com.ksw.service.object.UserService;
import com.ksw.service.object.CategoryService;
import com.ksw.service.object.FileService;

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
	private NoteService noteService;

	@Autowired
	private FileService fileService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	public QuestionVO convertToWriteVO(QuestionDTO questionDTO) {
		QuestionVO.Builder builder = new QuestionVO.Builder();

		builder.noteVO(noteService.convertToVO(questionDTO.getNoteDTO()))
				.userVO(userService.convertToVO(questionDTO.getUserDTO()))
				.categoryVO(categoryService.convertToVO(questionDTO.getCategoryDTO()));

		if (questionDTO.getFileDTO() != null) {
			builder.fileVO(fileService.convertToVO(questionDTO.getFileDTO()));
		}
		return builder.build();
	}

	@Transactional
	public QuestionVO noteWrite(NoteDTO noteDTO, MultipartFile notefile, CategoryDTO categoryDTO, UserDTO userDTO) {

		QuestionVO questionVO = null;
		
		try {
			FileDTO fileDTO = fileService.uploadFile(notefile);
		
			Note note = noteService.convertToEntity(noteDTO);
			Category category = categoryService.convertToEntity(categoryDTO);
			User user = userService.convertToEntity(userDTO);

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
}
