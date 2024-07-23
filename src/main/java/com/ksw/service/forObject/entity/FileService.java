package com.ksw.service.forObject.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ksw.dao.forObject.entity.FileRepository;
import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.object.entity.File;
import com.ksw.vo.forObject.entity.FileVO;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;
	
    private Path fileStorageLocation;

    @Value("${file.upload-dir.linux:/var/www/uploads}")
    private String linuxUploadDir;

    @Value("${file.upload-dir.windows:C:/var/www/uploads}")
    private String windowsUploadDir;

    @PostConstruct
    public void init() {
    	// 현재 운영체제 이름 가져
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // win일 경우, 파일 저장할 저장소 경로를 windowsUploadDir로
            this.fileStorageLocation = Paths.get(windowsUploadDir).toAbsolutePath().normalize();
        } else {
            // linux일 경우, 파일 저장할 저장소 경로를 linuxUploadDir로
            this.fileStorageLocation = Paths.get(linuxUploadDir).toAbsolutePath().normalize();
        }
        try {
        	// 경로에 해당하는 폴더가 없으면 폴더를 생성할 
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
        	// 권한이 없거나, 생성하지 못하는 경우에러를 반환할 것
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    public File save(FileDTO fileDTO) {
    	return fileRepository.save(this.convertToEntity(fileDTO));
    }
    
	public List<FileDTO> uploadFile(List<MultipartFile> file) throws IOException {

		List<FileDTO> filelist = new ArrayList<>();
		
		// file이 null일 경우, 빈 fileDTO 반환
		if (file == null || file.isEmpty()) {
			return filelist;
		}
		
		for (MultipartFile fileitem : file ) {
			// DTO에 저장될 필드명 미리 정의
			String originalFileName = "";
			String extension = "";
			String savedFileName = "";
			
			// 반환할 DTO 미리 정의 (null 처리 위해서)
			FileDTO fileDTO = new FileDTO();
			
			// file 업로드 당시 이름 가져오기
			originalFileName = fileitem.getOriginalFilename();
			
			// file 확장자 가져오기
		    extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		    
		    // UUID 통해서 랜덤한 이름 생성 (중복으로 저장됐을 때 피하기 위해서)
		    savedFileName = UUID.randomUUID().toString() + extension;
		    
		    // 파일이 저장될 경로 정의
	        Path filePath = fileStorageLocation.resolve(savedFileName);
	        
	        // 파일 저장
	        Files.copy(fileitem.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // DTO 필드 채우기 
		    fileDTO.setSavedName(savedFileName);
		    fileDTO.setUploadName(originalFileName);
		    fileDTO.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

			filelist.add(fileDTO);
		}
	    return filelist;
	}
	
    // Entity -> DTO 변환 메소드
    public FileDTO convertToDTO(File file) {
    	FileDTO dto = new FileDTO();
    	if (file == null) {
    		System.out.println("File to FileDTO failed. Empty FileDTO created. File is null");
    		return dto;
    	}
    	dto.setFileNo(file.getFileNo());
    	dto.setSavedName(file.getSavedName());
    	dto.setUploadName(file.getUploadName());
    	dto.setCreatedAt(file.getCreatedAt());
        return dto;
    }
    
    // DTO -> Entity 변환 메소드
    public File convertToEntity(FileDTO fileDTO) {
    	File file = new File();
    	if(fileDTO == null) {
    		System.out.println("FileDTO to File failed. Empty File created. FileDTO is null");
    		return file;
    	}
        file.setFileNo(fileDTO.getFileNo());
        file.setSavedName(fileDTO.getSavedName());
        file.setUploadName(fileDTO.getUploadName());
        file.setCreatedAt(fileDTO.getCreatedAt());
        return file;
    }


    // DTO -> VO 변환 메소드
    public FileVO convertToVO(FileDTO fileDTO) {
        if (fileDTO == null) {
    		System.out.println("FileDTO to FileVO failed. Empty FileVO created. FileDTO is null");
            return new FileVO.Builder().build();
        }
        return new FileVO.Builder()
                .fileNo(fileDTO.getFileNo())
                .savedName(fileDTO.getSavedName())
                .uploadName(fileDTO.getUploadName())
                .createdAt(fileDTO.getCreatedAt())
                .build();
    }
    
    public List<FileDTO> convertToDTOList(List<File> filelist) {
    	if(filelist == null || filelist.isEmpty()) {
    		System.out.println("List<File> filelist is null");
    		return new ArrayList<FileDTO>();
    	}
    	return filelist.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    public List<FileVO> convertToVOList(List<FileDTO> filelist) {
    	if (filelist == null || filelist.isEmpty()) {
    		System.out.println("FileDTO to fileVO failed. Empty FileList created.");
    		return new ArrayList<FileVO>();
    	}
    	return filelist.stream().map(this::convertToVO).collect(Collectors.toList());
    }
}
