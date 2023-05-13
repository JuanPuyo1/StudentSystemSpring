package edu.ucentral.serviciosecurity.oauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import edu.ucentral.commonusuarios.model.Usuario;
import edu.ucentral.serviciosecurity.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String,Object> info = new HashMap<String,Object>();
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		info.put("email", usuario.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
