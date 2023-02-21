package com.rf.tienda.exception;


@SuppressWarnings("serial")
public class DAOException extends Exception {
	
	public DAOException() {
	}

	public DAOException(String mensaje) {
		super(mensaje);

	}
}
