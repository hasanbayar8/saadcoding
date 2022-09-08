package com.example.saatcoding.requirement.repository;

import com.example.saatcoding.requirement.main.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenseRepository
        extends JpaRepository<License, Long> {

    @Query("SELECT s FROM License s WHERE s.name = ?1 ")
    Optional<License> findLicenseByName(String name);

    License findOneById(Long id);

}
