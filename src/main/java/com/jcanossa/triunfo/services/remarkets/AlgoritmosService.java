package com.jcanossa.triunfo.services.remarkets;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.jcanossa.triunfo.entity.Instrument;
import com.jcanossa.triunfo.utils.JsonUtils;

@Service
public class AlgoritmosService {
	
	private final Logger logger = LoggerFactory.getLogger(AlgoritmosService.class);
	
	private JsonUtils utils = new JsonUtils();
	private Gson gson = new Gson();

	@Value("${url.dev.local}")
	private String url;
	
	@Autowired
	public AlgoritmosService() {
		super();
	}
	
	public List<Instrument> instrumentListBuilder(){
		List<Instrument> instrumentList = null;
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/listaInstrumentosDetallado";
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			
			if (response.getStatusCode() == HttpStatus.OK) {
				JsonObject jsonObject = utils.getJsonObjectFromString(response.getBody());
				
				instrumentList = gson.fromJson(
						jsonObject.get("instruments").getAsString(), 
						new TypeToken<List<Instrument>>() {}.getType());
				
			} else {
				logger.error("HttpStatus = "+response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return instrumentList;
	}
	
}
