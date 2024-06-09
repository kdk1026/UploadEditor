package com.kdk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${upload.folder}")
	private String uploadFolder;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/index.jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/upload/**")
			.addResourceLocations(uploadFolder);
	}

}
