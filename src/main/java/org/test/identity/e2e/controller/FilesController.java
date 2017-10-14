package org.test.identity.e2e.controller;

import java.io.IOException;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.identity.e2e.model.FileInfo;
import org.test.identity.e2e.service.FileService;
import org.xml.sax.SAXException;

@RestController
@RequestMapping("/files")
public class FilesController {
	
	@Autowired
	private FileService fileService;

	@Value("#{'${files.mime.types.config}'.split(',')}")
	private List<String> configuredExtensions;

	public static final Logger LOGGER = LoggerFactory.getLogger(FilesController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FileInfo> listAllFiles() throws IOException, SAXException, TikaException {
		List<FileInfo> fileInfos = fileService.getFiles(null);
		return fileInfos;
	}

	@RequestMapping(value = "/configFiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FileInfo> listConfiguredFiles() throws IOException, SAXException, TikaException {
		List<FileInfo> fileInfos = fileService.getFiles(configuredExtensions);
		return fileInfos;
	}
}