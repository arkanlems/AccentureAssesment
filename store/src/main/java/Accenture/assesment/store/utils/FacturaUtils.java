package Accenture.assesment.store.utils;

import java.util.List;

import Accenture.assesment.store.model.Cliente;

public class FacturaUtils {

	public static boolean idExist(List<Cliente> clientes, String id) {
		for (Cliente cliente : clientes) {
			if(cliente.getId()==id)
				return true;
		}
		return false;
	}

}
