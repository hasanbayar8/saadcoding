package com.example.saatcoding.requirement.repository;

import com.example.saatcoding.requirement.entities.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenseRepository
        extends JpaRepository<License, Long> {

    @Query("SELECT s FROM License s WHERE s.name = ?1 ")
    Optional<License> findLicenseByName(String name);

    License findOneById(Long id);

    List<License> findAllByIdIn(List<Long> licenseIds);
}
