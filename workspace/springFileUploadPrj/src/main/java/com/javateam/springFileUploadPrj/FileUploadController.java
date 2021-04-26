package com.javateam.springFileUploadPrj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.springFileUploadPrj.domain.fileUpload.FileUploadForm;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Slf4j
public class FileUploadController {
	
       @Autowired
	   private FileSystemResource uploadDirResource; // fileUpload metadata wiring

	   @RequestMapping(value = "/show", method=RequestMethod.GET)
	   public String displayForm() {
		   
		   log.info("show");
		   
	       return "file_upload_form";
	   }
	     
	   @RequestMapping(value = "/save", method = RequestMethod.POST)
	   public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm,
       					  Model model,
       					  BindingResult result) 
	   { 
		   log.info("save");
		   
		   List<MultipartFile> files = uploadForm.getFiles();
	       List<String> fileNames = new ArrayList<String>(); // 파일(들)명(인자 전송용)
	    	
	    	// 썸네일(thumbnail picture) 파일(인자 전송용)
	    	List<String> thumbFileNames = new ArrayList<String>();
	    	
	    	// 오류(에러) 처리
	    	 if (result.hasErrors()) {
	               for (ObjectError error : result.getAllErrors()) {
	                    System.err.println("Error: " + error.getCode() + " - " 
	                    				   + error.getDefaultMessage());
	               }
	               return "/show";	               
	          } 

	    	  if  (!uploadForm.getFiles().isEmpty() &&
	    			  files != null &&
	    			  files.size() > 0) { // 파일(들) 유효성 점검

			      log.info("파일 갯수 : " + files.size());
			      
			      String tmpFileName = "";
			      String tmpFileExt = "";
			      				      
			      // 파일 정보
			      for (int i=0; i<files.size(); i++) {
			      
				       tmpFileName = uploadForm.getFiles()
				    		   				   .get(i)
				    		   				   .getOriginalFilename();
				       
		               tmpFileExt = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1, 
		            		   							  tmpFileName.length());
		               
		               log.info("파일명 - 확장자 :  " 
		            		   			  + tmpFileName + "-" 
		            		   			  + tmpFileExt);
	               
			      } // for
			      
			     // 업로드 파일 확장자 제한 : 그림 파일 업로드 게시판용
			     if  (tmpFileExt.equalsIgnoreCase("JPG") || 
		    		  tmpFileExt.equalsIgnoreCase("JPEG") ||
		    		  tmpFileExt.equalsIgnoreCase("PNG") ||
		    		  tmpFileExt.equalsIgnoreCase("GIF")) {    
			    	 
			    	 log.info("그림 파일 확장자 OK !");

			      //  if (files != null  && files.size() > 0) {
			      
		          FileOutputStream fos = null;
		        	
	            for (MultipartFile multipartFile : files) {
	 
	                String fileName = multipartFile.getOriginalFilename();
	                fileNames.add(fileName);
	                
	                //  파일 저장소(k:\\upload\\image) 에 저장				                
                    try {
            			 // byte[] bytes = uploadForm.getFiles().get(i).getBytes(); 
    			         byte[] bytes = multipartFile.getBytes();
            	
                         File outFileName 
                         		    = new File(uploadDirResource.getPath() 
                         		    		   + fileName);
                         
                         // 썸네일(thumbnail) path : PNG 형식으로 저장
                         // ex) k:\\upload\\image\\thumbnail
                         String thumbPath = uploadDirResource.getPath() + "thumbnail/";
                         String thumbPathFileName = "thumb_"
                        		 				  + fileName.split("\\.")[0]
                		 						  +".png";
                         
                         fos = new FileOutputStream(outFileName);
                         
                         fos.write(bytes);
                         
                         // 썸네일 파일 저장 시작
                         // https://github.com/coobird/thumbnailator
                         // 200*100 크기의 썸네일 작성
                         
                         File thumbnail = new File(thumbPathFileName); 
                         //if (outFileName.exists()) { 
                    	 // thumbnail.getParentFile().mkdirs(); 
                         
                         Thumbnails.of(outFileName)
		              	 		   .size(200, 100)
		      	 				   .outputFormat("png")
		              	 		   .toFile(thumbPath + thumbnail); 
                    	// }                        
                        // 썸네일 파일 저장 끝
                         
                        // 썸네일 파일 인자 저장
                        thumbFileNames.add(thumbPathFileName);
                         
                    } catch (IOException e) {
                    	log.info("FileUploadController : save File writing error ! ");
                    	e.printStackTrace();
                    } finally {
                          try { 	
                        	  		if (fos !=null) fos.close(); 
                    	  } catch (IOException e) {
	                        	  log.info("FileUploadController save IOE : ");
								  e.printStackTrace();
						}
                    	  log.info("File Upload Success! ");
                    } // try
	 
	            } // for
				
	         // if : 그림 파일 점검   
		     } else {
		    	 
		    	 log.info("올바른 그림 파일 형식이 아닙니다.");
		    	 return "/error/image_error";
		     } //			     
			        
	    } else {
  				log.info("File type error! ");
        }
        
	    // 전송 인자(원(original) 이미지, 썸네일 이미지)	  
        model.addAttribute("files", fileNames);
        model.addAttribute("thumbFiles", thumbFileNames);
        
        return "file_upload_success";
    } //

} //