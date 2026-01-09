package com.nick.TaroDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.nick.TaroDemo.mapper"})
@SpringBootApplication(scanBasePackages = "com.nick")
public class TaroDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaroDemoApplication.class, args);
	}

}
