package com.javateam.springFileUploadProject.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javateam.springFileUploadProject.util.FileNamingEncoder;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class FileDownloadController {
	
	@Autowired
	@Qualifier("fileNamingEncoder")
	private FileNamingEncoder fileNamingEncoder;
	
	@Autowired
	private FileSystemResource uploadDirResource;
	
	private String getBrowser(HttpServletRequest request) {
		
		  String header = request.getHeader("User-Agent");

		  if (header.contains("MSIE")) {
			  return "MSIE";
	  	  } else if(header.contains("Trident")) {
	  		  return "MSIE11";
		  } else if(header.contains("Chrome")) {
			  return "Chrome";
		  } else if(header.contains("Opera")) {
			  return "Opera";
		  }

		  return "Firefox";
	}
	
	// 주의사항) {fileName:.+} 에서 ".+" 를 포함하지 않으면 파일의 확장자가 인자로 넘어오지 않음. 
	@RequestMapping(value="/upload/{fileName:.+}", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, 
    						 HttpServletResponse response, 
    						 @PathVariable("fileName") String fileName) throws IOException {
		
		log.info("####### fileName : "+ fileName);
		
		String filePath = uploadDirResource.getPath() + "/" + fileName;
		
		log.info("########### file Path : " + filePath);
     
        File file = null;
        file = new File(filePath);
         
        if(!file.exists()){
        	
            String msg = "파일이 존재하지 않습니다.";
            log.error(msg);
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        
        if (mimeType == null){
        	
            log.error("mimetype 인지 불가. 기본 Mime으로 설정 !");
            mimeType = "application/octet-stream";
        }
         
        log.error("mimetype : "+mimeType);
        
        // fileName = file.getName();
        // 원래 파일명
        fileName = fileNamingEncoder.decodeFilename(fileName);
        
        // 다운로드시 한글 파일 깨짐 현상 방지
        String header = getBrowser(request);

        if (header.contains("MSIE")) {
        	String docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
        } else if (header.contains("Firefox")) {
	        String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        } else if (header.contains("Opera")) {
	        String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        } else if (header.contains("Chrome")) {
        	String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        }

        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        response.setContentType(mimeType);
        response.setContentLength((int)file.length());
        
        // 원래 파일명으로 저장(다운로드)
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    } //

}