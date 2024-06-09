package com.kdk.app.editor.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdk.app.common.util.RequestUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 6. 9. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
@Slf4j
@RestController
@RequestMapping("/editor")
public class EditorFileUploadController {

	// WebMvcConfig addResourceHandlers
	private final String RESOURCE_PATH = "/upload/";
	private final String WEB_APP_ROOT_PATH = "D:/upload/";
	private final String EDITOR_UPLOAD_PATH = "editor";

	@PostMapping("/image-upload")
	public Map<String, Object> uploadEditorImage(@RequestParam(value = "image") MultipartFile image
			, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();

		try {
			/*
			 * Apache Tika로 Mime Type 체크할려 했으나 Class를 못찾음
			 * main 메소드에서는 되는데...
			 */

			String sUploadPath = WEB_APP_ROOT_PATH + EDITOR_UPLOAD_PATH;

			File dir = new File(sUploadPath);
			if ( !dir.exists() ) {
				dir.mkdirs();
			}

			String sOrgFilename = image.getOriginalFilename();
			String sExtension = sOrgFilename.substring(sOrgFilename.lastIndexOf(".") + 1);

			String sUuid = UUID.randomUUID().toString().replaceAll("-", "");
			String sSaveFilename = sUuid + "." + sExtension;

			File saveFile = new File(sUploadPath + "/" + sSaveFilename);

			Path savePath = Paths.get(saveFile.getAbsolutePath());

			if ( !Files.probeContentType(savePath).startsWith("image") ) {
				return map;
			}

			image.transferTo(saveFile);

			String sRequestDomain = RequestUtil.getRequestDomain(request);
			String sFileUrl = sRequestDomain + RESOURCE_PATH + EDITOR_UPLOAD_PATH + "/" + sSaveFilename;

			map.put("fileUrl", sFileUrl);

		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e);
		}

		return map;
	}

}
