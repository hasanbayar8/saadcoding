package com.example.saatcoding.requirement.service.implementation;

import com.example.saatcoding.requirement.main.Content;
import com.example.saatcoding.requirement.repository.ContentRepository;
import com.example.saatcoding.requirement.repository.LicenseRepository;
import com.example.saatcoding.requirement.main.License;
import com.example.saatcoding.requirement.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseServiceImp implements LicenseService {
    private final LicenseRepository licenseRepository;
    private final ContentRepository contentRepository;

    public List<License> getLicense() {
        return licenseRepository.findAll();
    }

    public void addNewLicense(License license) {
        Optional<License> licenseOptional = licenseRepository.
                findLicenseByName(license.getName());
        if (licenseOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        licenseRepository.save(license);
    }

    public void deleteLicense(Long licenseId) {
        boolean exists = licenseRepository.existsById(licenseId);
        if (!exists) {
            throw new IllegalStateException("license with id " +
                    licenseId + " does not exists");
        }
        licenseRepository.deleteById(licenseId);
    }

    @Override
    public List<License> getAll() {
        return licenseRepository.findAll();
    }

    @Override
    public License getLicenseById() {
        return null;
    }
    @Override
    public void add(License license) {
        boolean isExisted = licenseRepository.existsById(license.getId());
        if (!isExisted) {
            licenseRepository.save(license);
        }
    }
    @Override
    public void addLicenseToContents(Long licenseId, Long[] contentIds) {
        boolean isExisted = licenseRepository.existsById(licenseId);
        if (isExisted) {
            for (Long contentId : contentIds) {
                Content content = contentRepository.findOneById(contentId);
                if (content != null && !content.getLicenses().stream().anyMatch(license -> license.getId().equals(licenseId))) {
                    License license = licenseRepository.findOneById(licenseId);
                    content.getLicenses().add(license);
                }
            }
        }

    }
}
