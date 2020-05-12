package com.cloe.modelo;

public class Cliente {
	private String folio;
	private String nombre;
	private String contacto;
	private String correo;
	private String telefono;
	private String tipo;
	private String domCalle;
	private String domNumero;
	private String domNotas;
	private String domEstado;
	private String domCiudad;
	private String domColonia;
	private String domCP;
	private String perfilId;
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(String folio, String nombre, String contacto, String correo, String telefono, String tipo,
			String domCalle, String domNumero, String domNotas, String domEstado, String domCiudad, String domColonia,
			String domCP, String perfilId) {
		super();
		this.folio = folio;
		this.nombre = nombre;
		this.contacto = contacto;
		this.correo = correo;
		this.telefono = telefono;
		this.tipo = tipo;
		this.domCalle = domCalle;
		this.domNumero = domNumero;
		this.domNotas = domNotas;
		this.domEstado = domEstado;
		this.domCiudad = domCiudad;
		this.domColonia = domColonia;
		this.domCP = domCP;
		this.perfilId = perfilId;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDomCalle() {
		return domCalle;
	}
	public void setDomCalle(String domCalle) {
		this.domCalle = domCalle;
	}
	public String getDomNumero() {
		return domNumero;
	}
	public void setDomNumero(String domNumero) {
		this.domNumero = domNumero;
	}
	public String getDomNotas() {
		return domNotas;
	}
	public void setDomNotas(String domNotas) {
		this.domNotas = domNotas;
	}
	public String getDomEstado() {
		return domEstado;
	}
	public void setDomEstado(String domEstado) {
		this.domEstado = domEstado;
	}
	public String getDomCiudad() {
		return domCiudad;
	}
	public void setDomCiudad(String domCiudad) {
		this.domCiudad = domCiudad;
	}
	public String getDomColonia() {
		return domColonia;
	}
	public void setDomColonia(String domColonia) {
		this.domColonia = domColonia;
	}
	public String getDomCP() {
		return domCP;
	}
	public void setDomCP(String domCP) {
		this.domCP = domCP;
	}
	public String getPerfilId() {
		return perfilId;
	}
	public void setPerfilId(String perfilId) {
		this.perfilId = perfilId;
	}
}
