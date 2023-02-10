package util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.rf.tienda.util.Validator;

class ValidatorTest {
	
	final String ALFANUMERICO_OK="abc456";
	final String ALFANUMERICO_NOT= "$%&·&&&";
	
	final String PHONENUMBER_OK="1233211230";
	final String PHONENUMBER_NOT="345";
	
	final String EMAIL_OK="lorena.acedo.vazquez@accenture.com";
	final String EMAIL_NOT="holaquetal.com";
	
	final String DNI_OK="28.825.401-F";
	final String DNI_NOT="34.454.34543";
	final String DNI_NOT_CORTO="345";
	
	final String FECHA_OK="09/01/2023";
	final String FECHA_NOT="234432";
	
	final String PASSWORD_OK="3E456&54*^$5a";
	final String PASSWORD_NOT="12345";
	
	

	@Test
	void testIsAlfanumeric() {
		assertTrue(Validator.isAlfanumeric(ALFANUMERICO_OK));
		assertFalse(Validator.isAlfanumeric(ALFANUMERICO_NOT));
	}

	@Test
	void testCumplePhoneNumber() {
		assertTrue(Validator.cumplePhoneNumber(PHONENUMBER_OK));
		assertFalse(Validator.cumplePhoneNumber(PHONENUMBER_NOT));
	}

	@Test
	void testIsEmailValido() {
		assertTrue(Validator.isEmailValido(EMAIL_OK));
		assertFalse(Validator.isEmailValido(EMAIL_NOT));
	}

	@Test
	void testCumpleDNI() {
		assertTrue(Validator.cumpleDNI(DNI_OK));
		assertFalse(Validator.cumpleDNI(DNI_NOT));
		assertFalse(Validator.cumpleDNI(DNI_NOT_CORTO));
	}

	@Test
	void testCumpleRango() {
		assertTrue(Validator.cumpleRango(5, 1, 10));
        assertFalse(Validator.cumpleRango(0, 1, 10));
	}


	@Test
	void testCumpleLongitudMin() {
		assertTrue(Validator.cumpleLongitudMin("El texto con la longitud correcta", 10));
        assertFalse(Validator.cumpleLongitudMin("Hola", 10));
	}

	@Test
	void testCumpleLongitudMax() {
		assertTrue(Validator.cumpleLongitudMax("El texto con la longitud correcta", 100));
        assertFalse(Validator.cumpleLongitudMax("El texto sobrepasa el límite permitido", 10));
	}

	@Test
	void testCumpleLongitud() {
		assertTrue(Validator.cumpleLongitud("Texto con la longitud correcta", 5, 50));
        assertFalse(Validator.cumpleLongitud("Texto corto", 20, 50));
	}

	@Test
	void testValDateMin() {
		LocalDate fechaMin_ok = LocalDate.of(2022, 12, 31);
	    LocalDate min_ok = LocalDate.of(2022, 01, 01);
	    LocalDate fechaMin_Not = LocalDate.of(2021, 12, 31);
	    LocalDate min_Not = LocalDate.of(2022, 01, 01);
	    
		assertTrue(Validator.valDateMin(fechaMin_ok, min_ok));
		assertFalse(Validator.valDateMin(fechaMin_Not, min_Not));
	}

	@Test
	void testValDateMax() {	
		LocalDate fechaMax_ok = LocalDate.of(2022, 12, 31);
	    LocalDate max_ok = LocalDate.of(2023, 01, 01);
		LocalDate fechaMax_Not = LocalDate.of(2023, 01, 02);
	    LocalDate max_Not = LocalDate.of(2023, 01, 01);
	    
		assertTrue(Validator.valDateMax(fechaMax_ok,max_ok));
		assertFalse(Validator.valDateMax(fechaMax_Not,max_Not));
		
	}

	@Test
	void testEsFechaValida() {
		assertTrue(Validator.esFechaValida(FECHA_OK));
		assertFalse(Validator.esFechaValida(FECHA_NOT));
	}

	@Test
	void testEsPasswordValida() {
		assertTrue(Validator.esPasswordValida(PASSWORD_OK));
		assertFalse(Validator.esPasswordValida(PASSWORD_NOT));
	}

}
