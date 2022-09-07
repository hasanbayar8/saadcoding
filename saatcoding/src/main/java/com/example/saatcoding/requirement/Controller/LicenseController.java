package com.example.saatcoding.requirement.Controller;

import com.example.saatcoding.requirement.main.License;
import com.example.saatcoding.requirement.service.ContentService;
import com.example.saatcoding.requirement.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;
    private final ContentService contentService;

    @GetMapping
    public List<License> getLicense() {
        return licenseService.getLicense();
    }

    @PostMapping
    public ResponseEntity registerNewLicense(@RequestBody License license) {
        licenseService.add(license);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(path = "{licenseId}")
    public void deleteLicense(@PathVariable("licenseId") Long licenseId) {
        licenseService.deleteLicense(licenseId);
    }

    @PostMapping(path = "/{licenseId}/addContentToLicense")
    public ResponseEntity addLicenseToContents(@PathVariable("licenseId") Long licenseId, @RequestBody Long[] contentIds) {
        licenseService.addLicenseToContents(licenseId, contentIds);

        return ResponseEntity.ok("OK");
    }
}
