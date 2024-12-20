package com.hlc.coche_mvc.repositorio;

//import org.springframework.data.repository.CrudRepository; -->
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlc.coche_mvc.entidad.Coche;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche, Long> {

}
