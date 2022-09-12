package com.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.core.email.EmailProperties.Implementacao;
import com.algafood.domain.service.EnvioEmailService;
import com.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.algafood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {
	
	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		if(emailProperties.getImpl().equals(Implementacao.SMTP)) {
			return new SmtpEnvioEmailService();
		}
		
		return new FakeEnvioEmailService();
	}
}
