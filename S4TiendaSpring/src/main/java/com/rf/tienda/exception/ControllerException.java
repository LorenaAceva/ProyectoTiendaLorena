package com.rf.tienda.exception;

@SuppressWarnings("serial")
public class ControllerException extends Exception {
	
	public ControllerException() {
	}

	public ControllerException(String mensaje) {
		super(mensaje);

	}

}
