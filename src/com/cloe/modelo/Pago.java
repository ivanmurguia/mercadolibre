package com.cloe.modelo;

public class Pago {
	private String id;
	private String fecha;
	private String monto;
	private String estatus;
	private String metodo;
	private String notas;
	private String cuota;
	public Pago() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pago(String id, String fecha, String monto, String estatus, String metodo, String notas, String cuota) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.monto = monto;
		this.estatus = estatus;
		this.metodo = metodo;
		this.notas = notas;
		this.cuota = cuota;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	
}
