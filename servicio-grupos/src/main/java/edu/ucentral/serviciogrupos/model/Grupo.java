package edu.ucentral.serviciogrupos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.ucentral.commonestudiantes.model.Estudiante;

@Entity
@Table(name="grupos")
public class Grupo implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	@NotEmpty(message="No puede estar vacio")
	@Size(min = 3, max = 30, message="El numero de caracteres debe estar entre 3 y 30")
	private String nombre;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@ManyToOne
	@NotNull
	private Asignatura asignatura;
	@OneToMany
	private List<Estudiante> estudiantes;
	@PrePersist
	public void prePersist() {
		createAt = new Date(); 
	}

	public Grupo() {
		estudiantes = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	public void addEstudiante(Estudiante estudiante) {
		estudiantes.add(estudiante);
	}
	
	public void removeEstudiante(Estudiante estudiante) {
		estudiantes.remove(estudiante);
	}

	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Grupo)) {
			return false;
		}
		
		Grupo g = (Grupo) obj;
		return this.id!=null && this.id.equals(g.getId());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3541403606405584200L;
}
