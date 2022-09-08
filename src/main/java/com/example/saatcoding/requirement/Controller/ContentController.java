package com.example.saatcoding.requirement.Controller;

import com.example.saatcoding.requirement.main.Content;
import com.example.saatcoding.requirement.service.implementation.ContentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api/v1/content")
@RequiredArgsConstructor
public class ContentController{
    private final ContentServiceImp contentService;

    @GetMapping
    public List<Content> getContent() {
        return contentService.getContent();
    }

    @PostMapping
    public ResponseEntity registerNewContent(@RequestBody Content content) {
        contentService.addNewContent(content);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(path = "{contentId}")
    public void deleteContent(@PathVariable("contentId") Long contentId) {
        contentService.deleteContent(contentId);
    }

    @PutMapping(path = "{contentId}")
    public void updateContent(
            @PathVariable("contentId") Long contentId,
            @RequestParam(required = false) String videoUrl) {
        contentService.updateContent(contentId, videoUrl);
    }

    @PostMapping(path = "/{contentId}/addLicenseToContent")
    public ResponseEntity addContentToLicenses(@PathVariable("contentId") Long contentId,
                                               @RequestBody Long[] licenseIds) {
        contentService.addContentToLicenses(contentId, licenseIds);

        return ResponseEntity.ok("OK");
    }

}
