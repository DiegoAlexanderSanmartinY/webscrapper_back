package com.ds.webscrapper.jobs;

import com.ds.webscrapper.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebScraperJobs {

    @Autowired
    private SpiderService spiderService;

    @Scheduled(cron = "0 0 12 * * ?")
    public void executeJob() {
        spiderService.start();

    }
}
