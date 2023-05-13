package edu.ucentral.serviciosecurity.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ucentral.commonusuarios.model.Usuario;

@FeignClient(name="servicio-usuarios")
public interface UsuarioFeignClient {

	@GetMapping("/buscar-username")
	public Usuario findByUsername(@RequestParam String username);

}
