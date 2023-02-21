package com.rf.tienda.util;




/* *****************************************************
 * NOMBRE: ErrorMessages.java
 * 
 * DESCRIPCION:  
 * 			Clase con los String que contienen los mensajes de error 
 * 			especificados por las reglas de negocio.
 * 
 *  @version	Febrero 2021
 *  
 *  @author 	Lorena Acedo
 *  
 *  *****************************************************/
public class ErrorMessages {
	
	
		
	
	/**
	 * Codigo de producto
	 */
	public final static String PROERR_001 = "Formato codigo erroneo";
	public final static String PROERR_002 = "Longitud de codigo erroneo";
	
	/**
	 * Campo con longitud erronea
	 */
	public final static String PROERR_003 = "La longitud de ? ha de estar entre ? y ?";
	
	/**
	 * Campo con formato erroneo
	 */
	public final static String PROERR_004 = "El formato de  ? es erroneo; se espera ? ";

	/**
	 * Campo con rango erroneo
	 */
	public final static String PROERR_005 = "El campo  ? solo acepta valores entre ? y ? ";
	
	/**
	 * Fecha formato erroneo
	 */
	public final static String PROERR_006 = "El campo  ? ha de ser formato dd/mm/yyyy ";
	
	/**
	 * Campo con rango erroneo
	 */
	public final static String PROERR_007 = "El campo  ? ha de ser posterior a la fecha actual ";
	
	

}
