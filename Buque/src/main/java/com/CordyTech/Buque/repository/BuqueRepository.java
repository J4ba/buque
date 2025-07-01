package com.CordyTech.Buque.repository;

import com.CordyTech.Buque.model.Buque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuqueRepository extends JpaRepository<Buque, String> {

    Optional<Buque> findByNombreBuque(String nombreBuque);

    Optional<Buque> findByFechaLlegada(Date fecha);

}
