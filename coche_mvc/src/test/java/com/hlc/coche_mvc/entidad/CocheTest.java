package com.hlc.coche_mvc.entidad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class CocheTest {
	
	Coche coche;
	
	 @BeforeAll
	  static void setup() {
	     System.out.println("Se ejecuta antes de todas las pruebas");
 
	 }

	 @BeforeEach
	    void setupThis() {
		 coche = new Coche();
	     System.out.println("Se ejecuta antes de cada método @Test en la clase actual");
	    }

		@Test
		@DisplayName("Getter/Setter de color")
		void comprobarColorCorrecto() {
			coche.setColor("verde");
			
			String resultadoEsperado = "verde";
	        
			assertEquals(resultadoEsperado, coche.getColor(), "El color no coincide");
	        
		}
		
		
	

}
