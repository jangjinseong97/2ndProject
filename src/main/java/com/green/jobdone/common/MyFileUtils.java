package com.green.jobdone.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component //빈등록
public class MyFileUtils {
    private final String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    /*
        @Value("${file.directory}")은
        yaml 파일에 있는 file.directory 속성에 저장된 값을 생성자 호출할 때 값을 넣어준다.
         */
    public MyFileUtils(@Value("${file.directory}") String uploadPath) {
        log.info("MyFileUtils - 생성자: {}", uploadPath);
        this.uploadPath = uploadPath;
    }

    // path = "ddd/aaa"
    // D:/2024-02/download/greengram_ver1/
    // feed/2
    // D:/2024-02/download/greengram_ver1/feed/2
    // D:/2024-02/download/greengram_ver1/ddd/aaa
    //디렉토리 생성
    public void makeFolders(String path) {
        File file = new File(uploadPath, path);
        // static 아님  >>  객체화하고 주소값.(file.)으로 호출했기 때문에
        // 리턴타입은 boolean  >>  if()안에서 호출했기 때문에
        // 파라미터는 없음   >>  호출 때 인자를 보내지 않았기 때문에
        // 메소드명은  >>  exists였다.
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    //파일명에서 확장자 추출
    public String getExt(String fileName) {
        int lastIdx = fileName.lastIndexOf(".");
        return fileName.substring(lastIdx);
    }

    //랜덤파일명 생성
    public String makeRandomFileName() {
        return UUID.randomUUID().toString();
    }

    //랜덤파일명 + 확장자 생성하여 리턴
    public String makeRandomFileName(String originalFileName) {
        return makeRandomFileName() + getExt(originalFileName);
    }

    public String makeRandomFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        return makeRandomFileName(originalFileName);
    }

    //파일을 원하는 경로에 저장
    public void transferTo(MultipartFile mf, String path) throws IOException {
        File file = new File(uploadPath, path);
        mf.transferTo(file);
    }

    //폴더 삭제, e.g. "user/1"
    public void deleteFolder(String path, boolean deleteRootFolder) {
        File folder = new File(uploadPath,path); // 절대경오 박아야 폴더 없어져요 여러분
        if(folder.exists() && folder.isDirectory()) { //폴더가 존재하면서 디렉토리인가?
            File[] includedFiles = folder.listFiles();

            for(File f : includedFiles) {
                if(f.isDirectory()) {
                    deleteFolder(f.getAbsolutePath(), true);
                } else {
                    f.delete();
                }
            }

            if(deleteRootFolder) {
                folder.delete();
            }
        }

    }

    public boolean deleteFile(String path) {   //지정한 경로 폴더 안의 파일만 삭제하는 메소드 추가 24.01.22
        File dir = new File(uploadPath, path);
        if (dir.exists() && dir.isDirectory()) {
            File[] includedFiles = dir.listFiles();
            if (includedFiles != null) {
                for (File file : includedFiles) {
                    if (file.isFile() && !file.delete()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

}
