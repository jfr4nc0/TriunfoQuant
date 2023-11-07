package com.jcanossa.triunfo.services.remarkets;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jcanossa.triunfo.dto.SegmentRepository;
import com.jcanossa.triunfo.entity.Segment;
import com.jcanossa.triunfo.utils.JsonUtils;

@Service
public class SegmentService {

	@Autowired
	private SegmentRepository segmentRepository;
	
	private final Logger logger = LoggerFactory.getLogger(SegmentService.class);
	
	private JsonUtils utils = new JsonUtils();
	private Gson gson = new Gson();
	
	public void guardarListaSegmentos(String segmentListString) {
		JsonObject jsonObject = utils.getJsonObjectFromString(segmentListString);
		
		List<Segment> lista_segmentos_nueva = gson.fromJson(
				jsonObject.get("segments").getAsJsonArray().toString(),
				new TypeToken<List<Segment>>() {}.getType()); // TODO Armar lista sin los ids
		
		// Chequear si hay nuevos cambios y actualizar la BD
		List<Segment> lista_segmentos_actual = segmentRepository.findAll();
		
		if (!lista_segmentos_actual.isEmpty()) {
			
			try {
				// Comparar listas para encontrar diferencias
				List<Segment> aux_actual = lista_segmentos_actual.stream()
						.map(segmento -> new Segment(segmento.getMarketId(), segmento.getMarketSegmentId()))
						.collect(Collectors.toList());
				if (!(aux_actual.containsAll(lista_segmentos_nueva) 
						&& lista_segmentos_nueva.containsAll(aux_actual))) {
					// Listas distintas, actualizo los elementos nuevos en la db
					// Tomo los elementos que son nuevos y los agrego a la db
					List<Segment> aux_nuevos_segmentos = lista_segmentos_nueva.stream()
							.filter(segmento -> !aux_actual.contains(segmento))
							.collect(Collectors.toList());
					segmentRepository.saveAllAndFlush(aux_nuevos_segmentos);
				} else {
					// else listas iguales por lo tanto no se actualiza
					logger.debug("No se encontraron nuevos segmentos para actualizar en la BD");
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			

		} else {
			segmentRepository.saveAllAndFlush(lista_segmentos_nueva);
			logger.info("DATABASE: Tabla de Segmentos actualizada");
		}

	}
}
