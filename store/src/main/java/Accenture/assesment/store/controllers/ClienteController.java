package Accenture.assesment.store.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Accenture.assesment.store.model.Cliente;
import Accenture.assesment.store.model.Factura;
import Accenture.assesment.store.model.FacturaResultado;
import Accenture.assesment.store.model.Item;
import Accenture.assesment.store.utils.FacturaUtils;

@RestController
public class ClienteController {
	
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<Factura> facturas = new ArrayList<Factura>();
	
	@PostMapping("/crearCliente")
	public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente){
		clientes.add(cliente);
		return ResponseEntity.accepted().body("Cliente creado");
	}
	
	@PostMapping("/crearFactura/{idCliente}/{idFactura}")
	public ResponseEntity<Factura> crearFactura(@PathVariable String idCliente,@PathVariable String idFactura, @RequestBody List<Item> item){
		
		if(!FacturaUtils.idExist(clientes, idCliente)) {
			return ResponseEntity.badRequest().body(null);
		}
		Factura factura = new Factura();
		factura.setId(idFactura);
		factura.setIdCliente(idCliente);
		factura.setItems(item);
		factura.setFecha(new Date());
		long valor= FacturaUtils.sumItems(item);
		factura.setIva(true);
		valor+=(valor*0.19);
		if(valor<100000) {
			factura.setDomicilio(true);
			valor+=8000;
		}
		factura.setValor(valor);
		facturas.add(factura);
		return ResponseEntity.accepted().body(factura);
		
		
	}
	@PostMapping("/anadirAlPedido/{idFactura}")
	public ResponseEntity<FacturaResultado> anadirAlPedido(@PathVariable String idFactura, @RequestBody List<Item> item){
		FacturaResultado resultado = new FacturaResultado();
		resultado.setId(idFactura);
		Factura factura = FacturaUtils.buscarPorId(facturas, idFactura);
		
		if(factura==null) {
			resultado.setValorTransaccion(0);
			resultado.setMensaje("No se encontro el pedido");
			return ResponseEntity.badRequest().body(resultado);
		}
		
		Date actual = new Date();
		//long diferencia= TimeUnit.MILLISECONDS.toHours(actual.getTime()-1);
		long diferencia= TimeUnit.MILLISECONDS.toHours(actual.getTime()-factura.getFecha().getTime());
		//System.out.println("diferencia de hora: "+diferencia);
		if(diferencia>5) {
			resultado.setValorTransaccion(0);
			resultado.setMensaje("Ya pasaron 5 horas por lo que no se puede editar el pedido");
			return ResponseEntity.badRequest().body(resultado);
		}
		
		if(!FacturaUtils.preciosMayoresOIguales(factura.getItems(), item)) {
			resultado.setValorTransaccion(0);
			resultado.setMensaje("los nuevos productos no tienen un valor igual o mayor a los del pedido");
			return ResponseEntity.badRequest().body(resultado);
		}
		
		factura.getItems().addAll(item);
		long valor= FacturaUtils.sumItems(factura.getItems());
		valor+=(valor*0.19);
		factura.setDomicilio(false);
		if(valor<100000) {
			factura.setDomicilio(true);
			valor+=8000;
		}
		factura.setValor(valor);
		facturas.add(factura);
		
		resultado.setValorTransaccion(valor);
		resultado.setMensaje("se añadieron los nuevos items el nuevo valor a pagar es: "+valor);
		return ResponseEntity.accepted().body(resultado);
	
	}
	@PostMapping("/cancelarPedido/{idFactura}")
	public ResponseEntity<FacturaResultado> cancelarPedido(@PathVariable String idFactura){
		Date actual = new Date();
		Factura factura = FacturaUtils.buscarPorId(facturas, idFactura);
		FacturaResultado resultado = new FacturaResultado();
		resultado.setId(idFactura);
		if(factura==null) {
			resultado.setValorTransaccion(0);
			resultado.setMensaje("No se encontro el pedido");
			return ResponseEntity.badRequest().body(resultado);
		}
		//long diferencia= TimeUnit.MILLISECONDS.toHours(actual.getTime()-1);
		long diferencia= TimeUnit.MILLISECONDS.toHours(actual.getTime()-factura.getFecha().getTime());
		
		
	}
	
	@GetMapping("/todasLasFacturas")
	public List<Factura> todasLasFacturas(){
		return facturas;
	}
}
