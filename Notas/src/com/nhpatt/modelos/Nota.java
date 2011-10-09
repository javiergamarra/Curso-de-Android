package com.nhpatt.modelos;

import java.io.Serializable;
import java.util.Date;

public class Nota implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descripcion;
	private Date fecha = new Date();

	public Nota(final String descripcion) {
		this.descripcion = descripcion;
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

}
