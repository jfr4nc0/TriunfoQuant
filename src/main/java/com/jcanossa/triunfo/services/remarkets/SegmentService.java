package com.jcanossa.triunfo.services.remarkets;

import java.util.List;

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
		List<Segment> listaSegmentos = null; // TODO parsear str json a lista de segmentos
		JsonObject jsonObject = utils.getJsonObjectFromString(segmentListString);
		listaSegmentos = gson.fromJson(
				jsonObject.get("segments").getAsJsonArray().toString(), // TODO Array must have size 1, but has size 18, parsear a mano
				new TypeToken<List<Segment>>() {}.getType());
		segmentRepository.saveAllAndFlush(listaSegmentos);
	}
}
