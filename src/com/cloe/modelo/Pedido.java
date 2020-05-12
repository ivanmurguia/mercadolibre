package com.cloe.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private String folio;
	private String fechaCreacion;
	private String canal;
	private String guia;
	private String almacen;
	private String monto;
	private String costoEnvio;
	private String pagoAprovado;
	private String pagoEstatus;
	private String entregaEstatus;
	private String cumplimientoPagoEstatus;
	private String cumplimientoEntregaEstatus;
	private String entregaMetodo;
	private String pagoPlazo;
	private String moneda;
	private String estaAbierto;
	private String estaCancelado;
	private String tieneAlgunEnvio;
	private String fecha;
	private String idIntegracionFactura;
	private Cliente cliente;
	private List<PedidoML> pedidos;
	public Pedido() {
		super();
		pedidos = new ArrayList<>();
		cliente = new Cliente();
		// TODO Auto-generated constructor stub
	}
	public Pedido(String folio, String fechaCreacion, String canal, String guia, String almacen, String monto,
			String costoEnvio, String pagoAprovado, String pagoEstatus, String entregaEstatus,
			String cumplimientoPagoEstatus, String cumplimientoEntregaEstatus, String entregaMetodo, String pagoPlazo,
			String moneda, String estaAbierto, String estaCancelado, String tieneAlgunEnvio, String fecha,
			String idIntegracionFactura, Cliente cliente, List<PedidoML> pedidos) {
		super();
		pedidos = new ArrayList<>();
		cliente = new Cliente();
		this.folio = folio;
		this.fechaCreacion = fechaCreacion;
		this.canal = canal;
		this.guia = guia;
		this.almacen = almacen;
		this.monto = monto;
		this.costoEnvio = costoEnvio;
		this.pagoAprovado = pagoAprovado;
		this.pagoEstatus = pagoEstatus;
		this.entregaEstatus = entregaEstatus;
		this.cumplimientoPagoEstatus = cumplimientoPagoEstatus;
		this.cumplimientoEntregaEstatus = cumplimientoEntregaEstatus;
		this.entregaMetodo = entregaMetodo;
		this.pagoPlazo = pagoPlazo;
		this.moneda = moneda;
		this.estaAbierto = estaAbierto;
		this.estaCancelado = estaCancelado;
		this.tieneAlgunEnvio = tieneAlgunEnvio;
		this.fecha = fecha;
		this.idIntegracionFactura = idIntegracionFactura;
		this.cliente = cliente;
		this.pedidos = pedidos;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getGuia() {
		return guia;
	}
	public void setGuia(String guia) {
		this.guia = guia;
	}
	public String getAlmacen() {
		return almacen;
	}
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(String costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public String getPagoAprovado() {
		return pagoAprovado;
	}
	public void setPagoAprovado(String pagoAprovado) {
		this.pagoAprovado = pagoAprovado;
	}
	public String getPagoEstatus() {
		return pagoEstatus;
	}
	public void setPagoEstatus(String pagoEstatus) {
		this.pagoEstatus = pagoEstatus;
	}
	public String getEntregaEstatus() {
		return entregaEstatus;
	}
	public void setEntregaEstatus(String entregaEstatus) {
		this.entregaEstatus = entregaEstatus;
	}
	public String getCumplimientoPagoEstatus() {
		return cumplimientoPagoEstatus;
	}
	public void setCumplimientoPagoEstatus(String cumplimientoPagoEstatus) {
		this.cumplimientoPagoEstatus = cumplimientoPagoEstatus;
	}
	public String getCumplimientoEntregaEstatus() {
		return cumplimientoEntregaEstatus;
	}
	public void setCumplimientoEntregaEstatus(String cumplimientoEntregaEstatus) {
		this.cumplimientoEntregaEstatus = cumplimientoEntregaEstatus;
	}
	public String getEntregaMetodo() {
		return entregaMetodo;
	}
	public void setEntregaMetodo(String entregaMetodo) {
		this.entregaMetodo = entregaMetodo;
	}
	public String getPagoPlazo() {
		return pagoPlazo;
	}
	public void setPagoPlazo(String pagoPlazo) {
		this.pagoPlazo = pagoPlazo;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getEstaAbierto() {
		return estaAbierto;
	}
	public void setEstaAbierto(String estaAbierto) {
		this.estaAbierto = estaAbierto;
	}
	public String getEstaCancelado() {
		return estaCancelado;
	}
	public void setEstaCancelado(String estaCancelado) {
		this.estaCancelado = estaCancelado;
	}
	public String getTieneAlgunEnvio() {
		return tieneAlgunEnvio;
	}
	public void setTieneAlgunEnvio(String tieneAlgunEnvio) {
		this.tieneAlgunEnvio = tieneAlgunEnvio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getIdIntegracionFactura() {
		return idIntegracionFactura;
	}
	public void setIdIntegracionFactura(String idIntegracionFactura) {
		this.idIntegracionFactura = idIntegracionFactura;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<PedidoML> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoML> pedidos) {
		this.pedidos = pedidos;
	}
}
