package com.javateam.springFileUploadProject.domain.fileupload;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadForm {
	
	private List<MultipartFile> files;

} //