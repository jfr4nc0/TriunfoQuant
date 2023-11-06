package com.jcanossa.triunfo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcanossa.triunfo.services.remarkets.AlgoritmosService;
import com.jcanossa.triunfo.services.remarkets.RemarketService;

@RestController
@RequestMapping("/api")
public class RemarketController {

	@Autowired
	private RemarketService remarketService;
	
	@Autowired
	private AlgoritmosService algoritmosService;
	
	@GetMapping("/auth")
	public ResponseEntity<String> getToken(){
		ResponseEntity<String> response = remarketService.getToken();
		return response;
	}
	
	@GetMapping("/listaSegmentos")
	public ResponseEntity<String> getListaSegmentos(){
		ResponseEntity<String> response = remarketService.getListaSegmentosDisponibles();
		return response;
	}
	
	@GetMapping("/listaInstrumentosResumido")
	public ResponseEntity<String> getListaInstrumentosResumido(){
		ResponseEntity<String> response = remarketService.getListaInstrumentosResumido();
		return response;
	}
	
	@GetMapping("/listaInstrumentosDetallado")
	public ResponseEntity<String> getListaInstrumentosDetallado(){
		ResponseEntity<String> response = remarketService.getListaInstrumentosDetallado();
		return response;
	}
	
	@GetMapping("/marketData")
	public ResponseEntity<String> getMarketData(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.getMarketData(params);
		return response;
	}
	
	@GetMapping("/trades")
	public ResponseEntity<String> getTrades(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarTrades(params);
		return response;
	}
	
	@GetMapping("/enviarOrdenAlMercado")
	public ResponseEntity<String> enviarOrdenAlMercado(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.enviarOrdenAlMercado(params);
		return response;
	}
	
	@GetMapping("/estadoDeOrdenByClientOrdenId")
	public ResponseEntity<String> estadoDeOrdenByClientOrdenId(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarEstadoDeOrdenByClientOrderId(params);
		return response;
	}
	
	@GetMapping("/estadosDeOrdenesByClientId")
	public ResponseEntity<String> estadosDeOrdenesByClientId(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarAllEstadosByClientID(params);
		return response;
	}
	
	@GetMapping("/estadoDeOrdenByOrdenId")
	public ResponseEntity<String> estadoDeOrdenByOrdenId(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarEstadoDeOrdenByOrderId(params);
		return response;
	}
	
	@GetMapping("/ultimosEstadosAsociadosByAccountId")
	public ResponseEntity<String> ultimoEstadosAsociadosByAccountId(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarUltimosEstadosAsociadosByAccountId(params);
		return response;
	}
	
	@GetMapping("/estadoDeOrdenByExecutionId")
	public ResponseEntity<String> estadoDeOrdenByExecutionId(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarEstadoDeOrdenByExecutionId(params);
		return response;
	}
	
	@GetMapping("/ordenesOperadasByAccountId")
	public ResponseEntity<String> ordenesOperadasByAccountId(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarOrdenesOperadasByAccountId(params);
		return response;
	}
	
	@GetMapping("/ordenesActivasByAccountId")
	public ResponseEntity<String> ordenesActivasByAccountId(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarOrdenesActivasByAccountId(params);
		return response;
	}
	
	@GetMapping("/reemplazarOrden")
	public ResponseEntity<String> reemplazarOrden(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.reemplazarOrden(params);
		return response;
	}
	
	@GetMapping("/cancelarOrden")
	public ResponseEntity<String> cancelarOrden(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.cancelarOrden(params);
		return response;
	}
	
	@GetMapping("/cuentasAsociadas")
	public ResponseEntity<String> consultarCuentasAsociadas(){
		ResponseEntity<String> response = remarketService.consultarCuentasAsociadas();
		return response;
	}
	
	@GetMapping("/reporteDeCuenta")
	public ResponseEntity<String> consultarReporteDeCuenta(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarReporteDeCuenta(params);
		return response;
	}
	
	@GetMapping("/reportePosicionDeCuenta")
	public ResponseEntity<String> consultarReportePosicionDeCuenta(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarReporteDeCuenta(params);
		return response;
	}
	
	@GetMapping("/reporteDetalladoDeCuenta")
	public ResponseEntity<String> consultarReporteDetalladoDeCuenta(@RequestParam Map<String, String> params){
		ResponseEntity<String> response = remarketService.consultarReporteDetalladoDeCuenta(params);
		return response;
	}
	
	@GetMapping("/test")
	public String test(){
		algoritmosService.instrumentListBuilder();
		return "OK";
	}
}
