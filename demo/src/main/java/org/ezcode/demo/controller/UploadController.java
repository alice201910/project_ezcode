package org.ezcode.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.ezcode.demo.domain.AttachDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

/**
 * UploadController
 */
@Controller
@Slf4j
public class UploadController {

    @PostMapping(value="/uploadFile",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<AttachDTO>> uploadFile(MultipartFile[] uploadFile, Model model){
        log.info("upload ajax");
        List<AttachDTO> list = new ArrayList<>();
        
        String uploadFolder = "C:\\upload";

        String uploadFolderPath = getFolder(); 
        log.info("uploadFolderPath : "+uploadFolderPath);
        //폴더 만듦
        File uploadPath = new File(uploadFolder, getFolder());
        log.info("upload Path : "+ uploadPath);

        if(uploadPath.exists() == false){
            uploadPath.mkdirs();
        }
        // end of make folder 
        
        for(MultipartFile multipartFile : uploadFile){
                log.info("----------------------------------");
                log.info("Upload File Name : " + multipartFile.getOriginalFilename());
                log.info("upload File Size : " + multipartFile.getSize());
                log.info("----------------------------------");

            AttachDTO attachDTO = new AttachDTO();

            String uploadFileName = multipartFile.getOriginalFilename();

            //IE ver
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
            
            attachDTO.setFname(uploadFileName); 

            // uuid+파일명
            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString() + "_" + uploadFileName;
            log.info("uploadFileName : "+uploadFileName);
            log.info("------------------★----------------");
            
            
            //upload밑에 날짜 폴더 생김
            try {
                File saveFile = new File(uploadPath, uploadFileName); 
                log.info("----------------------------------");
                log.info("saveFile : "+saveFile); 
                multipartFile.transferTo(saveFile); 
                
                attachDTO.setUuid(uuid.toString());
                attachDTO.setUploadpath(uploadFolderPath);

                log.info("-----------------------------------");
                //img check
                if(checkImageType(saveFile)){ 
                   
                    attachDTO.setFiletype(true); 
                    File files = new File(uploadPath,"s_" + uploadFileName);
                    log.info("-----------------------------------");
                    log.info("files : "+files);
                    log.info("-----------------------------------");
                    FileOutputStream thumbnail = new FileOutputStream(files); 
                    log.info("thumbnail : "+thumbnail);
                    log.info("-----------------------------------");
                    
                     Thumbnailator.createThumbnail(new FileInputStream(saveFile), thumbnail, 100, 100);
                    thumbnail.close(); //FOS 닫음
                }
                //add list
                list.add(attachDTO);
            } catch (Exception e) {
                e.printStackTrace();                
            } 

        }//end for
        
        list.forEach(vo->{
            log.info("list : "+vo);
        });

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 날짜
    private String getFolder(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);

        log.info("날짜 : "+str);

        return str.replace("-", File.separator);


    }

    private boolean checkImageType(File file){

        try {
            log.info("file.toPath : "+file.toPath().toString());
            String contentType = Files.probeContentType(file.toPath()); //file의 mimeType text/plian같은

            log.info("contentType : "+contentType);

            return contentType.startsWith("image");

        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    @GetMapping("/viewFile")
    @ResponseBody
    public ResponseEntity<byte[]> viewFile(String fname){
        log.info("fname : "+fname);

        File file = new File("c:\\upload\\" + fname);

        log.info("file : "+file);

        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }
    
}