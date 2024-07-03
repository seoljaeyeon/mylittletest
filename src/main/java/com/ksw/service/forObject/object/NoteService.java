package com.ksw.service.forObject.object;

import com.ksw.dao.object.NoteRepository;
import com.ksw.dao.relation.NoteCategoryMapper;
import com.ksw.dao.relation.ViewUserNoteMapper;
import com.ksw.dto.forObject.object.NoteDTO;
import com.ksw.object.entity.jpa.Note;
import com.ksw.object.vo.combined.ViewHistoryVO;
import com.ksw.object.vo.object.NoteVO;
import com.ksw.service.function.ViewHistoryService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService {

    @Autowired
    private NoteCategoryMapper noteCategoryMapper;

    @Autowired
    private ViewUserNoteMapper viewUserNoteMapper;

    @Autowired
    private NoteRepository noteRepository;
    
    @Autowired
    ViewHistoryService viewHistoryService;
    
    @Transactional(readOnly = true)
    public Note getRandomUnviewedNoteByCategory(int categoryNo, int userNo) {
        // 사용자가 특정 카테고리에서 본 노트 목록을 가져옵니다.
        List<ViewHistoryVO> viewHistoriesByCategory = viewHistoryService.getHistoryByCategory(userNo, categoryNo);
        List<Integer> viewedNoteNosByCategory = viewHistoriesByCategory.stream()
                .map(ViewHistoryVO::getNoteNo)
                .collect(Collectors.toList());

        // 특정 카테고리에 속하는 모든 노트 ID를 가져옵니다.
        List<Integer> noteNosInCategory = viewHistoriesByCategory.stream()
                .map(ViewHistoryVO::getNoteNo)
                .collect(Collectors.toList());

        // 사용자가 보지 않은 노트들만 필터링합니다.
        List<Integer> unviewedNoteNos = noteNosInCategory.stream()
                .filter(noteNo -> !viewedNoteNosByCategory.contains(noteNo))
                .collect(Collectors.toList());

        List<Note> notesToChooseFrom;

        if (unviewedNoteNos.isEmpty()) {
            // 사용자가 보지 않은 노트가 없으면, 이미 본 노트들 중에서 선택합니다.
            notesToChooseFrom = noteRepository.findNotesByNoteNos(viewedNoteNosByCategory);
        } else {
            // 사용자가 보지 않은 노트가 있으면, 그 노트들 중에서 선택합니다.
            notesToChooseFrom = noteRepository.findNotesByNoteNos(unviewedNoteNos);
        }

        if (notesToChooseFrom.isEmpty()) {
            throw new IllegalStateException("No notes available in this category for the user");
        }

        // 랜덤으로 노트를 선택합니다.
        Random random = new Random();
        int randomIndex = random.nextInt(notesToChooseFrom.size());
        return notesToChooseFrom.get(randomIndex);
    }
	

    // Entity -> DTO 변환 메소드
    public NoteDTO convertToDTO(Note note) {
        return new NoteDTO.Builder()
                .noteNo(note.getNoteNo())
                .noteTitle(note.getNoteTitle())
                .noteContent(note.getNoteContent())
                .noteHint(note.getNoteHint())
                .noteAnswer(note.getNoteAnswer())
                .isActive(note.getIsActive())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

    // DTO -> Entity로 변환
    public Note convertToEntity(NoteDTO noteDTO) {
        Note note = new Note();
        note.setNoteNo(noteDTO.getNoteNo());
        note.setNoteTitle(noteDTO.getNoteTitle());
        note.setNoteContent(noteDTO.getNoteContent());
        note.setNoteCommentary(noteDTO.getNoteCommentary());
        note.setNoteHint(noteDTO.getNoteHint());
        note.setNoteAnswer(noteDTO.getNoteAnswer());
        note.setIsActive(noteDTO.getIsActive());
        note.setCreatedAt(noteDTO.getCreatedAt());
        note.setUpdatedAt(noteDTO.getUpdatedAt());
        return note;
    }
    
    // DTO -> VO 변환 메소드
    public NoteVO convertToVO(NoteDTO noteDTO) {
        return new NoteVO.Builder()
                .noteNo(noteDTO.getNoteNo())
                .noteTitle(noteDTO.getNoteTitle())
                .noteContent(noteDTO.getNoteContent())
                .noteHint(noteDTO.getNoteHint())
                .noteAnswer(noteDTO.getNoteAnswer())
                .isActive(noteDTO.getIsActive())
                .createdAt(noteDTO.getCreatedAt())
                .updatedAt(noteDTO.getUpdatedAt())
                .build();
    }
}
