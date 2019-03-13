package com.myland;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2018-06-23 12:40
 */
@SpringBootApplication
@MapperScan({"com.myland.**.dao"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
