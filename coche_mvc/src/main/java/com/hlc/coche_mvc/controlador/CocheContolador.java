package com.hlc.coche_mvc.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlc.coche_mvc.entidad.Coche;
import com.hlc.coche_mvc.servicio.CocheServicio;

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
		Coche coche = new Coche();
		model.addAttribute("coche", coche);
		return "coche-form";
	}
	
	@PostMapping("/agregar")
	public String nuevoCoche(Model model, @ModelAttribute Coche coche) {
		servicio.nuevoCoche(coche);
			
		return "redirect:/coches";
	}

}
