package com.CordyTech.Buque.repository;

import com.CordyTech.Buque.model.Buque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BuqueRepository extends JpaRepository<Buque, String> {

    List<Buque> findByNombreBuqueContainingIgnoreCase(String nombre);

    List<Buque> findByFechaLlegadaBefore(Date fecha);

}
