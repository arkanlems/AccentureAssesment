package Accenture.assesment.store.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Accenture.assesment.store.model.Cliente;
import Accenture.assesment.store.model.Factura;
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
	
}
