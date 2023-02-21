package com.rf.tienda.exception;



@SuppressWarnings("serial")
public class DomainException extends RuntimeException {
	
	public DomainException() {
	}

	public DomainException(String mensaje) {
		super(mensaje);

	}
}
