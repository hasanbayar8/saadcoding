package com.example.saatcoding.requirement.service.implementation;

import com.example.saatcoding.requirement.entities.Content;
import com.example.saatcoding.requirement.entities.License;
import com.example.saatcoding.requirement.entities.Status;
import com.example.saatcoding.requirement.repository.ContentRepository;
import com.example.saatcoding.requirement.repository.LicenseRepository;
import com.example.saatcoding.requirement.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


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
        boolean isExisted = contentRepository.existsById(content.getId());
        if (!isExisted) {
            contentRepository.save(content);
        }
    }

    public boolean isOverlapped(License existsLicenses, License newLicense) {

        if (!newLicense.getStartTime().isAfter(existsLicenses.getEndTime())
                && !newLicense.getEndTime().isBefore(existsLicenses.getStartTime())) {
            return true;
        }
        return false;
    }

    @Override
    public void addContentToLicenses(Long contentId, Long[] licenseIds) {
        boolean isExisted = contentRepository.existsById(contentId);
        Content content = contentRepository.findOneById(contentId);
        if (isExisted && licenseIds.length > 0) {
            Content newContent = checkLicenseOfTheContent(content, Arrays.asList(licenseIds));
            if (newContent != null) {

                contentRepository.save(newContent);
            }
        }
    }
    @Override
    public void updateStatus(Long contentId, Status status) throws IllegalStateException {

        try {
            Content content = contentRepository.findById(contentId).orElseThrow(() -> new IllegalStateException(
                    "content status " + status + "does not exist"));

            LocalDate localDate = LocalDate.now();
            System.out.println("Current local time : " + localDate);

            boolean isBefore = content.getLicenses().stream().allMatch(license -> license.getEndTime().isBefore(localDate));

            System.out.println("is before: " + isBefore);

            if (content.getVideoUrl() != null && content.getPosterUrl() != null && !content.getLicenses().isEmpty()
                    & !isBefore) {
                System.out.println("worked in the if");
                content.setStatus(Status.Published);
            } else {
                content.setStatus(Status.Inprogress);
            }

            contentRepository.save(content);

        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    private Content checkLicenseOfTheContent(Content content, List<Long> licenseIds) {
        List<License> licenses = licenseRepository.findAllByIdIn(licenseIds);

        //for (License existLicenses: content.getLicenses()) {

        if (!content.getLicenses().isEmpty() && licenseIds.size() > 0) {
            ArrayList<License> license = new ArrayList<>();
            license.addAll(content.getLicenses());
            Iterator<License> iterator = content.getLicenses().iterator();
            while(iterator.hasNext()) {
                License existLicenses = iterator.next();
                for (License newLicense : licenses) {
                    if (newLicense.getId() != existLicenses.getId()) {
                        if (!isOverlapped(existLicenses, newLicense)) {
                           content.getLicenses().add(newLicense);
                        }
                    }
                }
            }
        }
        if (content.getLicenses().isEmpty()) {
            content.getLicenses().addAll(licenses);
        }
        return content;
    }
}