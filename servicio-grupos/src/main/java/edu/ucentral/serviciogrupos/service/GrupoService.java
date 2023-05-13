package edu.ucentral.serviciogrupos.service;

import edu.ucentral.commonsservice.service.CommonService;
import edu.ucentral.serviciogrupos.model.Asignatura;
import edu.ucentral.serviciogrupos.model.Grupo;

public interface GrupoService extends CommonService<Grupo> {
	public Grupo findGrupoByEstudianteId(Long id);
	public Iterable<Asignatura> findAllAsignaturas();
}
