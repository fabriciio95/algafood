package com.algafood.infrastructure.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.algafood.core.email.EmailProperties;

public class SandBoxEnvioEmailService extends SmtpEnvioEmailService {
	

	@Autowired
	private EmailProperties emailProperties;

	@Override
	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
		
		MimeMessage mimeMessage = super.criarMimeMessage(mensagem);
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setTo(emailProperties.getSandbox().getDestinatario());
		
		return mimeMessage;
	}
}
