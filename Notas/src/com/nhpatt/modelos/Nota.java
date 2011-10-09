package com.nhpatt.modelos;

import java.io.Serializable;
import java.util.Date;

public class Nota implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String descripcion;
	private Date fecha = new Date();

	public Nota(final String descripcion) {
		this.descripcion = descripcion;
	}

	public Nota(final int id, final String descripcion, final Date fecha) {
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return descripcion + " (" + fecha.toGMTString() + ")";
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

}
