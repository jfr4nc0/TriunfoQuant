package com.jcanossa.triunfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcanossa.triunfo.services.remarkets.RemarketService;

@RestController
@RequestMapping("/api")
public class RemarketController {

	private final RemarketService remarketService;
	
	@Autowired
	public RemarketController(RemarketService remarketService) {
		this.remarketService = remarketService;
	}
	
	@GetMapping("/auth")
	public ResponseEntity<String> getToken(){
		ResponseEntity<String> response = remarketService.getToken();
		return response;
	}
	
}
