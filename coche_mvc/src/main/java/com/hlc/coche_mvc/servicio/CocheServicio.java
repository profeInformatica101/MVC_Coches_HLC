package com.hlc.coche_mvc.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hlc.coche_mvc.entidad.Coche;
import com.hlc.coche_mvc.repositorio.CocheRepositorio;

@Service
public class CocheServicio {
	CocheRepositorio repositorio;
	
	public CocheServicio(CocheRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public Coche guardarCoche(Coche coche) {
		return repositorio.save(coche);
	}

	public List<Coche> obtenerCoches() {
		return repositorio.findAll();
	}
	

	public Coche nuevoCoche(Coche coche) {
		 Coche coche_nuevo = repositorio.save(coche);
		 return coche_nuevo;
	}

	public Coche obtenerCochePorId(Long id) {
		Optional<Coche> coche = repositorio.findById(id);
		return coche.get();
	}

}
