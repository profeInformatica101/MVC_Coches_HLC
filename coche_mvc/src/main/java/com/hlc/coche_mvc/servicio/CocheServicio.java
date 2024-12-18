package com.hlc.coche_mvc.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hlc.coche_mvc.entidad.Coche;
import com.hlc.coche_mvc.repositorio.CocheRepositorio;

@Service
public class CocheServicio {
	CocheRepositorio repositorio;
	
	public CocheServicio(CocheRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public void guardarCoche(Coche coche) {
		repositorio.save(coche);
	}

	public List<Coche> obtenerCoches() {
		List<Coche> coches = new ArrayList<>();
		repositorio.findAll().forEach(coches::add);
		return coches;
	}
	

	public Coche nuevoCoche(Coche coche) {
		 Coche coche_nuevo = repositorio.save(coche);
		 return coche_nuevo;
	}

}
