package com.hlc.coche_mvc.config;

import org.springframework.stereotype.Component;

import com.hlc.coche_mvc.entidad.Coche;
import com.hlc.coche_mvc.servicio.CocheServicio;

import jakarta.annotation.PostConstruct;

@Component
public class InicializarDatos {
	CocheServicio servicio;
	
	public InicializarDatos(CocheServicio servicio) {
		this.servicio = servicio;
	}
	
	@PostConstruct
	public void init() {
		//String nombre, String matricula, String color
		Coche c1 = new Coche("Kangoo", "1234ABC", "rojo");
		Coche c2 = new Coche("Audi", "4321BCD", "azul");
		
		servicio.guardarCoche(c1);
		servicio.guardarCoche(c2);
	}
}
