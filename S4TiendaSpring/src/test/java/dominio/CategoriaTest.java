package dominio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rf.tienda.Entity.Categoria;
import com.rf.tienda.util.Validator;

class CategoriaTest {

	@Test
	void testSetCat_nombre() {
		
		Categoria categoria = new Categoria();
        int NUM_MIN = 5;
        int NUM_MAX = 50;
        
        String cat_nombre = "Juan";
        assertFalse("setCat_nombre should return false for invalid input", categoria.setCat_nombre(cat_nombre));
        assertNull("cat_nombre should not be set if it does not meet length requirements", categoria.getCat_nombre());
        
        cat_nombre = "valid name";
        assertTrue("setCat_nombre should return true for valid input", categoria.setCat_nombre(cat_nombre));
        assertEquals("cat_nombre should be set if it meets length requirements", cat_nombre, categoria.getCat_nombre());

}
}
