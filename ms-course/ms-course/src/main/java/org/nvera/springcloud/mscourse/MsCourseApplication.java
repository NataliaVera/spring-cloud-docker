package org.nvera.springcloud.mscourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCourseApplication.class, args);
	}

}
