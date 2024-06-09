package com.kdk.config.mvc;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import jakarta.servlet.MultipartConfigElement;

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
@Configuration
public class MultipartConfig {

	@Bean
	MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxRequestSize(DataSize.ofMegabytes(50L));
		factory.setMaxFileSize(DataSize.ofMegabytes(50L));

		return factory.createMultipartConfig();
	}

}
