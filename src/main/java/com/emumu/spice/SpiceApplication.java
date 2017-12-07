package com.emumu.spice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.emumu.spice.mapper")
public class SpiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiceApplication.class, args);
	}
}
