package com.ksw.mylittletest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.object.entity.File;
import com.ksw.service.forObject.entity.FileService;
import com.ksw.service.forObject.relation.FileUserService;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileUserService fileUserService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
    		@RequestParam("file") List<MultipartFile> files, 
    		@RequestParam("userNo") Integer userNo) {
        try {
        	List<FileDTO> filelist = fileService.uploadFile(files);
        	
			// DTO로 바뀐 파일이 null이 아니면, 데이터를 저장
			if (filelist != null && !filelist.isEmpty()) {
				
				for (FileDTO fileDTO : filelist) {
					File file = fileService.convertToEntity(fileDTO); // DTO -> Entity 변환
					file = fileService.save(file); // JPA 기본 문법으로 file 데이터 저장
					// 관계형 테이블 데이터 삽입
					fileUserService.insert(file.getFileNo(), userNo);
				}
			}
            return ResponseEntity.ok(filelist);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
}