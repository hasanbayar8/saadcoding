package com.example.saatcoding.requirement.service;

import com.example.saatcoding.requirement.main.License;

import java.util.List;

public interface LicenseService {

    List<License> getAll();

    License getLicenseById();

    void add(License license);

    void addLicenseToContents(Long licenseId, Long[] contentIds);

    List<License> getLicense();

    void deleteLicense(Long licenseId);
}
