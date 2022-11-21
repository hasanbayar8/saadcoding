package com.example.saatcoding.requirement.service;

import com.example.saatcoding.requirement.entities.Content;
import com.example.saatcoding.requirement.entities.Status;

import java.util.List;

public interface ContentService {

    List<Content> getAll();

    Content getContentById();

    void add(Content content);

    void addContentToLicenses(Long contentId, Long[] licenseIds);

    List<Content> getContent();

    void deleteContent(Long contentId);

    void updateContent(Long contentId, String videoUrl);

    void updateStatus(Long contentId, Status status);

}

