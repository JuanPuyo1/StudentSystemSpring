package edu.ucentral.servicio.estudiantes.controller;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.ucentral.commonestudiantes.model.Estudiante;
import edu.ucentral.commonsservice.controller.CommonController;
import edu.ucentral.servicio.estudiantes.service.EstudianteService;

@RestController
public class EstudianteController extends CommonController<Estudiante,EstudianteService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Estudiante estudiante, @PathVariable Long id, BindingResult result){
		if(result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Estudiante> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Estudiante estudianteBd = optional.get();
		estudianteBd.setApellido(estudiante.getApellido());
		estudianteBd.setCodigo(estudiante.getCodigo());
		estudianteBd.setEmail(estudiante.getEmail());
		estudianteBd.setNombre(estudiante.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estudianteBd));
	}
	
	@GetMapping("/filtrar/{termino}")
	public ResponseEntity<?> filtratPorNombreOrApellido(@PathVariable String termino){
		return ResponseEntity.ok(service.findByNombreOrApellido(termino));
	}
	
	@PostMapping("/crear-con-imagen")
	public ResponseEntity<?> crearConImagen(@Valid Estudiante estudiante,BindingResult result, @RequestParam MultipartFile archivo) throws IOException{
		if(!archivo.isEmpty()) {
			estudiante.setImagen(archivo.getBytes());
		}
		return super.crear(estudiante, result);
	}
	
	
	@PutMapping("/editar-con-imagen/{id}")
	public ResponseEntity<?> editarConImagen(@Valid Estudiante estudiante, @PathVariable Long id, BindingResult result, @RequestParam MultipartFile archivo) throws IOException{
		if(result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Estudiante> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Estudiante estudianteBd = optional.get();
		estudianteBd.setApellido(estudiante.getApellido());
		estudianteBd.setCodigo(estudiante.getCodigo());
		estudianteBd.setEmail(estudiante.getEmail());
		estudianteBd.setNombre(estudiante.getNombre());
		if(!archivo.isEmpty()) {
			estudianteBd.setImagen(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estudianteBd));
	}
	
	@GetMapping("upload/img/{id}")
	public ResponseEntity<?> verImagen (@PathVariable Long id) {
		Optional<Estudiante> optional = service.findById(id);
		if(!optional.isPresent() || optional.get().getImagen() == null) {
			return ResponseEntity.notFound().build();
		}
		org.springframework.core.io.Resource img = new ByteArrayResource(optional.get().getImagen());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
	}
}
