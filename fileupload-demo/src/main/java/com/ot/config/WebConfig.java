package com.ot.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		Path signatureUploadDir = Paths.get("./signature");
		String signatureUploadPath = signatureUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/signature/**").addResourceLocations("file:/" + signatureUploadPath + "/");
		
	}
}
