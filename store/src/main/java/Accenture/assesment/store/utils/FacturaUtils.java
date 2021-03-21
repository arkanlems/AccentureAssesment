package Accenture.assesment.store.utils;

import java.util.List;

import Accenture.assesment.store.model.Cliente;
import Accenture.assesment.store.model.Factura;
import Accenture.assesment.store.model.Item;

public class FacturaUtils {

	public static boolean idExist(List<Cliente> clientes, String id) {
		for (Cliente cliente : clientes) {
			if(cliente.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public static long sumItems(List<Item> items) {
		long cont=0;
		for (Item item : items) {
			cont+=item.getPrecio();
		}
		return cont;
	};
	public static Factura buscarPorId(List<Factura>facturas, String id) {
		for (Factura factura : facturas) {
			if(factura.getId().equals(id)) {
				facturas.remove(factura);
				return factura;
			}
		}
		return null;
	}
	public static boolean preciosMayoresOIguales(List<Item> viejos, List<Item> nuevos) {
		long menorViejos=viejos.get(0).getPrecio();
		for (Item item : viejos) {
			if(item.getPrecio()<menorViejos) {
				menorViejos=item.getPrecio();
			}
		}
		for (Item item : nuevos) {
			if(menorViejos>item.getPrecio()) {
				return false;
			}
		}
		return true;
	}

}
