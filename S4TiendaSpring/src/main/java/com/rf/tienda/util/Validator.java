package com.rf.tienda.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/********************************************************************************************
 * NOMBRE: Validator.java
 * 
 * DESCRIPCION: 
 * 			Clase auxiliar para validar los datos que sean 
 * 			introducidos en la aplicacion.
 * 
 *  @version	30/01/2023 
 *  @author 	Lorena Acedo
 *  
 * ******************************************************************************************/
public class Validator {
	
	private static final String ALFANUMERIC_PATTERN = "^[0-9a-zA-Z]*$";
	
	private static final String PASSWORD_PATTERN = 
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	/**
	 * Patron para validar el email, evitando puntos finales antes de la @ y que solo contenga
	 * car�cteres alfanumericos		 
	 */
	private final static String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
	/**
	 * Permite verificar que un DNI cumple con el patron XX.XXX.XXX-L
	 */
	private final static String DNI_PATTERN = "^[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Za-z]$";
		
	/**
	 * Permite validar un telefono, el cual debe contener de 10 a 20 digitos
	 * y donde los espacios estan permitidos
	 */
	private final static String PHONE_PATTERN =  "[\\d ]{10,20}";
	
	/**
	 * Orden de las letras con las cuales se comprobara la validez del DNI
	 */
	private final static String LETRA_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	/**
	 * Formato que debe tener la fecha introducida
	 */
	private static final String DATE_FORMAT = "dd/MM/yyyy";

	/* ***************************************************************************************
	 * NOMBRE: isAlfanumeric                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Permite verificar que el texto pasado solo contiene caracters alfanumericos
	 * 
	 * @param texto String a verificar que solo tenga carácteres alfanumericos
	 * 
	 * @return  true, si cumple solo contiene caracters alfanumericos. <br> 
	 * 			false en caso contrario
	 * FECHA: Enero 2023
	 * 
	 * AUTOR: Lorena Acedo
	 * 
	 * **************************************************************************************/
	public static boolean isAlfanumeric(String texto){
		
		/**
		 * Definimos un patrón de búsqueda para nuestra cadena de carácteres
		 */
		Pattern p= Pattern.compile(ALFANUMERIC_PATTERN);
		
		
		/**
		 * La clase pattern contiene el método matcher que nos ayudará a encontrar coincidencias
		 */
		
		Matcher m= p.matcher(texto);
		
		/**
		 * Devolvemos si el String coincide con la constante
		 */
		return m.matches();
	}
	
	
	
	/* ***************************************************************************************
	 * NOMBRE: cumplePhoneNumber                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		El phone number debe tener un total de entre 10 y 20, contando digitos y espacios.
	 * 		Minimo aceptable son 10 digitos.
	 * 
	 * @param phoneNumber String con el número de telefono de entre 10 y 20 dgitos. 
	 * 		Puede tener espacios, pero siempre con 10 digitos como minimo.
	 * 
	 * @return true, si cumple todas las condiciones
	 *
	 * FECHA: Enero 2023
	 * AUTOR: Lorena Acedo
	 * 
	 * **************************************************************************************/
	public static boolean cumplePhoneNumber(String phoneNumber){
		if (phoneNumber.length()>20|| phoneNumber.length()<10){
			return false;
		}		
		
		Pattern p=Pattern.compile(PHONE_PATTERN);
		Matcher m=p.matcher(phoneNumber);
		return m.matches();
	}

	/* ***************************************************************************************
	 * NOMBRE: isEmailValido                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 			Comprueba si el email tiene un formato que pueda ser válido.
	 * 
	 * @param email String a comprobar
	 * 
	 * @return true, en caso que el formato sea válido
	 * FECHA: Enero 2023
	 * 
	 * AUTOR: Lorena Acedo
	 * 
	 * **************************************************************************************/
	public static boolean isEmailValido(String email){
		if (email != null) {
			Pattern p=Pattern.compile(EMAIL_PATTERN);
			Matcher m=p.matcher(email);
			return m.matches();
		}
		return true;
	}
		
		

	/* ***************************************************************************************
	 * NOMBRE: cumpleDNI                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * Esta funcion verifica que el DNI cumple el siguiente formato: xx.xxx.xxx-L <br>
	 * El DNI ha de tener longitud 12
	 * 
	 * @param dni String con DNI a ser validado
	 * 
	 * @return true, si el DNI cumple el estandar nacional.
	 * 
	 * El filtro creado comprueba la veracidad del DNI
	 * 
	 * FECHA: Enero 2023
	 * AUTOR: Lorena Acedo
	 * 
	 * **************************************************************************************/
	public static boolean cumpleDNI(String dni){
				
		/**
		 * Comprobamos el patrón del DNI
		 */
		Pattern p=Pattern.compile(DNI_PATTERN);
		Matcher matcher = p.matcher(dni);
	    if (!matcher.matches()) {
	      return false;
	    }
	 
	    /**
	     * 
	     */
	    char letter = dni.charAt(dni.length() - 1);
	    String number = dni.substring(0, dni.length() - 2).replace(".", "");
	    return letter==calculaLetra(number);
		}
	
