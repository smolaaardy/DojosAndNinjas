package com.smolaardy.dojosandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smolaardy.dojosandninjas.models.Dojo;
import com.smolaardy.dojosandninjas.models.Ninja;
import com.smolaardy.dojosandninjas.services.DojoService;

@Controller
public class DojoController {

	
	private final DojoService dojoService;
	
	public DojoController(DojoService dojoService) {
		this.dojoService = dojoService;
	}
	 
	@RequestMapping("/")
	public String main(Model model) {
		return "redirect:/dojos/new";
		
	}
	
	@RequestMapping("/dojos/new")
	public String addDojo(Model model) {
		model.addAttribute("dojo", new Dojo());
		return "newDojo.jsp";
	}
	
	@PostMapping("/dojos/new")
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/dojos/new";
		}
		else {
			Long newdojo = dojoService.addDojo(dojo);
			return "redirect:/dojos/" + newdojo;
		}
	}
	
	@RequestMapping("dojos/{id}")
	public String showDojo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dojo", dojoService.findDojo(id));
		List<Ninja> ninjas = dojoService.findDojo(id).getNinjas();
		model.addAttribute("ninjas", ninjas);
		
		return "dojoDetail.jsp";
	}
	
}
