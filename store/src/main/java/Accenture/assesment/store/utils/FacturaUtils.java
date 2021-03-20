package Accenture.assesment.store.utils;

import java.util.List;

import Accenture.assesment.store.model.Cliente;
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

}