		/**
		 * Se calcula que la letra sea la correcta
		 * @param number
		 * @return
		 */
	    public static char calculaLetra(String number) {
	    int dniValor=Integer.parseInt(number);
	    char[] letra= LETRA_DNI.toCharArray();
	    return letra[dniValor%23];
	    }  
	

	/** ***************************************************************************************
	 * NOMBRE: cumpleRango                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprueba que un Número se necuentra entre 2 valores
	 * 
	 * @param valor (int)/(double) Número a comprobar
	 * @param valorMinimo (int) Número valor aceptable
	 * @param valorMaximo (int) MINumero valor aceptable
	 * 
	 * @return true si valorMinimo > valor > valorMaximo
	 * FECHA: Enero 2023
	 * AUTOR: Lorena Acedo 
	 * 
	 * **************************************************************************************/
	public static boolean cumpleRango(
			int valor, 
			int valorMinimo,
			int valorMaximo){
					
			return valor >= valorMinimo && valor <= valorMaximo;
	}	
		

	/* ***************************************************************************************
	 * NOMBRE: cumpleLongitudMin                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprobar que el texto pasado tiene una longitud de al menos x caracteres, siendo
	 * 		x el entero pasado como parámetro
	 * 
	 * @param texto String texto a comprobar
	 * @param longitudMinima int que indique longitud minima.
	 * 
	 * @return cierto, si la longitud del texto es mayor o igual que longitudMinima
	 * FECHA: Enero 2023
	 * AUTOR: Lorena Acedo
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitudMin(
			String texto, 
			int longitudMinima)
	{
		return texto.length()>=longitudMinima;
		
	}


	/* ***************************************************************************************
	 * NOMBRE: cumpleLongitudMax                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprobar que el texto pasado tiene una longitud de, como mucho, x caracteres, siendo
	 * 		x el entero pasado como parámetro
	 * 
	 * @param texto String con el texto a comprobar
	 * @param longitudMaxima int con la longitud máxima del texto
	 * 
	 * @return true, si el texto es menor o igual que la longitud máxima.
	 * FECHA: Enero 2023 
	 * AUTOR: Lorena Acedo 
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitudMax(
			String texto, 
			int longitudMaxima)
	{
		
		return texto.length()<=longitudMaxima;
		
	}


	/****************************************************************************************
	 * NOMBRE: cumpleLongitud                                                                 *
	 * 
	 * DESCRIPCION: *//**
	 * 		Comprobar que la longitud de un texto se encuentra entre unos valores maximos y minimos 
	 * 
	 * @param texto String con el texto que a validar
	 * @param longitudMinima (int) Minima longitud del texto
	 * @param longitudMaxima (int) Maxima longitud valida para el texo
	 * 
	 * @return true, si la longitud del texto cumple: longitudMinima
	 *               <= longitudTexto <=longitudMaxima
	 * FECHA: Enero 2023 
	 * AUTOR: Lorena Acedo
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitud(
			String texto, 
			int longitudMinima, 
			int longitudMaxima){

		return texto.length()>=longitudMinima && texto.length()<=longitudMaxima;
		

	}
	/**
	 * Valida una fecha calendar con minimo min
	 * @param fecha
	 * @param min
	 * @return
	 */
	
	public static boolean valDateMin(LocalDate fecha, LocalDate min){
		
		return fecha.isAfter(min) || fecha.isEqual(min);
		
	}
	
	/**
	 * Valida una fecha calendar con maximo max
	 * @param fecha
	 * @param max
	 * @return
	 */
	public static boolean valDateMax(LocalDate fecha, LocalDate max){
		return fecha.isBefore(max) || fecha.isEqual(max);
		
	}	
	
	/**
	 * esFechaValida
	 * Recibe una string con formato fecha dd/mm/aaaa y comprueba el formato
	 * @param fecha
	 * @return
	 */
	public static boolean esFechaValida(String fecha){
		try {
		      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		      LocalDate localDate = LocalDate.parse(fecha, formatter);
		      return true;
		    } catch (DateTimeParseException e) {
		      return false;
		 }
	 }
	
	
	/**
	 * Nombre esPasswordValida
	 * Descripcion Comprueba que la cadena recibida cumpla con lasnormas de contraseña
	 * @param password string con la contraseña introducida
	 * @return true si cumple con las especificaciones
	 */
	public static boolean esPasswordValida(String password){
		Pattern p=Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher=p.matcher(password);
		
		return matcher.matches();

	}
	
	/**
	 * Detecta si el espacio está vacio
	 * 
	 * El método trim() podrá eliminar los espacios iniciales y finales del string,
	 * detectando así si hay espacios o simplemente está vacio.
	 * 
	 * @param texto
	 * @return
	 */
	public static boolean isVacio(String texto) {
		
		return (texto==null || texto.equals(""));
	}
}
