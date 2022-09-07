package com.example.saatcoding.requirement.service.implementation;

import com.example.saatcoding.requirement.main.License;
import com.example.saatcoding.requirement.repository.ContentRepository;
import com.example.saatcoding.requirement.main.Content;
import com.example.saatcoding.requirement.repository.LicenseRepository;
import com.example.saatcoding.requirement.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContentServiceImp implements ContentService {
    private final ContentRepository contentRepository;
    private final LicenseRepository licenseRepository;

    public List<Content> getContent() {
        return contentRepository.findAll();
    }

    public void addNewContent(Content content) {
        Optional<Content> contentOptional = contentRepository.
                findContentByName(content.getName());
        if (contentOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        contentRepository.save(content);
    }
    public void deleteContent(Long contentId) {
        boolean exists = contentRepository.existsById(contentId);
        if (!exists) {
            throw new IllegalStateException("content with id " +
                    contentId + " does not exists");
        }
        contentRepository.deleteById(contentId);
    }
    @Transactional
    public void updateContent(Long contentId,
                               String videoUrl) {
        Content content = contentRepository.findById(contentId).orElseThrow(() -> new IllegalStateException(
                "content with id" + contentId + " does not exists"));
        if (videoUrl != null &&
                videoUrl.length() > 0 &&
                !Objects.equals(content.getVideoUrl(), videoUrl)) {
            Optional<Content> contentOptional = contentRepository.findContentByVideoUrl(videoUrl);
            if (contentOptional.isPresent()) {
                throw new IllegalStateException("videoUrl used");

            }
            content.setVideoUrl(videoUrl);
        }
        contentRepository.save(content);
    }

    @Override
    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    @Override
    public Content getContentById() {
        return null;
    }
    @Override
    public void add(Content content) {
        boolean isExisted= contentRepository.existsById(content.getId());
        if (!isExisted) {
            contentRepository.save(content);
        }
    }
    @Override
    public void addContentToLicenses(Long contentId, Long[] licenseIds) {
        boolean isExisted = contentRepository.existsById(contentId);
        if (isExisted) {
            for (Long licenseId:licenseIds) {
                License license = licenseRepository.findOneById(licenseId);
                if (license != null && !license.getContents().stream().anyMatch(content -> content.getId().equals(contentId))){
                    Content content = contentRepository.findOneById(contentId);
                    license.getContents().add(content);
                }
            }
        }
    }
}
