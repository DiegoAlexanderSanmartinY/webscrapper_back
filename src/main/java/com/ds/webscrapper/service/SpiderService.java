package com.ds.webscrapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpiderService {

    @Autowired
    private WebscrapperService webscrapperService;

    public void start(){
        String initialLink = "https://elpais.com/america/2024-04-21/el-candidato-13-el-hombre-que-une-a-la-oposicion-venezolana.html#?rel=mas" ;
        scrapeLinksAndSave(initialLink);
    }

    public void scrapeLinksAndSave(String url){
        List<String> links=webscrapperService.getAllLinks(url);
        links.forEach(link -> {
            webscrapperService.scrapeAndSave(link);
            scrapeLinksAndSave(link);

        });
    }
}
