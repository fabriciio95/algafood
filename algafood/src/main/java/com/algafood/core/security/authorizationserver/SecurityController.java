package com.algafood.core.security.authorizationserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String login() {
		return "pages/login";
	}
	
	@GetMapping("/oauth/confirm_access")
	public String approval() {
		//WhitelabelApprovalEndpoint classe responsável por criar pagina padrão do spring
		return "pages/approval";
	}
}
