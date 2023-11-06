package com.jcanossa.triunfo.services.remarkets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcanossa.triunfo.dto.InstrumentRepository;
import com.jcanossa.triunfo.entity.Instrument;

@Service
public class InstrumentService {
	
	@Autowired
	private InstrumentRepository instrumentRepository;
	
	public List<Instrument> obtenerTodosLosInstrumentos(){
		return (List<Instrument>) instrumentRepository.findAll();
	}
	
	public Instrument guardarInstrumento(Instrument instrument) {
		return instrumentRepository.save(instrument);
	}
}
