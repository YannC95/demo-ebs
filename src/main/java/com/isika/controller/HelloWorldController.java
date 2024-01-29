package com.isika.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping
	public String helloWorld() {
		return "{'message': 'Hello World! V1 + Jenkinsfile test 2'}";
	}
}
