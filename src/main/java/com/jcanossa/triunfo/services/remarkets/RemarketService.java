package com.jcanossa.triunfo.services.remarkets;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RemarketService {
	private final RestTemplate restTemplate;
	
	@Value("${remarkets.api}")
	private String remarketUrl;
	
	@Value("${remarkets.username}")
	private String username;
	
	@Value("${remarkets.password}")
	private String password;
	
	private static List<String> token = null;
	
	@Autowired
	public RemarketService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public ResponseEntity<String> getToken(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Username", username);
		headers.add("X-Password", password);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		
		String url = remarketUrl+"/auth/getToken";
		ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

		token = response.getHeaders().get("X-Auth-Token");
		
		return response;
	}
	
	public HttpHeaders getHeaderAuthToken(){
		HttpHeaders headers = new HttpHeaders();
		
		if (token == null) {this.getToken();}
		
		headers.add("X-Auth-Token", token.get(0));
		
		return headers;
	}
	
	public ResponseEntity<String> getListaSegmentosDisponibles(){
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeaderAuthToken());

		String url = remarketUrl+"/rest/segment/all";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		return response;
	}
	
	public ResponseEntity<String> getListaInstrumentosResumido(){
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeaderAuthToken());
		
		String url = remarketUrl+"/rest/instruments/all";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		return response;
	}
	
	public ResponseEntity<String> getListaInstrumentosDetallado(){
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeaderAuthToken());
		
		String url = remarketUrl+"/rest/instruments/details";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		return response;
	}
	
	public ResponseEntity<String> getMarketData(Map<String, String> params){
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeaderAuthToken());
		
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/marketdata/get")
				.queryParam("marketId", params.get("marketId"))
				.queryParam("symbol", params.get("symbol"))
				.queryParam("entries", params.get("entries"))
				.queryParam("depth", params.get("depth"))
				.toUriString();
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		return response;
	}
	
	public ResponseEntity<String> getCuentasAsociadas(){
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeaderAuthToken());
		
		String url = remarketUrl+"/rest/accounts";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		return response;
	}
}
