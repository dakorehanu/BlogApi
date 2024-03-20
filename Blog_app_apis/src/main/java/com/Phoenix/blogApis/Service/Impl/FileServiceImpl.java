package com.Phoenix.blogApis.Service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.Phoenix.blogApis.Service.FileService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage
	(String path, MultipartFile file) throws IOException {
		
		log.info("Intiating dao call to upload postImage");
		String name=file.getOriginalFilename();
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		String filePath=path + File.separator +fileName1;
		File f=new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		log.info("completed dao call to upload post Image");
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		log.info("Intiating dao call to download postImage");
		String fullPath=path+ File.separator+fileName;
		InputStream is=new FileInputStream(fullPath);
		log.info("completed dao call to download post Image");
		
		return is;
	}

	
}
