package com.aram.connect;

import com.aram.connect.persistence.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ConnectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("Aram.... init");
		SpringApplication.run(ConnectApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("Aram configured --------------------");
        return application.sources(ConnectApplication.class);
	}
	
	
}
