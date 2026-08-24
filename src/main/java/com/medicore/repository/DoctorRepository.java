package com.medicore.repository;
import com.medicore.model.Doctor;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
