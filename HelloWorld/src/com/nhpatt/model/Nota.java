package com.nhpatt.model;

import java.util.Calendar;
import java.util.Date;

public class Nota {

	private String descripcion;
	private Date fechaCreacion = Calendar.getInstance().getTime();

	public Nota(String nota, Date date) {
		this.descripcion = nota;
		this.fechaCreacion = date;
	}

	public Nota(String nota) {
		this.descripcion = nota;
	}

	public String getNota() {
		return descripcion;
	}

	public void setNota(String nota) {
		this.descripcion = nota;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return descripcion;
	}
}
