package com.example.saatcoding.requirement.cronjob;

import com.example.saatcoding.requirement.entities.Content;
import com.example.saatcoding.requirement.entities.Status;
import com.example.saatcoding.requirement.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class AutoProcessJobs {
    @Autowired
    private ContentService contentService;

    public AutoProcessJobs(ContentService contentService) {
        this.contentService = contentService;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void updateStatus() throws IllegalStateException {
        List<Content> contents = contentService.getAll();

        for (Content content : contents) {
            if (content.getStatus().label.equals(Status.Published.label)) {
             contentService.updateStatus(content.getId(), Status.Inprogress);
            } else {
                contentService.updateStatus(content.getId(), Status.Published);
            }
        }
    }
}