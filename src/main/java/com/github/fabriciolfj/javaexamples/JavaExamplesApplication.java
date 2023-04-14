package com.github.fabriciolfj.javaexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JavaExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaExamplesApplication.class, args);
	}

}
