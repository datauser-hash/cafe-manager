package com.datauser.cafemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleSelectionController {

	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	// add request mapping for /leaders

	@GetMapping("/manager")
	public String showManagers() {
		
		return "manager";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/waiter")
	public String showSystems() {
		
		return "waiter";
	}
	
}










