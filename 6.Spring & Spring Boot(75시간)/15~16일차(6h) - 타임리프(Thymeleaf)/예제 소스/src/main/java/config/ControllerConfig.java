package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.*;

@Configuration
public class ControllerConfig {
		
	@Bean
	public BasicController basicController() {
		return new BasicController();
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
}
