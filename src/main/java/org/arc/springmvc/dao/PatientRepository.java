package org.arc.springmvc.dao;
import org.arc.springmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository <Patient, Long>{
    List <Patient> findByNameContains(String name);
    List <Patient> findByMalade(boolean m);
    List <Patient> findByNameContainsAndMalade(String n, boolean m);
    Page<Patient> findByNameContains(String nome, Pageable pg);
}
