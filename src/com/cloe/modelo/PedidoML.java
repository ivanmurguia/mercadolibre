package com.cloe.modelo;

public class PedidoML {
	private String folio;
	private Producto producto;
	private Pago pago;
	public PedidoML() {
		super();
		producto = new Producto();
		pago = new Pago();
		// TODO Auto-generated constructor stub
	}
	public PedidoML(String folio, Producto producto, Pago pago) {
		super();
		this.folio = folio;
		this.producto = producto;
		this.pago = pago;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
}
