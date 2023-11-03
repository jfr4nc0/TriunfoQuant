package com.jcanossa.triunfo.services.remarkets;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.jcanossa.triunfo.entity.Instrument;

public class AlgoritmosServiceTests {

	@Test
	public void instrumentInstanceTest() {
		RestTemplate restTemplate = new RestTemplate();
		AlgoritmosService algoritmosService = new AlgoritmosService(restTemplate);
		List<Instrument> listaInstrumentos = algoritmosService.instrumentListBuilder();
		for ( Instrument elem : listaInstrumentos ) {
			System.out.println(elem);
		}
	}
}
