package com.cloe.modelo;

public class Producto {
	private String sku;
	private String codigo;
	private String descripcion;
	private Double cantidad;
	private Double monto;
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto(String sku, String codigo, String descripcion, Double cantidad, Double monto) {
		super();
		this.sku = sku;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.monto = monto;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
}
