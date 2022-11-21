package com.example.saatcoding.requirement.controller;

import com.example.saatcoding.requirement.entities.License;
import com.example.saatcoding.requirement.service.implementation.LicenseServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseServiceImp licenseService;

    @GetMapping
    public List<License> getLicense() {
        return licenseService.getLicense();
    }

    @PostMapping
    public ResponseEntity registerNewLicense(@RequestBody License license) {
        licenseService.addNewLicense(license);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(path = "{licenseId}")
    public void deleteLicense(@PathVariable("licenseId") Long licenseId) {
        licenseService.deleteLicense(licenseId);
    }

    @PostMapping(path = "/{licenseId}/addContentToLicense")
    public ResponseEntity addLicenseToContents(@PathVariable("licenseId") Long licenseId,
                                               @RequestBody Long[] contentIds) {
        licenseService.addLicenseToContents(licenseId, contentIds);

        return ResponseEntity.ok("OK");
    }
}
