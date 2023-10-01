package com.jcanossa.triunfo.services.remarkets;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	public ResponseEntity<String> getListaSegmentosDisponibles(){
		HttpHeaders headers = new HttpHeaders();
		
		if (token == null) {this.getToken();}
		
		headers.add("X-Auth-Token", token.get(0));
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		
		String url = remarketUrl+"/rest/segment/all";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		return response;
	}
}
