package edu.ucentral.servicio.estudiantes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ucentral.commonestudiantes.model.Estudiante;
import edu.ucentral.commonsservice.service.CommonServiceImpl;
import edu.ucentral.servicio.estudiantes.repository.EstudianteRepository;

@Service
public class EstudianteServiceImpl extends CommonServiceImpl<Estudiante,EstudianteRepository> implements EstudianteService {

	@Override
	public List<Estudiante> findByNombreOrApellido(String temino) {
		return repository.findByNombreOrApellido(temino);
	}

}
