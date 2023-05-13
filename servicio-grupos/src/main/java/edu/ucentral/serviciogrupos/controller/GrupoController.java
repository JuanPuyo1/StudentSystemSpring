package edu.ucentral.serviciogrupos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ucentral.commonestudiantes.model.Estudiante;
import edu.ucentral.commonsservice.controller.CommonController;
import edu.ucentral.serviciogrupos.model.Grupo;
import edu.ucentral.serviciogrupos.service.GrupoService;

@RestController
public class GrupoController extends CommonController<Grupo, GrupoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Grupo grupo, @PathVariable Long id, BindingResult result){
		if(result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Grupo> optional = service.findById(id); 
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Grupo grupoBd = optional.get();
		grupoBd.setCodigo(grupo.getCodigo());
		grupoBd.setNombre(grupo.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(grupoBd));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
	
	@PutMapping("/{id}/adicionar-estudiantes")
	public ResponseEntity<?> adicionarEstudiantes(@RequestBody List<Estudiante> estudiantes, @PathVariable Long id){
		Optional<Grupo> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Grupo grupoBd = optional.get();
		estudiantes.forEach(e ->{
			grupoBd.addEstudiante(e);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(grupoBd));
	}
	
	@PutMapping("/{id}/eliminar-estudiante")
	public ResponseEntity<?> eliminarEstudiante(@RequestBody Estudiante estudiante, @PathVariable Long id){
		Optional<Grupo> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Grupo grupoBd = optional.get();
		grupoBd.removeEstudiante(estudiante);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(grupoBd));
	}
	
	@GetMapping("/estudiante/{id}")
	public ResponseEntity<?> buscarGrupoPorEstudiante(@PathVariable Long id){
		Grupo grupo = service.findGrupoByEstudianteId(id);
		return ResponseEntity.ok(grupo);
	}
}
