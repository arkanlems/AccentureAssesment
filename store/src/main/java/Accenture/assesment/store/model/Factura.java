package Accenture.assesment.store.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
	String id;
	String idCliente;
	long valor;
	boolean iva;
	boolean domicilio;
	List<Item> items;
	Date fecha;
	
	
	public Factura() {
		items = new ArrayList<Item>();
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items.clear();
		this.items.addAll(items);
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public long getValor() {
		return valor;
	}
	public void setValor(long valor) {
		this.valor = valor;
	}
	public boolean isIva() {
		return iva;
	}
	public void setIva(boolean iva) {
		this.iva = iva;
	}
	public boolean isDomicilio() {
		return domicilio;
	}
	public void setDomicilio(boolean domicilio) {
		this.domicilio = domicilio;
	}
	
	
}
