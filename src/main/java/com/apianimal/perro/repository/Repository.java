package com.apianimal.perro.repository;

import com.apianimal.perro.model.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Repository extends JpaRepository<Perro, Integer> {

    //  @Query("SELECT t FROM Perro t WHERE t.raza= ?1")
    List<Perro> findByRaza(String raza);

    @Query("select count(e) from Perro e where e.raza= ?1")
    long countHastaTresDeIgualRaza(String raza);
}
