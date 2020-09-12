package com.beanio.example;

import com.beanio.example.converter.BeanIoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanIoApplication {
@Autowired
private BeanIoConverter beanIoConverter;
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BeanIoApplication.class, args);
		BeanIoConverter beanIoConverter = new BeanIoConverter();
		beanIoConverter.convert();
	}

}
