package com.example.ex.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public final String FILE_UPLOAD_PATH = "C:\\성훈\\spring\\springProject\\upload\\image/";
	
	//파일 저장
	public String saveFile(int userId,MultipartFile file) {
		
		// 파일 경로
		// 사용자 별로 구분할 수 있도록
		// 사용자가 파일을 올린 시간으로 구분
		//  /12_3339093090/test.png
		// 1970년 1월 1일 기준으로 현지 밀리 세컨이 경과 되었는지 나타내는 수
		
		
		String directoryName = userId + "_" +System.currentTimeMillis() + "/";
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		//디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 디렉토리 생성 에러
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			// 파일 저장 경로 + 파일 이름 객체
			Path path = Paths.get(filePath + file.getOriginalFilename());
			// 파일 저장
			Files.write(path, bytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			// 파일 저장 실패
			e.printStackTrace();
			return null;
		}
		
		// 파일 접근 가능한 주소 리턴
		// <img src="/images/12_123232422/test.png">
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
		
		
	}

}
