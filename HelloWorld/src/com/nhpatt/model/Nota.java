package com.nhpatt.model;

import java.util.Calendar;
import java.util.Date;

public class Nota {

	private String nota;
	private Date fechaCreacion = Calendar.getInstance().getTime();

	public Nota(String nota) {
		this.nota = nota;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return nota;
	}
}
