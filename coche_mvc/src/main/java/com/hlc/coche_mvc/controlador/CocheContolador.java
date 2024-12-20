package com.hlc.coche_mvc.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlc.coche_mvc.entidad.Coche;
import com.hlc.coche_mvc.servicio.CocheServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/coches")
public class CocheContolador {
	CocheServicio servicio;
	
	public CocheContolador(CocheServicio servicio) {
		this.servicio = servicio;
	}
	
	@GetMapping
	public String mostrarIndex(Model model) {
		
		List<Coche>coches = servicio.obtenerCoches();
		model.addAttribute("coches", coches);
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String mostrarForm(Model model) {
		model.addAttribute("coche", new Coche());
		return "coche-form";
	}
	
	@PostMapping("/agregar")
	public String nuevoCoche(@Valid @ModelAttribute Coche coche, BindingResult bindingResult ) {
	
		if(bindingResult.hasErrors()) {
			 return "coche-form";
		}
		
		servicio.nuevoCoche(coche);	
		return "redirect:/coches";
	}

	@GetMapping("/editar/{id}")
	public String editarCoche(Model model, @PathVariable Long id) {
		Coche coche = servicio.obtenerCochePorId(id);
		model.addAttribute("coche", coche);
		return "coche-form";
	}
}
