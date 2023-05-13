package edu.ucentral.serviciosecurity.service;

import edu.ucentral.commonusuarios.model.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
