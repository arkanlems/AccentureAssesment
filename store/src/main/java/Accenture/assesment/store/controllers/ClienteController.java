package Accenture.assesment.store.controllers;

import java.util.ArrayList;
import java.util.List;

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
	
	@PostMapping("/crearCliente")
	public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente){
		clientes.add(cliente);
		return ResponseEntity.accepted().body("Cliente creado");
	}
	
	@PostMapping("/crearFactura/{id}")
	public ResponseEntity<String> crearFactura(@PathVariable String id, @RequestAttribute List<Item> item){
		if(!FacturaUtils.idExist(clientes, id)) {
			return ResponseEntity.badRequest().body("No existe el cliente");
		}
		Factura factura = new Factura();
		
		
		return null;
		
		
	}

}
