package com.jcanossa.triunfo.services.remarkets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jcanossa.triunfo.entity.Instrument;

@Service
public class AlgoritmosService {
	private final RestTemplate restTemplate;
	
	@Value("${url.dev.local}")
	private String url;
	
	@Autowired
	public AlgoritmosService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Instrument instrumentInstanceBuilder() {
		url += "/listaInstrumentosDetallado";
		Instrument instrument = null;
		
		ResponseEntity<Instrument> res = restTemplate.getForEntity(url, Instrument.class);
		
		return instrument;
	}
	
	public List<Instrument> instrumentListBuilder(){
		List<Instrument> instrumentList = null;
		
		
		return instrumentList;
	}
	
}
