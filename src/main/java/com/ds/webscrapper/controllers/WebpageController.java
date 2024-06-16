package com.ds.webscrapper.controllers;

import com.ds.webscrapper.models.Webpage;
import com.ds.webscrapper.service.SpiderService;
import com.ds.webscrapper.service.WebpageService;
import com.ds.webscrapper.service.WebscrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class WebpageController {

    @Autowired
    private WebpageService webpageService;

    @Autowired
    private WebscrapperService webscrapperService;

    @Autowired
    private SpiderService spiderService;
//---------------------------------------------------------------------------------
    @GetMapping("/search")
    public List<Webpage> getAllWebpages(@RequestParam("query") String query) {
        return webpageService.getAllWebpages(query);
    }


//-------------------------------------------------------------------------------------
    @GetMapping("/webscrapper")
    public Webpage createWebpageScrape(@RequestParam("url") String url) throws IOException {

        return webscrapperService.scrapeAndSave(url);
    }

    @GetMapping("/start")
    public void scrapeLinksAndSave() {

        spiderService.start();
    }






    @GetMapping("/{id}")
    public Webpage getWebpageById(@PathVariable Integer id) {
        return webpageService.getWebpageById(id);
    }

    @PostMapping
    public Webpage createWebpage(@RequestBody Webpage webpage) {
        return webpageService.saveWebpage(webpage);
    }
    @PutMapping("/{id}")
    public Webpage updateWebpage(@PathVariable Integer id, @RequestBody Webpage webpage) {
        Webpage existingWebpage = webpageService.getWebpageById(id);
        if (existingWebpage != null) {
            existingWebpage.setId(webpage.getId());
            existingWebpage.setRank(webpage.getRank());
            existingWebpage.setTitle(webpage.getTitle());
            existingWebpage.setDomain(webpage.getDomain());
            existingWebpage.setPicture(webpage.getPicture());
            existingWebpage.setDescription(webpage.getDescription());
            existingWebpage.setUrl(webpage.getUrl());
            return webpageService.saveWebpage(existingWebpage);
        }
        return null;
    }
    public void deleteWebpage(Integer id) {
        webpageService.deleteWebpage(id);
    }
}
