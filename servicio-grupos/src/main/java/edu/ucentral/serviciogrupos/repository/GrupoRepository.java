package edu.ucentral.serviciogrupos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ucentral.serviciogrupos.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	@Query("SELECT g FROM Grupo g join fetch g.estudiantes e WHERE e.id=?1")
	public Grupo findGrupoByEstudianteId(Long id);
}
