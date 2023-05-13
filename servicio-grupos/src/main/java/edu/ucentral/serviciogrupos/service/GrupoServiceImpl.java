package edu.ucentral.serviciogrupos.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ucentral.commonsservice.service.CommonServiceImpl;
import edu.ucentral.serviciogrupos.model.Asignatura;
import edu.ucentral.serviciogrupos.model.Grupo;
import edu.ucentral.serviciogrupos.repository.AsignturaRepository;
import edu.ucentral.serviciogrupos.repository.GrupoRepository;

@Service
public class GrupoServiceImpl extends CommonServiceImpl<Grupo, GrupoRepository> implements GrupoService {

	@Autowired
	private AsignturaRepository asignaturaRepo;
	
	@Override
	@Transactional(readOnly = true)
	public Grupo findGrupoByEstudianteId(Long id) {
		return repository.findGrupoByEstudianteId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Asignatura> findAllAsignaturas() {
		return asignaturaRepo.findAll();
	}

	
}
