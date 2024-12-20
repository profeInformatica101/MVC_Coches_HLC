package com.hlc.coche_mvc.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.hlc.coche_mvc.entidad.Coche;
import com.hlc.coche_mvc.repositorio.CocheRepositorio;

@ExtendWith(MockitoExtension.class) // Habilita la integración con Mockito.
@SpringBootTest
class CocheServicioTest {

    @InjectMocks
    CocheServicio cocheServicio;

    @Mock
    private CocheRepositorio cocheRepositorio;

    private Coche cocheEsperado; // Campo para reutilizar en todas las pruebas.

    @BeforeAll
    static void setup() {
        System.out.println("Se ejecuta antes de todas las pruebas");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("Se ejecuta antes de cada método @Test en la clase actual");

        // Configuración inicial de un coche reutilizable.
        cocheEsperado = new Coche();
        cocheEsperado.setId(1L);
        cocheEsperado.setNombre("Toyota");
    }

    @AfterEach
    void tearThis() {
        System.out.println("Se ejecuta después de cada método @Test en la clase actual");
    }

    @AfterAll
    static void tear() {
        System.out.println("Se ejecuta después de todas las pruebas");
    }

    @Test
    @DisplayName("Prueba de guardar un coche")
    void testGuardarCoche() {
        // Dado
        when(cocheRepositorio.save(any(Coche.class))).thenReturn(cocheEsperado);

        // Cuando
        Coche cocheGuardado = cocheServicio.guardarCoche(cocheEsperado);

        // Entonces
        assertEquals(cocheEsperado, cocheGuardado, "El coche guardado debe coincidir con el mock");
        verify(cocheRepositorio, times(1)).save(cocheEsperado);
    }

    @Test
    @DisplayName("Prueba de obtener un coche por ID")
    void testObtenerCochePorId() {
        // Dado
        when(cocheRepositorio.findById(1L)).thenReturn(Optional.of(cocheEsperado));

        // Cuando
        Coche cocheResultado = cocheServicio.obtenerCochePorId(1L);

        // Entonces
        assertNotNull(cocheResultado, "El coche no debe ser nulo");
        assertEquals(cocheEsperado, cocheResultado, "El coche recuperado debe coincidir con el esperado");
        verify(cocheRepositorio, times(1)).findById(1L);
    }
}