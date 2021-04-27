package com.javateam.springFileUploadProject.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.springFileUploadProject.domain.fileupload.FileUploadForm;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class FileUploadController2 {
	
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/saveRelativePath", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm, Model model, BindingResult result) {

		List<MultipartFile> files = uploadForm.getFiles();
		List<String> fileNames = new ArrayList<>(); // 파일(들)명
		List<String> filePathList = new ArrayList<>(); // 파일(들) 경로

		// 오류(에러) 처리
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "/show";
		}

		if (!uploadForm.getFiles().isEmpty() && files != null && files.size() > 0) { // 파일(들) 유효성 점검

			log.info("파일 갯수 : " + files.size());

			// 파일 정보
			for (int i = 0; i < files.size(); i++) {

				String tmpFileName = uploadForm.getFiles().get(i).getOriginalFilename();
				String tmpFileExt = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1, tmpFileName.length());

				log.info("파일명 - 확장자 :  " + tmpFileName + "-" + tmpFileExt);

			} // for

			// 업로드 파일 확장자 제한 : 그림 파일 업로드 게시판용
			/*
			 * if (tmpFileExt.equalsIgnoreCase("JPG") || tmpFileExt.equalsIgnoreCase("JPEG")
			 * || tmpFileExt.equalsIgnoreCase("GIF")) {
			 */

			// if (files != null && files.size() > 0) {

			FileOutputStream fos = null;

			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				fileNames.add(fileName);

				// 파일 경로(들) 저장 : 상대 경로
				String tempPath = servletContext.getRealPath("/resources/upload/");
				filePathList.add(tempPath + fileName);
				log.info("### 상대 경로 file path : " + tempPath);

				try {
					// byte[] bytes = uploadForm.getFiles().get(i).getBytes();					
					byte[] bytes = multipartFile.getBytes();
					
					/* // 방법-1
					File outFileName = new File(tempPath + fileName);
					fos = new FileOutputStream(outFileName);
					fos.write(bytes);
				 	*/	
					
					// 방법-2
					// Files.write(Paths.get(tempPath + fileName), bytes);
					
					// 방법-3
					multipartFile.transferTo(new File(tempPath + fileName));
					
				} catch (IOException e) {
					log.error("#### FileUploadController2 save File writing error ! ");
					e.printStackTrace();
				} finally {

					try {
						if (fos != null) fos.close();
					} catch (IOException e) {
						log.error("FileUploadController save IOE : ");
						e.printStackTrace();
					}

					log.info("File upload success! ");
				} // try

			} // for

			// } // if

		} else {
			log.error("### File type error! ");
		}

		model.addAttribute("files", fileNames);
		model.addAttribute("filePathList", filePathList);

		return "file_upload_success2";
	} //

} //