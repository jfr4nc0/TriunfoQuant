package com.jcanossa.triunfo.services.remarkets;

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

		response.getHeaders();
		
		return response;
	}
}
