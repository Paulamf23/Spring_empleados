package com.gestion.paula.model;
public class DatosNoCorrectosException extends Exception{
	private static final long serialVersionUID = 1L;

	public DatosNoCorrectosException(String mensaje) {
		super(mensaje);
	}
}
