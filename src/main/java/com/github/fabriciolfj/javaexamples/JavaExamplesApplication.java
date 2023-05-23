package com.github.fabriciolfj.javaexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.github.fabriciolfj.javaexamples")
public class JavaExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaExamplesApplication.class, args);
	}
}
