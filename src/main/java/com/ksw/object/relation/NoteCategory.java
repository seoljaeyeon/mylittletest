package com.ksw.object.relation;

import com.ksw.object.entity.Category;
import com.ksw.object.entity.Note;

public class NoteCategory {

    private Category category;
    private Note note;

    // 기본 생성자
    public NoteCategory() {}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

}
