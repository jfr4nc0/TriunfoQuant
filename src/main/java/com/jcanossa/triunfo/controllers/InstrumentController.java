package com.jcanossa.triunfo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcanossa.triunfo.entity.Instrument;
import com.jcanossa.triunfo.services.remarkets.InstrumentService;

@RestController
@RequestMapping("/api")
public class InstrumentController {

	@Autowired
	private InstrumentService instrumentService;
	
	@GetMapping("/instrumentos")
	public List<Instrument> obtenerInstruments(){
		return instrumentService.obtenerTodosLosInstrumentos();
	}
}
