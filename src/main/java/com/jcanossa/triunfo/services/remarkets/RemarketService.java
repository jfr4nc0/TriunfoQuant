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
	
	// Segmentos disponibles
	public ResponseEntity<String> getListaSegmentosDisponibles(){
		String url = remarketUrl+"/rest/segment/all";
		
		return doGet(url);
	}
	
	// Listado de instrumentos resumido
	public ResponseEntity<String> getListaInstrumentosResumido(){
		String url = remarketUrl+"/rest/instruments/all";
		
		return doGet(url);
	}
	
	// Listado de instrumentos detallado
	public ResponseEntity<String> getListaInstrumentosDetallado(){
		String url = remarketUrl+"/rest/instruments/details";
		
		return doGet(url);
	}
	
	// Obtener MarketData
	public ResponseEntity<String> getMarketData(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/marketdata/get")
				.queryParam("marketId", params.get("marketId"))
				.queryParam("symbol", params.get("symbol"))
				.queryParam("entries", params.get("entries"))
				.queryParam("depth", params.get("depth"))
				.toUriString();
		
		return doGet(url);
	}
	
	// Consultar los trades realizados para un contrato Matba Rofex en un rango de fechas
	public ResponseEntity<String> consultarTrades(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/data/getTrades")
				.queryParam("marketId", params.get("marketId"))
				.queryParam("symbol", params.get("symbol"))
				.queryParam("dateFrom", params.get("dateFrom"))
				.queryParam("dateTo", params.get("dateTo"))
				.queryParam("external", params.get("external"))
				.toUriString();

		return doGet(url);
	}
	
	// Enviar una orden al mercado
	public ResponseEntity<String> enviarOrdenAlMercado(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/newSingleOrder")
				.queryParam("marketId", params.get("marketId"))
				.queryParam("symbol", params.get("symbol"))
				.queryParam("account", params.get("account"))
				.queryParam("side", params.get("side"))
				.queryParam("price", params.get("price"))
				.queryParam("orderQty", params.get("orderQty"))
				.queryParam("ordType", params.get("ordType"))
				.queryParam("timeInForce", params.get("timeInForce"))
				.toUriString();

		return doGet(url);
	}
	
	// Consultar el estado de una orden por ClOrderId
	public ResponseEntity<String> consultarEstadoDeOrdenByClientOrderId(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/id")
				.queryParam("clOrdld", params.get("clOrdld"))
				.queryParam("proprietary", params.get("proprietary"))
				.toUriString();

		return doGet(url);
	}
	
	// Consultar todos los estados por ClientID
	public ResponseEntity<String> consultarAllEstadosByClientID(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/allById")
				.queryParam("clOrdld", params.get("clOrdld"))
				.queryParam("proprietary", params.get("proprietary"))
				.toUriString();

		return doGet(url);
	}

	public ResponseEntity<String> consultarEstadoDeOrdenByOrderId(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/byOrderId")
				.queryParam("orderId", params.get("orderId"))
				.toUriString();

		return doGet(url);
	}
	
	// Consultar el último estado de los request(clOrderId) asociadas a una cuenta
	public ResponseEntity<String> consultarUltimosEstadosAsociadosByAccountId(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/all")
				.queryParam("accountId", params.get("accountId"))
				.toUriString();

		return doGet(url);
	}
	
	// Consultar estado de una orden por ExecId (ExecutionID)
	public ResponseEntity<String> consultarEstadoDeOrdenByExecutionId(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/byExecId")
				.queryParam("execId", params.get("execId"))
				.toUriString();

		return doGet(url);
	}
	
	// Consultar las órdenes operadas
	public ResponseEntity<String> consultarOrdenesOperadasByAccountId(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/filleds")
				.queryParam("accountId", params.get("accountId"))
				.toUriString();

		return doGet(url);
	}
	
	// Consultar las órdenes activas
	public ResponseEntity<String> consultarOrdenesActivasByAccountId(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/actives")
				.queryParam("accountId", params.get("accountId"))
				.toUriString();

		return doGet(url);
	}
	
	// Reemplazar una orden
	public ResponseEntity<String> reemplazarOrden(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/actives")
				.queryParam("clOrdId", params.get("clOrdId"))
				.queryParam("proprietary", params.get("proprietary"))
				.queryParam("orderQty", params.get("orderQty"))
				.queryParam("price", params.get("price"))
				.toUriString();

		return doGet(url);
	}	
	
	// Cancelar una orden
	public ResponseEntity<String> cancelarOrden(Map<String, String> params){
		String url = UriComponentsBuilder.fromHttpUrl(remarketUrl+"/rest/order/cancelById")
				.queryParam("clOrdId", params.get("clOrdId"))
				.queryParam("proprietary", params.get("proprietary"))
				.toUriString();

		return doGet(url);
	}	
	
	// Consultar las cuentas asociadas
	public ResponseEntity<String> consultarCuentasAsociadas(){
		String url = remarketUrl+"/rest/accounts";
		
		return doGet(url);
	}
	
	// Consultar el reporte de una cuenta
	public ResponseEntity<String> consultarReporteDeCuenta(Map<String, String> params){
		String url = remarketUrl+"/rest/risk/accountReport/"+params.get("cuenta").toString();
		
		return doGet(url);
	}
	
	// Consultar el reporte de posicion de una cuenta
	public ResponseEntity<String> consultarReportePosicionDeCuenta(Map<String, String> params){
		String url = remarketUrl+"/rest/risk/position/getPositions/"+params.get("cuenta").toString();
		
		return doGet(url);
	}
	
	// Consultar el reporte detallado de la posicion de una cuenta
	public ResponseEntity<String> consultarReporteDetalladoDeCuenta(Map<String, String> params){
		String url = remarketUrl+"/rest/account/"+params.get("cuenta").toString();
		
		return doGet(url);
	}
}
