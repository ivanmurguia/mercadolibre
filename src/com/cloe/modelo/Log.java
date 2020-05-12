package com.cloe.modelo;

import java.util.Date;

public class Log {
	private String pedido;
	private String status;
	private String mensaje;
	private Date fecha;
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Log(String pedido, String status, String mensaje, Date fecha) {
		super();
		this.pedido = pedido;
		this.status = status;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}
	public String getPedido() {
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
