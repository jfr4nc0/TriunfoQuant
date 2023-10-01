package com.jcanossa.triunfo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping("/listaSegmentos")
	public ResponseEntity<String> getListaSegmentos(){
		ResponseEntity<String> response = remarketService.getListaSegmentosDisponibles();
		return response;
	}
	
	@GetMapping("/listaInstrumentosResumido")
	public ResponseEntity<String> getListaInstrumentosResumido(){
		ResponseEntity<String> response = remarketService.getListaInstrumentosResumido();
		return response;
	}
	
	@GetMapping("/listaInstrumentosDetallado")
	public ResponseEntity<String> getListaInstrumentosDetallado(){
		ResponseEntity<String> response = remarketService.getListaInstrumentosDetallado();
		return response;
	}
	
	@GetMapping("/marketData")
	public ResponseEntity<String> getMarketData(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.getMarketData(params);
		return response;
	}
	
	@GetMapping("/trades")
	public ResponseEntity<String> getTrades(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.getMarketData(params);
		return response;
	}
	
	@GetMapping("/cuentasAsociadas")
	public ResponseEntity<String> getCuentasAsociada(){
		ResponseEntity<String> response = remarketService.getCuentasAsociadas();
		return response;
	}
}
