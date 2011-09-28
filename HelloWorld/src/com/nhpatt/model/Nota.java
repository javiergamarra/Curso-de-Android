package com.nhpatt.model;

import java.util.Calendar;
import java.util.Date;

public class Nota {

	private Integer id;
	private String descripcion;
	private Date fechaCreacion = Calendar.getInstance().getTime();

	public Nota(Integer id, String descripcion, Date date) {
		this.descripcion = descripcion;
		this.fechaCreacion = date;
		this.id = id;
	}

	public Nota(String descripcion, Date date) {
		this.descripcion = descripcion;
		this.fechaCreacion = date;
	}

	public Nota(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
