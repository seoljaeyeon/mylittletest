package com.ksw.mylittletest;

import java.io.IOException;

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
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("userNo") Integer userNo) {
        try {
            FileDTO fileDTO = fileService.uploadFile(file);
            File fileInfos = fileService.save(fileDTO);
            fileUserService.insert(fileInfos.getFileNo(), userNo);
            System.out.println(fileDTO.getSavedName());
            return ResponseEntity.ok(fileDTO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
}