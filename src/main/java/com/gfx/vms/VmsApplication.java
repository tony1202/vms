package com.gfx.vms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class VmsApplication{

	public static void main(String[] args) {
		SpringApplication.run(VmsApplication.class, args);
	}


}
