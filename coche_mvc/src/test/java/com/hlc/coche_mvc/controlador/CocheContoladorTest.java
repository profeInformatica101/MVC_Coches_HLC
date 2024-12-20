package com.hlc.coche_mvc.controlador;

import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // Para realizar solicitudes HTTP como POST y GET.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  // Para las expectativas como status(), view(), y model().
import com.hlc.coche_mvc.entidad.Coche;
import com.hlc.coche_mvc.servicio.CocheServicio;


@WebMvcTest(CocheContolador.class)
class CocheControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CocheServicio cocheServicio;

    @Test
    void testMostrarIndex() throws Exception {
        // Configurar el comportamiento del mock
        Mockito.when(cocheServicio.obtenerCoches()).thenReturn(List.of(
            new Coche("Toyota", "1234ABC", "rojo"),
            new Coche("Honda", "5678DEF", "azul")
        ));

        // Realizar la solicitud y verificar la respuesta
        mockMvc.perform(MockMvcRequestBuilders.get("/coches"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("index"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("coches"))
            .andExpect(MockMvcResultMatchers.model().attribute("coches", hasSize(2)));
    }
    @Test
    @DisplayName("Validación de datos inválidos al agregar un coche")
    void testValidacionDatosInvalidos() throws Exception {
        mockMvc.perform(post("/coches/agregar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nombre", "") // Campo vacío para forzar error
                .param("matricula", "123XYZ") // Matrícula inválida
                .param("color", "morado") // Color inválido
        )
        .andExpect(status().isOk())
        .andExpect(view().name("coche-form"))
        .andExpect(model().attributeHasFieldErrors("coche", "nombre"))
        .andExpect(model().attributeHasFieldErrors("coche", "matricula"))
        .andExpect(model().attributeHasFieldErrors("coche", "color"));
    }
    
}
