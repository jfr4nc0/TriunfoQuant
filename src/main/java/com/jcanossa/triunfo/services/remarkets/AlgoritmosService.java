package com.jcanossa.triunfo.services.remarkets;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.jcanossa.triunfo.entity.Instrument;

@Service
public class AlgoritmosService {
	
	private final Logger logger = LoggerFactory.getLogger(AlgoritmosService.class);
	
	@Value("${url.dev.local}")
	private String url;
	
	@Autowired
	public AlgoritmosService() {
		super();
	}
	
//	public Instrument instrumentInstanceBuilder() {
//		url += "/listaInstrumentosDetallado";
//		
//		ResponseEntity<List<Instrument>> res = restTemplate.getForEntity(url, Instrument.class);
//		
//		return instrument;
//	}
	
	public List<Instrument> instrumentListBuilder(){
		List<Instrument> instrumentList = null;
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/listaInstrumentosDetallado";
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			String body = response.getBody();
			Gson gson = new Gson();
			instrumentList = gson.fromJson(body, new TypeToken<List<Instrument>>() {}.getType());
			logger.info(body);
			
			for (Instrument instrument : instrumentList) {
				logger.info(instrument.toString());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return instrumentList;
	}
	
}
