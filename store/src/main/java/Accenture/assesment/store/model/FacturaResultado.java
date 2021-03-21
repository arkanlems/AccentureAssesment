package Accenture.assesment.store.model;

public class FacturaResultado {
	String idFactura;
	long valorTransaccion;
	String mensaje;
	
	public String getId() {
		return idFactura;
	}
	public void setId(String id) {
		this.idFactura = id;
	}
	public long getValorTransaccion() {
		return valorTransaccion;
	}
	public void setValorTransaccion(long valorTransaccion) {
		this.valorTransaccion = valorTransaccion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
