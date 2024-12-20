package com.hlc.coche_mvc.controlador;

import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
}
