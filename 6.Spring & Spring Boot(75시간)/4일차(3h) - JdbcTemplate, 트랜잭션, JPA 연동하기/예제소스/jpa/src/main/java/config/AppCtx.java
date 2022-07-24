package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import repository.*;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
}
