package com.hlc.coche_mvc.entidad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

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
		

		@Test
		@DisplayName("Validación de restricciones en la entidad Coche")
		void testValidacionEntidad() {
		    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();

		    Coche coche = new Coche("", "123XYZ", "morado"); // Datos inválidos
		    Set<ConstraintViolation<Coche>> violaciones = validator.validate(coche);

		    // Verifica que hay 4 errores debido a las restricciones combinadas de @NotBlank y @Size
		    assertThat(violaciones).hasSize(4);

		    // Verifica que las propiedades específicas tienen errores
		    assertThat(violaciones).anyMatch(v -> v.getPropertyPath().toString().equals("nombre") &&
		            v.getMessage().equals("El nombre no puede estar vacio"));
		    assertThat(violaciones).anyMatch(v -> v.getPropertyPath().toString().equals("nombre") &&
		            v.getMessage().equals("El nombre de tener entre 2 y 50 carácteres"));
		    assertThat(violaciones).anyMatch(v -> v.getPropertyPath().toString().equals("matricula"));
		    assertThat(violaciones).anyMatch(v -> v.getPropertyPath().toString().equals("color"));
		}
	

}
