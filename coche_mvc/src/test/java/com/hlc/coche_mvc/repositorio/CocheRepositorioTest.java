package com.hlc.coche_mvc.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hlc.coche_mvc.entidad.Coche;
import org.junit.jupiter.api.Test;


@DataJpaTest
class CocheRepositorioTest {

    @Autowired
    private CocheRepositorio cocheRepositorio;

    @BeforeEach
    void setup() {
        Coche coche1 = cocheRepositorio.save(new Coche("Toyota", "1234ABC", "rojo"));
        Coche coche2 = cocheRepositorio.save(new Coche("Honda", "5678DEF", "azul"));
        System.out.println("Coche 1 ID: " + coche1.getId());
        System.out.println("Coche 2 ID: " + coche2.getId());
    }

    @Test
    @DisplayName("Encontrar un coche por ID devuelve un resultado presente")
    void testFindByIdIsPresent() {
        Coche cocheGuardado = cocheRepositorio.save(new Coche("Toyota", "1234ABC", "rojo"));
        Long cocheId = cocheGuardado.getId(); // Obtén el ID generado dinámicamente.

        Optional<Coche> coche = cocheRepositorio.findById(cocheId);
        assertThat(coche).isPresent(); // Verifica que el Optional contiene un valor.
    }
    
    @Test
    @DisplayName("Encontrar un coche por ID devuelve el nombre correcto")
    void testFindByIdName() {
        Coche cocheGuardado = cocheRepositorio.save(new Coche("Toyota", "1234ABC", "rojo"));
        Long cocheId = cocheGuardado.getId(); // Obtén el ID generado dinámicamente.

        Optional<Coche> coche = cocheRepositorio.findById(cocheId);
        assertThat(coche).isPresent();
        assertThat(coche.get().getNombre()).isEqualTo("Toyota");
    }

    @Test
    @DisplayName("Listar todos los coches devuelve el número esperado de registros")
    void testFindAllSize() {
        // Verifica que hay 2 coches en la base de datos
        List<Coche> coches = cocheRepositorio.findAll();
        assertThat(coches).hasSize(2);
    }

    @Test
    @DisplayName("Guardar un coche genera un ID no nulo")
    void testSaveGeneratesId() {
        // Verifica que guardar un coche genera un ID automáticamente
        Coche coche = cocheRepositorio.save(new Coche("Ford", "9876XYZ", "verde"));
        assertThat(coche.getId()).isNotNull();
    }

    @Test
    @DisplayName("Guardar un coche mantiene los datos del modelo")
    void testSaveModelName() {
        // Verifica que el coche guardado mantiene su nombre
        Coche coche = cocheRepositorio.save(new Coche("Ford", "9876XYZ", "verde"));
        assertThat(coche.getNombre()).isEqualTo("Ford");
    }

    @Test
    @DisplayName("Eliminar un coche por ID deja el registro ausente")
    void testDeleteByIdIsAbsent() {
        // Verifica que el coche eliminado ya no está presente
        cocheRepositorio.deleteById(1L);
        Optional<Coche> coche = cocheRepositorio.findById(1L);
        assertThat(coche).isNotPresent();
    }
}

