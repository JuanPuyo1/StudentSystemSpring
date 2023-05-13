package edu.ucentral.servicio.estudiantes.service;

import java.util.List;

import edu.ucentral.commonestudiantes.model.Estudiante;
import edu.ucentral.commonsservice.service.CommonService;

public interface EstudianteService extends CommonService<Estudiante> {
	
	public List<Estudiante> findByNombreOrApellido(String temino);
	
}
