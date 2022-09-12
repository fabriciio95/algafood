package com.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.domain.service.EnvioEmailService;
import com.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.algafood.infrastructure.service.email.SandBoxEnvioEmailService;
import com.algafood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {
	
	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		switch(emailProperties.getImpl()) {
		case FAKE:
			return new FakeEnvioEmailService();
		case SANDBOX:
			return new SandBoxEnvioEmailService();
		case SMTP:
			return new SmtpEnvioEmailService();
		default:
			return new FakeEnvioEmailService();		 
		}
	}
}
