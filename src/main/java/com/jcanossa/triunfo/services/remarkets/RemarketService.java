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
	
	public HttpEntity<String> createAuthTokenEntity(){
		HttpHeaders headers = new HttpHeaders();
		
		if (token == null) {this.getToken();}
		
		headers.add("X-Auth-Token", token.get(0));
		
		return (new HttpEntity<String>(headers));
	}
	
	public ResponseEntity<String> doGet(String url){
		ResponseEntity<String> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				createAuthTokenEntity(), 
				String.class);
		
		return response;
	}
	
	public ResponseEntity<String> getListaSegmentosDisponibles(){
		String url = remarketUrl+"/rest/segment/all";
		
		return doGet(url);
	}
	
	public ResponseEntity<String> getListaInstrumentosResumido(){
		String url = remarketUrl+"/rest/instruments/all";
		
		return doGet(url);
	}
	
	public ResponseEntity<String> getListaInstrumentosDetallado(){
		String url = remarketUrl+"/rest/instruments/details";
		
		return doGet(url);
	}
	
	public ResponseEntity<String> getMarketData(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/marketdata/get")
				.queryParam("marketId", params.get("marketId"))
				.queryParam("symbol", params.get("symbol"))
				.queryParam("entries", params.get("entries"))
				.queryParam("depth", params.get("depth"))
				.toUriString();
		
		return doGet(url);
	}
	
	public ResponseEntity<String> getTrades(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/marketdata/get")
				.queryParam("marketId", params.get("marketId"))
				.queryParam("symbol", params.get("symbol"))
				.queryParam("dateFrom", params.get("dateFrom"))
				.queryParam("dateTo", params.get("dateTo"))
				.queryParam("external", params.get("external"))
				.toUriString();

		return doGet(url);
	}
	
	public ResponseEntity<String> getCuentasAsociadas(){
		String url = remarketUrl+"/rest/accounts";
		
		return doGet(url);
	}
}
