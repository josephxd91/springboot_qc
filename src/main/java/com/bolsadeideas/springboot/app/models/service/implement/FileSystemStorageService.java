package com.bolsadeideas.springboot.app.models.service.implement;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.app.models.service.IStorageService;

public class FileSystemStorageService implements IStorageService {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
