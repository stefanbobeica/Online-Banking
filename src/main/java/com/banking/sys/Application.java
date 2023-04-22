package com.banking.sys;

import com.banking.sys.service.TwoFaService;
import com.banking.sys.service.TwoFaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {



	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
