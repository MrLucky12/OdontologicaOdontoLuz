package com.zumaran.odontooga.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zumaran.odontooga.app.models.service.IUploadFileService;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class DemoApplication{

	// @Autowired
	// IUploadFileService UFS;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	UFS.deleteAll();
	// 	UFS.init();
	// }

}
