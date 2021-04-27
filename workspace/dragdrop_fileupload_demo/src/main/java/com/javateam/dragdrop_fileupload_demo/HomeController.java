package com.javateam.dragdrop_fileupload_demo;
 
import java.io.File;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j2;
 
@Controller
@Log4j2
public class HomeController {
	
	// log
	//private static final Logger logger = LogManager.getLogger(HomeController.class);
	
    @Autowired
    private FileSystemResource uploadDirResource; // fileUpload metadata wiring
	
	@RequestMapping("/") 
	public String home() {
		
		log.info("##### home ######");
		return "redirect:/fileUpload";
	}
     
    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String dragAndDrop(Model model) {
        
    	log.info("##### fileUpload ######");
        return "fileUpload";
    }
     
    @RequestMapping(value = "/fileUpload/post")
    @ResponseBody
    public String upload(MultipartHttpServletRequest multipartRequest) { 
          
    	log.info("##### /fileUpload/post ######");
    	
        Iterator<String> itr =  multipartRequest.getFileNames();
         
        String filePath = uploadDirResource.getPath();
         
        while (itr.hasNext()) { 
             
            /* 
            MultipartFile mpf = multipartRequest.getFile(itr.next());
            String originFileName = mpf.getOriginalFilename();
            log.info("FILE_INFO: "+originFileName); 
            */
             
            MultipartFile mpf = multipartRequest.getFile(itr.next());
      
            String originalFilename = mpf.getOriginalFilename(); //파일명
      
            String fileFullPath = filePath + "/" + originalFilename; //파일 전체 경로
      
            try {
                mpf.transferTo(new File(fileFullPath)); //파일저장 실제로는 service에서 처리
                 
                log.info("originalFilename : "+originalFilename);
                log.info("fileFullPath : "+fileFullPath);
      
            } catch (Exception e) {
            	
                log.error("File upload error : "+fileFullPath);
                e.printStackTrace();
            }
                          
        } // while
          
        return "success";
    } //
     
}