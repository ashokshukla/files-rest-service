package org.test.identity.e2e.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.test.identity.e2e.model.FileInfo;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

@Service
public class FileService {

	@Value("${files.directory.config}")
	private String directoryConfig;

	public List<FileInfo> getFiles(List<String> extensions) throws IOException, SAXException, TikaException {
		List<FileInfo> filesInfo = new ArrayList<>();
		File directory = new File(directoryConfig);
		if(!directory.isDirectory()) {
			throw new IOException("Configured location for \"files.directory.config\"is not a directory.");
		}
		File[] files = directory.listFiles();

		for(File file : files) {
			FileInfo fileInfo = getFileInfo(file);
			if(extensions != null && extensions.contains(fileInfo.getExtension())) {
				filesInfo.add(fileInfo);
			} else if(extensions == null) {
				filesInfo.add(fileInfo);
			}
		}
		return filesInfo;
	}

	private FileInfo getFileInfo(File file) throws FileNotFoundException, IOException, SAXException, TikaException {
		double fileSizeKB = file.length() / 1024;

		FileInputStream inputStream = new FileInputStream(file);
		ContentHandler contenthandler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		Parser parser = new AutoDetectParser();
		ParseContext parseContext = new ParseContext();

		metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
		parser.parse(inputStream, contenthandler, metadata, parseContext);

		return new FileInfo(file.getName(), metadata.get(Metadata.CONTENT_TYPE), String.valueOf(fileSizeKB) + " KB", FilenameUtils.getExtension(file.getName()));
	}
}