package com.hlc.coche_mvc.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Coche {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(min = 2, max = 50, message = "El nombre de tener entre 2 y 50 carácteres")
	private String nombre;
	
	@Pattern(regexp = "^\\d{4}[A-Za-z]{3}$", message = "La matrícula debe tener 4 dígitos seguidos de 3 letras (ej: 5251MHX).")
	private String matricula;
	
	@Pattern(regexp = "^(rojo|azul|verde|amarillo)$", message = "El color debe ser uno de: rojo, azul, verde o amarillo.")
	private String color;

	public Coche() {
		super();
	}

	public Coche(String nombre, String matricula, String color) {
		super();
		this.nombre = nombre;
		this.matricula = matricula;
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
