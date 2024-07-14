package com.ksw.dto.function;

import java.io.Serializable;
import java.util.List;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.relation.NoteViewDTO;
import com.ksw.dto.forObject.relation.ReplyUserDTO;

public class QuestionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private UserDTO writerDTO;
	private NoteDTO noteDTO;
	private CategoryDTO categoryDTO;
	private FileDTO fileDTO;
	private List<ReplyUserDTO> replies;
	private Integer viewCount;
	private Integer favoriteCount;
	private Integer answerType;
	private Boolean isFavorite;
	private Integer todayNoteViewInCategory;
	
	public Integer getTodayNoteViewInCategory() {
		return todayNoteViewInCategory;
	}
	public void setTodayNoteViewInCategory(Integer todayNoteViewInCategory) {
		this.todayNoteViewInCategory = todayNoteViewInCategory;
	}
	public List<ReplyUserDTO> getReplies() {
		return replies;
	}
	public UserDTO getWriterDTO() {
		return writerDTO;
	}
	public void setWriterDTO(UserDTO writerDTO) {
		this.writerDTO = writerDTO;
	}
	public Boolean getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	public void setReplies(List<ReplyUserDTO> replies) {
		this.replies = replies;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public int getAnswerType() {
		return answerType;
	}
	public void setAnswerType(Integer answerType) {
		this.answerType = answerType;
	}
	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public NoteDTO getNoteDTO() {
		return noteDTO;
	}
	public void setNoteDTO(NoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	public FileDTO getFileDTO() {
		return fileDTO;
	}
	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}
}
