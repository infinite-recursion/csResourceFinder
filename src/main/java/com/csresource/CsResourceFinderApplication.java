package com.csresource;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
/* @Controller */
public class CsResourceFinderApplication {
	
	/*
	 * @RequestMapping("/")
	 * 
	 * @ResponseBody String home() { return "Hello World!"; }
	 */
	public static void main(String[] args) {
		SpringApplication.run(CsResourceFinderApplication.class, args);
	}

}
